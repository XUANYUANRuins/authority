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
import cn.ffcs.service.TbUserService;
import cn.ffcs.service.TbOperateLogService;
import cn.ffcs.service.TbOrganizationService;
import cn.ffcs.service.TbRoleService;
import cn.ffcs.service.TbTerminalService;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.MethodUtils;
import cn.ffcs.util.ResultUtils;
import cn.ffcs.util.ShiroUtils;
import cn.ffcs.util.SysConstants;


@Controller
@RequestMapping("/terminal")
public class TerminalController extends BaseController{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TbTerminalService tbTerminalService;
    
    @Resource
    private TbOrganizationService tbOrganizationService;
    
    @Resource
    private TbOperateLogService tbOperateLogService;
    
    @Resource
    private TbUserService tbUserService;
    
    /**
     * 分页列表
     * @param cameraBrand
     * @param cameraModel
     * @param cameraProducer
     * @param orgId
     * @param centralResolution
     * @param ccdResolution
     * @param produceStartDate
     * @param produceEndDate
     * @param buyStartDate
     * @param buyEndDate
     * @param request
     * @param response
     */
    @RequestMapping("/getPage")
    @ResponseBody
    public void getPage(@RequestParam(required = false) String cameraBrand, @RequestParam(required = false) String cameraModel,@RequestParam(required = false) String cameraProducer, @RequestParam(required = false) String orgId, @RequestParam(required = false) String centralResolution, @RequestParam(required = false) String ccdResolution, @RequestParam(required = false) String produceStartDate, 
    		@RequestParam(required = false) String produceEndDate, @RequestParam(required = false) String buyStartDate,@RequestParam(required = false) String buyEndDate, HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("----->>>>>[getPage]:");
   
    	logger.debug("cameraBrand:"+cameraBrand+",cameraModel:"+cameraModel+",cameraProducer:"+cameraProducer+",orgId:"+orgId+",centralResolution:"+centralResolution+",ccdResolution:"+ccdResolution+",produceStartDate:"+produceStartDate+",produceEndDate:"+produceEndDate+",buyStartDate:"+buyStartDate+",buyEndDate:"+buyEndDate);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String curentDateTime = sdf.format(new Date());
    	
    	TbTerminal tbTerminal = new TbTerminal();
    	tbTerminal.setCameraBrand(cameraBrand);
    	tbTerminal.setCameraModel(cameraModel);
    	tbTerminal.setCameraProducer(cameraProducer);
    	tbTerminal.setOrgId(orgId);
    	
    	if(!centralResolution.equals("")){
    		tbTerminal.setCentralResolution(Integer.parseInt(centralResolution));
    	}
    	
    	if(!ccdResolution.equals("")){
    		tbTerminal.setCcdResolution(Integer.parseInt(ccdResolution));
    	}
    		
    	Date produce_start_date = null;
    	Date produce_end_date = null;
    	Date buy_start_date = null;
    	Date buy_end_date = null;
    	
    	if(!produceStartDate.equals("")){ 		
    		try {
    			produce_start_date = sdf.parse(produceStartDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}   
    	}
    	if(!produceEndDate.equals("")){ 		
    		try {
    			produce_end_date = sdf.parse(produceEndDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}   
    	}
    	
    	if(!buyStartDate.equals("")){ 		
    		try {
    			buy_start_date = sdf.parse(buyStartDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}   
    	}
    	if(!buyEndDate.equals("")){ 		
    		try {
    			buy_end_date = sdf.parse(buyEndDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}   
    	}
    	    	
        //使用DataTables的属性接收分页数据
        DataTablePageUtil<TbTerminal> dataTable = new DataTablePageUtil<TbTerminal>(request);
        
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<TbTerminal> list = tbTerminalService.selectListByParam(tbTerminal, produce_start_date, produce_end_date, buy_start_date, buy_end_date);
        List<TbTerminal> list_new = new ArrayList<TbTerminal>();
        if(list.size()>0){
        	 for(int i=0; i<list.size(); i++){
        		 TbTerminal terminal = list.get(i);
        		 String org_id = terminal.getOrgId();
        		 TbOrganization tbOrganization = tbOrganizationService.selectByOrgId(org_id);
        		 if(tbOrganization != null){
        			 String orgName = tbOrganization.getOrgName();
        			 terminal.setOrgId(orgName);
        		 }else{
        			 terminal.setOrgId("");
        		 }
        		 list_new.add(terminal);
             }
        }     
        //用PageInfo对结果进行包装
        PageInfo<TbTerminal> pageInfo = new PageInfo<TbTerminal>(list_new);

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
    	ControllerUtils.writeJson(tbTerminalService.selectByKey(id), response);
    }
    @RequestMapping("/update")
    public void update(TbTerminal tbTerminal,HttpServletResponse response) {
    	logger.debug("----->>>>>[update]:");
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 创建时间
    	if ( tbTerminal.getId()==null ){
    		tbTerminalService.insert(tbTerminal);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("新增了一台设备["+tbTerminal.getCameraBrand()+" "+tbTerminal.getCameraModel()+"]");
        	tbOperateLogService.insert(tbOperateLog);
        	
        	map.put("operation", "add");//新增
    	}else{
    		tbTerminalService.updateSelective(tbTerminal);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("更新了一台设备["+tbTerminal.getCameraBrand()+" "+tbTerminal.getCameraModel()+"]");
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
    	
    	TbTerminal tbTerminal = tbTerminalService.selectByKey(id);
    	
    	if( tbTerminalService.delete(id) ){
    		
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一台设备["+tbTerminal.getCameraBrand()+" "+tbTerminal.getCameraModel()+"]");
        	tbOperateLogService.insert(tbOperateLog);
        	
    		ResultUtils.renderSuccess("success", response);
    	} else {
    		ResultUtils.renderFail("fail", response);
    	}
    	 
        
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
    		TbTerminal tbTerminal = tbTerminalService.selectByKey(id);
    		
    		tbTerminalService.delete(id);
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一台设备["+tbTerminal.getCameraBrand()+" "+tbTerminal.getCameraModel()+"]");
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
