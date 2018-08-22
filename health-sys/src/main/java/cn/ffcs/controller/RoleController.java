package cn.ffcs.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ffcs.model.TbMenu;
import cn.ffcs.model.TbOperateLog;
import cn.ffcs.model.TbRole;
import cn.ffcs.model.TbUser;
import cn.ffcs.pojo.State;
import cn.ffcs.pojo.TreeView;
import cn.ffcs.service.TbMenuService;
import cn.ffcs.service.TbOperateLogService;
import cn.ffcs.service.TbRoleService;
import cn.ffcs.service.TbUserService;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.ResultUtils;
import cn.ffcs.util.ShiroUtils;
import cn.ffcs.util.SysConstants;
import cn.ffcs.util.TreeUtils;


@Controller
@RequestMapping("/role")
public class RoleController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private TbRoleService tbRoleService;
    @Resource
    private TbMenuService tbMenuService;
    @Resource
    private TbUserService tbUserService;
    @Resource
    private TbOperateLogService tbOperateLogService;
    
    /**
     * 分页列表
     * @param role
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getPage")
    public void getPage(TbRole tbRole, 
    				HttpServletRequest request, HttpServletResponse response) {
    	
    	logger.debug("----->>>>>[getPage]:");

        //使用DataTables的属性接收分页数据
        DataTablePageUtil<TbRole> dataTable = new DataTablePageUtil<TbRole>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<TbRole> list = tbRoleService.selectListByParam(tbRole);
        //用PageInfo对结果进行包装
        PageInfo<TbRole> pageInfo = new PageInfo<TbRole>(list);

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
    	ControllerUtils.writeJson(tbRoleService.selectByKey(id), response);
    }
    @RequestMapping("/update")
    public void update(TbRole tbRole, HttpServletResponse response) {
    	logger.debug("----->>>>>[update]:");
    	
    	// 创建时间
    	if ( tbRole.getCreatetime()==null ){
    		tbRole.setCreatetime(new Date());
    	}
    	
    	if( tbRole.getId() != null){
    		// 更新时间
    		tbRole.setUpdatetime(new Date());
    		
    		tbRoleService.updateSelective(tbRole);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("编辑了一个角色["+tbRole.getName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    	} else {
    		tbRoleService.insert(tbRole);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("新增了一个角色["+tbRole.getName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    	}
    		
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
    	
		TbRole tbRole = tbRoleService.selectByKey(id);
		
    	if( tbRoleService.delete(id) ){
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一个角色["+tbRole.getName()+"]");
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
    		TbRole tbRole = tbRoleService.selectByKey(id);
    		
    		tbRoleService.delete(id);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一个角色["+tbRole.getName()+"]");
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
    @RequestMapping("/getTree")
    public void getTree(String ids, HttpServletResponse response){
    	TbMenu tbMenu = new TbMenu();
    	tbMenu.setStatus(SysConstants.ON);
    	
    	List<TbMenu> menus = tbMenuService.selectListByParam(tbMenu);
    	List<TreeView> treeViews = new ArrayList<TreeView>();
    	
    	//获取角色菜单
    	String[] mIds = new String[1];
		if( StringUtils.isNotBlank(ids) ){
			if(ids.indexOf(",")>-1){
				mIds = ids.split(",");
			} else {
				mIds[0] = ids;
			}
		} 
    	
    	// 转化为树结构
    	for(TbMenu menu : menus){
			String mid = menu.getId() + "";
			
			TreeView tree = new TreeView();
			tree.setId(menu.getId());
            tree.setText(menu.getName());
            tree.setIcon(menu.getIcon());
            tree.setParentId(menu.getParentid());
            tree.setUrl(menu.getUrl());
            tree.setStatus(menu.getStatus());
            // 存在所属菜单
			if( Arrays.asList(mIds).contains(mid) ){
				State state = new State();
            	state.setChecked(true);
            	tree.setState(state);
			}
			treeViews.add(tree);
		}
    	
    	ControllerUtils.writeJson(TreeUtils.formatNode(treeViews), response);
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
