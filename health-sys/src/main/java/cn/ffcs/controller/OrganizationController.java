package cn.ffcs.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.ffcs.model.TbUser;
import cn.ffcs.service.TbOperateLogService;
import cn.ffcs.service.TbOrganizationService;
import cn.ffcs.service.TbUserService;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.ResultUtils;
import cn.ffcs.util.ShiroUtils;


@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TbOrganizationService tbOrganizationService;
    
    @Resource
    private TbOperateLogService tbOperateLogService;
    
    @Resource
    private TbUserService tbUserService;
    
    /**
     * 分页列表
     * @param orgName
     * @param orgId
     * @param orgPhone
     * @param linkman
     * @param startDate
     * @param endDate
     * @param status
     * @param request
     * @param response
     */
    @RequestMapping("/getPage")
    @ResponseBody
    public void getPage(@RequestParam(required = false) String orgName, @RequestParam(required = false) String orgId,@RequestParam(required = false) String orgPhone, @RequestParam(required = false) String linkman, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, @RequestParam(required = false) String status, 
    				HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("----->>>>>[getPage]:");
   
    	logger.debug("orgName:"+orgName+",orgId:"+orgId+",orgPhone:"+orgPhone+",linkman:"+linkman+",startDate:"+startDate+",endDate:"+endDate+",status:"+status);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String curentDateTime = sdf.format(new Date());
    	
    	TbOrganization tbOrganization = new TbOrganization();
    	tbOrganization.setOrgName(orgName);
    	tbOrganization.setOrgId(orgId);
    	tbOrganization.setOrgPhone(orgPhone);
    	tbOrganization.setLinkman(linkman);
    	
    	if(!status.equals("")){
    		tbOrganization.setStatus(Integer.parseInt(status));
    	}
    		
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
        DataTablePageUtil<TbOrganization> dataTable = new DataTablePageUtil<TbOrganization>(request);
        
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<TbOrganization> list = tbOrganizationService.selectListByParam(tbOrganization, start_date, end_date);
               
        //用PageInfo对结果进行包装
        PageInfo<TbOrganization> pageInfo = new PageInfo<TbOrganization>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        ControllerUtils.writeJson(dataTable, response);
        
    }
    
    /**
     * 编辑
     * @param id
     * @param response
     */
    @RequestMapping("/edit")
    public void edit(Long id, HttpServletResponse response) {
    	ControllerUtils.writeJson(tbOrganizationService.selectByKey(id), response);
    }
    @RequestMapping("/update")
    public void update(TbOrganization tbOrganization, HttpServletResponse response) {
    	logger.debug("----->>>>>[update]:");
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 创建时间
    	if ( tbOrganization.getId()==null ){
        	tbOrganization.setInsertDate(new Date());
        	tbOrganizationService.insert(tbOrganization);
        	
        	TbOperateLog tbOperateLog = buildOperateLog("新增了一个机构["+tbOrganization.getOrgName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
        	
        	map.put("operation", "add");//新增
    	}else{
    		tbOrganization.setUpdateDate(new Date());
    		tbOrganizationService.updateSelective(tbOrganization);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("更新了一个机构["+tbOrganization.getOrgName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    		
    		map.put("operation", "update");//编辑
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
    	
    	TbOrganization tbOrganization = tbOrganizationService.selectByKey(id);
    	
    	if( tbOrganizationService.delete(id) ){
    		
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一个机构["+tbOrganization.getOrgName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
        	
    		ResultUtils.renderSuccess("success", response);
    	} else {
    		ResultUtils.renderFail("fail", response);
    	}
    }
    
    /**
     * 根据机构状态取得机构列表
     * @param status
     * @param response
     */
    @RequestMapping("getListByStatus")
    public void getListByStatus(String status, HttpServletResponse response){
    	ControllerUtils.writeJson(tbOrganizationService.selectListByStatus(status), response);
    }
    
//    /**
//     * 修改密码
//     * @param password
//     * @param newPassword
//     * @param response
//     */
//    @RequestMapping("/updatePassword")
//    public void updatePassword(String password, String newPassword,
//	        HttpServletResponse response){
//    	// 获取当前登录用户
//    	TbUser currentUser = ShiroUtils.getCurUser();
//    	TbUser tbUser = tbUserService.selectByKey(currentUser.getId());
//	    if(tbUser.getPassword().equals(MethodUtils.md5(password))){
//	    	tbUser.setPassword(MethodUtils.md5(newPassword));
//	    	tbUserService.updateSelective(tbUser);
//	        ResultUtils.renderSuccess("success", response);
//	    } else {
//	        ResultUtils.renderFail("fail", response);
//	    }
//    	
//    }
    
    /**
     * 批量删除
     * @param ids
     * @param response
     */
    @RequestMapping("/batchDelete")
    public void batchDelete(@RequestParam("ids[]")Long[] ids, HttpServletResponse response){
    	logger.debug("----->>>>>[batchDelete]:");
    	
    	for(Long id : ids){
    		TbOrganization tbOrganization = tbOrganizationService.selectByKey(id);
    		
    		tbOrganizationService.delete(id);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一个机构["+tbOrganization.getOrgName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    	}
    	
    	ResultUtils.renderSuccess("success", response);
    }
    
//    /**
//     * 获取树数据
//     * 
//     * @param ids
//     * @param response
//     */
//    @RequestMapping("/getTree")
//    public void getTree(String ids, HttpServletResponse response){
//    	TbRole tbRole = new TbRole();
//    	tbRole.setStatus(SysConstants.ON);
//    	
//    	List<TbRole> roles = tbRoleService.selectListByParam(tbRole);
//    	List<TreeView> treeViews = new ArrayList<TreeView>();
//    	
//    	//获取所属角色
//    	String[] rIds = new String[1];
//		if( StringUtils.isNotBlank(ids) ){
//			if(ids.indexOf(",")>-1){
//				rIds = ids.split(",");
//			} else {
//				rIds[0] = ids;
//			}
//		} 
//    	
//    	// 转化为树结构
//    	for(TbRole role : roles){
//    		String rid = role.getId() + "";
//    		
//    		TreeView tree = new TreeView();
//    		tree.setId(role.getId());
//    		tree.setText(role.getName());
//    		tree.setStatus(role.getStatus());
//    		// 存在所属角色
//    		if( Arrays.asList(rIds).contains(rid) ){
//    			State state = new State();
//            	state.setChecked(true);
//            	tree.setState(state);
//    		}
//    		treeViews.add(tree);
//    	}
//    	
//    	ControllerUtils.writeJson(treeViews, response);
//    }
    
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
