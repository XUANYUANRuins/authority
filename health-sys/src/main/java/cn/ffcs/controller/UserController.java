package cn.ffcs.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ffcs.model.TbOperateLog;
import cn.ffcs.model.TbOrganization;
import cn.ffcs.model.TbRole;
import cn.ffcs.model.TbTerminal;
import cn.ffcs.model.TbUser;
import cn.ffcs.pojo.State;
import cn.ffcs.pojo.TreeView;
import cn.ffcs.service.TbOperateLogService;
import cn.ffcs.service.TbOrganizationService;
import cn.ffcs.service.TbRoleService;
import cn.ffcs.service.TbTerminalService;
import cn.ffcs.service.TbUserService;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.MethodUtils;
import cn.ffcs.util.ResultUtils;
import cn.ffcs.util.ShiroUtils;
import cn.ffcs.util.SysConstants;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TbRoleService tbRoleService;
    @Resource
    private TbUserService tbUserService;
    @Resource
    private TbOperateLogService tbOperateLogService;
    @Resource
    private TbOrganizationService tbOrganizationService;
    @Resource
    private TbTerminalService tbTerminalService;
    
    
    /**
     * 分页列表
     * @param loginname
     * @param realname
     * @param startDate
     * @param endDate
     * @param status
     * @param request
     * @param response
     */
    @RequestMapping("/getPage")
    @ResponseBody
    public void getPage(@RequestParam(required = false) String loginname, @RequestParam(required = false) String realname, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, @RequestParam(required = false) String status, 
    		@RequestParam(required = false) String phone,@RequestParam(required = false) String orgId,HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("----->>>>>[getPage]:");
   
    	logger.debug("loginname:"+loginname+",realname:"+realname+",startDate:"+startDate+",endDate:"+endDate+",status:"+status+",phone:"+phone+",orgId:"+orgId);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String curentDateTime = sdf.format(new Date());
    	
    	TbUser tbUser = new TbUser();
    	tbUser.setLoginname(loginname);
    	tbUser.setRealname(realname);
    	if(!status.equals("")){
    		tbUser.setStatus(Integer.parseInt(status));
    	}
    	tbUser.setPhone(phone);	
    	tbUser.setOrgId(orgId);
    	
    	
    	Date start_date = null;
    	Date end_date = null;
    	
    	if(!startDate.equals("")){ 		
    		try {
				start_date = sdf.parse(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}   
    	}
    	if(!endDate.equals("")){ 		
    		try {
				end_date = sdf.parse(endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}   
    	}
    	
    	//logger.debug("start_date:"+start_date+",end_date:"+end_date);
    	
        //使用DataTables的属性接收分页数据
        DataTablePageUtil<TbUser> dataTable = new DataTablePageUtil<TbUser>(request);
        
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<TbUser> list = tbUserService.selectListByParam(tbUser, start_date, end_date);
       
        //System.out.println("list:"+list);
        
        List<TbUser> list_new = new ArrayList<TbUser>();
        
        if(list.size()>0){
       	 for(int i=0; i<list.size(); i++){
       		TbUser user = list.get(i);
       		 String org_id = user.getOrgId();
       		 TbOrganization tbOrganization = tbOrganizationService.selectByOrgId(org_id);
       		 if(tbOrganization != null){
       			 String orgName = tbOrganization.getOrgName();
       			 user.setOrgId(orgName);
       		 }else{
       			user.setOrgId("");
       		 }
       		 list_new.add(user);
            }
       } 
        
        //用PageInfo对结果进行包装
        PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(list_new);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        ControllerUtils.writeJson(dataTable, response);
        
    }
    
//    /**
//     * 分页列表
//     * @param tbUser
//     * @param startDate
//     * @param endDate
//     * @param request
//     * @param response
//     */
//    @RequestMapping("/getPage")
//    public void getPage(TbUser tbUser, Date startDate, Date endDate, 
//    				HttpServletRequest request, HttpServletResponse response) {
//    	logger.debug("----->>>>>[getPage]:");
//
//        //使用DataTables的属性接收分页数据
//        DataTablePageUtil<TbUser> dataTable = new DataTablePageUtil<TbUser>(request);
//        
//        //开始分页：PageHelper会处理接下来的第一个查询
//        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
//        //还是使用List，方便后期用到
//        List<TbUser> list = tbUserService.selectListByParam(tbUser, startDate, endDate);
//        //用PageInfo对结果进行包装
//        PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(list);
//
//        //封装数据给DataTables
//        dataTable.setDraw(dataTable.getDraw());
//        dataTable.setData(pageInfo.getList());
//        dataTable.setRecordsTotal((int) pageInfo.getTotal());
//        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());
//
//        ControllerUtils.writeJson(dataTable, response);
//        
//    }
    
    /**
     * 编辑
     * @param id
     * @param response
     */
    @RequestMapping("/edit")
    public void edit(Long id, HttpServletResponse response) {
    	ControllerUtils.writeJson(tbUserService.selectByKey(id), response);
    }
    @RequestMapping("/update")
    public void update(TbUser tbUser, HttpServletResponse response) {
    	logger.debug("----->>>>>[update]:");
    	// 创建时间
    	if ( tbUser.getCreatetime()==null ){
    		tbUser.setCreatetime(new Date());
    	}
    	
    	TbUser retUser = new TbUser();
    	retUser = tbUserService.selectByLoginname(tbUser.getLoginname());
    	Map<String, Object> map = new HashMap<String, Object>();
    	if( tbUser.getId() != null){
    		if(retUser==null || retUser.getId()==tbUser.getId()) {
    			
    			map.put("isRepeat", 0);
    			// 更新时间
        		tbUser.setUpdatetime(new Date());
        		
        		tbUserService.updateSelective(tbUser);
        		
        		TbOperateLog tbOperateLog = buildOperateLog("更新了一位用户["+tbUser.getRealname()+"]");
            	tbOperateLogService.insert(tbOperateLog);
    		} else {
    			map.put("isRepeat", 1);//判断Loginname是否重复
    		}
    		
    	} else {
    		if(retUser!=null) {
    			map.put("isRepeat", 1);
    		} else {
    			map.put("isRepeat", 0);
    			
        		// 初始化密码 123456
        		if( StringUtils.isBlank(tbUser.getPassword())){
        			tbUser.setPassword(MethodUtils.md5("123456"));
        		}
        		
        		tbUserService.insert(tbUser);
        		
        		TbOperateLog tbOperateLog = buildOperateLog("新增了一位用户["+tbUser.getRealname()+"]");
            	tbOperateLogService.insert(tbOperateLog);
    		}
    	}
    	ControllerUtils.writeJson(map, response);
    	
    	ResultUtils.renderSuccess("success", response);
    }
    
    
    /**
     * 删除
     * @param id
     * @param response
     */
    @RequestMapping("/delete")
    public void delete(Long id, HttpServletResponse response){
    	logger.debug("----->>>>>[delete]:");
    	
    	TbUser tbUser = tbUserService.selectByKey(id);
    	
    	if( tbUserService.delete(id) ){
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一位用户["+tbUser.getRealname()+"]");
        	tbOperateLogService.insert(tbOperateLog);
        	
    		ResultUtils.renderSuccess("success", response);
    	} else {
    		ResultUtils.renderFail("fail", response);
    	}
    	 
        
    }
    
    /**
     * 批量删除
     * @param ids
     * @param response
     */
    @RequestMapping("/batchDelete")
    public void batchDelete(@RequestParam("ids[]")Long[] ids, HttpServletResponse response){
    	logger.debug("----->>>>>[batchDelete]:");
    	
    	for(Long id : ids){
    		TbUser tbUser = tbUserService.selectByKey(id);
    		
    		tbUserService.delete(id);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一位用户["+tbUser.getRealname()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    	}
    	
    	ResultUtils.renderSuccess("success", response);
    }
    
    /**
     * 修改密码
     * @param password
     * @param newPassword
     * @param response
     */
    @RequestMapping("/updatePassword")
    public void updatePassword(String password, String newPassword,
	        HttpServletResponse response){
    	// 获取当前登录用户
    	TbUser currentUser = ShiroUtils.getCurUser();   	
    	TbUser tbUser = tbUserService.selectByKey(currentUser.getId());
	    if(tbUser.getPassword().equals(MethodUtils.md5(password))){
	    	tbUser.setPassword(MethodUtils.md5(newPassword));
	    	tbUserService.updateSelective(tbUser);
	    	
	    	TbOperateLog tbOperateLog = buildOperateLog("修改了登录密码");
        	tbOperateLogService.insert(tbOperateLog);
        	
	        ResultUtils.renderSuccess("success", response);
	    } else {
	        ResultUtils.renderFail("fail", response);
	    }
    	
    }
    
    /**
     * 批量停用
     * @param ids
     * @param response
     */
    @RequestMapping("/batchOff")
    public void batchOff(@RequestParam("ids[]")Long[] ids, HttpServletResponse response){
    	logger.debug("----->>>>>[batchOff]:");
    	
    	for(Long id : ids){
    		TbUser tbUser = tbUserService.selectByKey(id);
    		tbUser.setStatus(SysConstants.OFF);
    		tbUserService.updateSelective(tbUser);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("停用了一位用户["+tbUser.getRealname()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    	}
    	
    	ResultUtils.renderSuccess("success", response);
    }
    
    /**
     * 获取树数据
     * 
     * @param ids
     * @param response
     */
    @RequestMapping("/getRoleTree")
    public void getRoleTree(String ids, HttpServletResponse response){
    	TbRole tbRole = new TbRole();
    	tbRole.setStatus(SysConstants.ON);
    	
    	List<TbRole> roles = tbRoleService.selectListByParam(tbRole);
    	List<TreeView> treeViews = new ArrayList<TreeView>();
    	
    	//获取所属角色
    	String[] rIds = new String[1];
		if( StringUtils.isNotBlank(ids) ){
			if(ids.indexOf(",")>-1){
				rIds = ids.split(",");
			} else {
				rIds[0] = ids;
			}
		} 
    	
    	// 转化为树结构
    	for(TbRole role : roles){
    		String rid = role.getId() + "";
    		
    		TreeView tree = new TreeView();
    		tree.setId(role.getId());
    		tree.setText(role.getName());
    		tree.setStatus(role.getStatus());
    		// 存在所属角色
    		if( Arrays.asList(rIds).contains(rid) ){
    			State state = new State();
            	state.setChecked(true);
            	tree.setState(state);
    		}
    		treeViews.add(tree);
    	}
    	
    	ControllerUtils.writeJson(treeViews, response);
    }
    
    @RequestMapping("/getTerminalTree")
    public void getTerminalTree(String ids, String orgId, HttpServletResponse response){
    	TbTerminal tbTerminal = new TbTerminal();
    	tbTerminal.setOrgId(orgId);
    	
    	List<TbTerminal> terminals = tbTerminalService.selectListByParam(tbTerminal);
    	List<TreeView> treeViews = new ArrayList<TreeView>();
    	
    	//获取操作设备
    	String[] rIds = new String[1];
		if( StringUtils.isNotBlank(ids) ){
			if(ids.indexOf(",")>-1){
				rIds = ids.split(",");
			} else {
				rIds[0] = ids;
			}
		} 
    	
    	// 转化为树结构
    	for(TbTerminal terminal : terminals){
    		String rid = terminal.getId() + "";
    		
    		TreeView tree = new TreeView();
    		tree.setId(terminal.getId());
    		tree.setText(terminal.getCameraBrand()+" "+terminal.getCameraModel());
    		
    		//tree.setStatus(role.getStatus());
    		
    		// 存在所属角色
    		if( Arrays.asList(rIds).contains(rid) ){
    			State state = new State();
            	state.setChecked(true);
            	tree.setState(state);
    		}
    		treeViews.add(tree);
    	}
    	
    	ControllerUtils.writeJson(treeViews, response);
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
    
    /**
     * 获取当前登录用户的机构id
     * @param response
     */
    @RequestMapping("/getLoginOrgId")
    public void getLoginOrgId(HttpServletResponse response) {
    	TbUser currentUser = ShiroUtils.getCurUser();   	
    	TbUser tbUser = tbUserService.selectByKey(currentUser.getId());
    	//System.out.println("tbUser:"+tbUser);
    	ControllerUtils.writeJson(tbUser.getOrgId(), response);
    }
}
