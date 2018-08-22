package cn.ffcs.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;

import cn.ffcs.model.TbMenu;
import cn.ffcs.model.TbOperateLog;
import cn.ffcs.model.TbRole;
import cn.ffcs.model.TbUser;
import cn.ffcs.pojo.TreeView;
import cn.ffcs.service.TbMenuService;
import cn.ffcs.service.TbOperateLogService;
import cn.ffcs.service.TbRoleService;
import cn.ffcs.service.TbUserService;
import cn.ffcs.util.MethodUtils;
import cn.ffcs.util.ShiroUtils;
import cn.ffcs.util.SysConstants;
import cn.ffcs.util.TreeUtils;


@Controller
@Scope(value="prototype")
@RequestMapping("/")
public class MainController extends BaseController {
    
    @Resource
    private TbMenuService tbMenuService;
    @Resource
    private TbRoleService tbRoleService;
    @Resource
    private TbUserService tbUserService;
    @Resource
    private TbOperateLogService tbOperateLogService;
    @Autowired
    private SessionDAO sessionDAO;
    
    
    /**
     * 权限用户登入
     * @param loginName
     * @param password
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(TbUser user, Model model, HttpServletRequest request) throws Exception {
        
        String loginname = user.getLoginname();
        String password = user.getPassword();
        
        Subject subject = SecurityUtils.getSubject();
        
        Session session = subject.getSession();
        session.setAttribute(SysConstants.SESSION_MENUS, Boolean.FALSE);
        session.setAttribute(SysConstants.SESSION_USER, Boolean.FALSE);
        session.setAttribute(SysConstants.SESSION_STATUS, Boolean.FALSE);
        
        /*判断输入验证码是否正确*/
        String inputcode = request.getParameter("verificationcode");//获取用户输入验证码
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);//获取session中验证码  
        if( code==null || !code.equals(inputcode)){
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "验证码错误！");
            return "login"; //如果认证失败，则跳会登录页面并提示错误信息
        }
        
        logger.debug("password:"+MethodUtils.md5(password));
        
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, MethodUtils.md5(password));
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "账号输入错误！");
            return "login"; 
        } catch (LockedAccountException e) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "账号已被禁用！");
            return "login"; 
        } catch (ExcessiveAttemptsException e) {  
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "登录失败连续超过5次，账号锁定中，请于3分钟后尝试！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "账号或者密码输入错误！");
            return "login";
        } catch (AuthenticationException e) { 
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", e.getMessage());
            return "login";
        }
        
        TbUser curUser = (TbUser) session.getAttribute(SysConstants.SESSION_USER);
        List<TbMenu> menuList = new ArrayList<TbMenu>();
                
        String ids = curUser.getRoleids();
        if(StringUtils.isBlank(ids)) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "该用户没有绑定角色，请联系管理员！");
            return "login"; 
        }
        
        List<Long> roleIds = new ArrayList<Long>();
        if(ids.indexOf(",")>-1){
            String[] _ids = ids.split(",");
            for(int i=0;i<_ids.length;i++){
                roleIds.add(Long.parseLong(_ids[i]));
            }
        } else {
            roleIds.add(Long.parseLong(ids));
        }
        //获取所属角色
        List<TbRole> roles = tbRoleService.selectListByIds(roleIds);
        //菜单ids，使用线程安全CopyOnWriteArrayList进行操作
        List<Long> menuIds = new CopyOnWriteArrayList<Long>();
        for(TbRole role : roles){
            String r_mId = role.getMenuids();
            if(r_mId.indexOf(",")>-1){
                String[] _ids = r_mId.split(",");
                for(int i=0;i<_ids.length;i++){
                    menuIds.add(Long.parseLong(_ids[i]));
                }
            } else {
                menuIds.add(Long.parseLong(r_mId));
            }
        }
        if(menuIds==null || menuIds.size()==0){
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "该用户没有绑定菜单，请联系管理员！");
            return "login"; 
        }
        
        TbUser tbUser = tbUserService.selectByKey(curUser.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String curentDateTime = sdf.format(new Date());
    	tbUser.setLastLoginTime(curentDateTime);
    	
    	InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString(); //获取本机ip  
		    String hostName=addr.getHostName().toString(); //获取本机计算机名称  
		    tbUser.setLastLoginIp(ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
        
		tbUser.setIslogin(1);
		tbUser.setLoginQty(tbUser.getLoginQty()+1);	
		
		tbUserService.updateSelective(tbUser);
		
		TbOperateLog tbOperateLog = buildOperateLog("登录系统");
    	tbOperateLogService.insert(tbOperateLog);
		
        // 判断父指标是否存在
        for(Long mId : menuIds){
            //查询父指标
            TbMenu m = tbMenuService.selectByKey(mId);
            Long pid = m.getParentid();
            if(!menuIds.contains(pid)){
                menuIds.add(pid);
            }
        }
        
        menuList = tbMenuService.selectListByIds(menuIds);
        
        // 格式化菜单树
        List<TreeView> menuTree = TreeUtils.formatMenuTree(menuList);
        
        // 设置session到menus
        session.setAttribute(SysConstants.SESSION_MENUS, menuTree);
        session.setAttribute(SysConstants.SESSION_STATUS, Boolean.TRUE);
        
        
        /********************踢出用户**************************/
        //获取session登录信息。
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session LoginSession : sessions) {
        	Object obj = LoginSession.getAttribute(SysConstants.SESSION_USER);
        	if(obj!=null && (!(obj instanceof Boolean))) {
        		TbUser loginUser = (TbUser)obj;
            	if(loginname.equals(loginUser.getLoginname()) && LoginSession.getId()!=session.getId()) {
            		System.out.println(LoginSession.getId() + loginname + "要踢出！！！！！！");
            		LoginSession.setAttribute(SysConstants.SESSION_MENUS, Boolean.FALSE);
                	LoginSession.setAttribute(SysConstants.SESSION_USER, Boolean.FALSE);
                	LoginSession.setAttribute(SysConstants.SESSION_STATUS, Boolean.FALSE);
            	}
        	}
        }
        
        return "redirect:/page/main.jsp";
    }
    
    
    /**
     * 安全退出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
    	TbOperateLog tbOperateLog = buildOperateLog("退出系统");
    	tbOperateLogService.insert(tbOperateLog);
    	
    	TbUser curUser = (TbUser) session.getAttribute(SysConstants.SESSION_USER);
        TbUser tbUser = tbUserService.selectByKey(curUser.getId());
        
        tbUser.setIslogin(0);
        tbUserService.updateSelective(tbUser);
    	
        try {
            Session LoginSession = sessionDAO.readSession(request.getSession().getId());
            if(LoginSession != null) {
            	LoginSession.removeAttribute(SysConstants.SESSION_MENUS);
            	LoginSession.removeAttribute(SysConstants.SESSION_USER);
            	LoginSession.removeAttribute(SysConstants.SESSION_STATUS);
            }
        } catch (Exception e) {
        	
        }   
        
        return "redirect:/login.jsp";
    }
    
    public TbOperateLog buildOperateLog(String operateContent) {
    	TbOperateLog tbOperateLog = new TbOperateLog();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String curentDateTime = sdf.format(new Date());
    	tbOperateLog.setOperateTime(curentDateTime);
    	
    	// 获取当前登录用户
    	TbUser currentUser = ShiroUtils.getCurUser();   	
    	TbUser tbUser = tbUserService.selectByKey(currentUser.getId());
    	tbOperateLog.setUserName(tbUser.getLoginname());
    	tbOperateLog.setRealName(tbUser.getRealname());
    	tbOperateLog.setResult("0");
    	tbOperateLog.setOperateContent(tbUser.getRealname()+"于["+tbOperateLog.getOperateTime()+"]"+operateContent);
    	
    	InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString(); //获取本机ip  
		    String hostName=addr.getHostName().toString(); //获取本机计算机名称  
		    tbOperateLog.setIp(ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
		
//         System.out.println(ip);
//         System.out.println(hostName);
    	
    	return tbOperateLog;
    } 
    
}
