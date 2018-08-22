package cn.ffcs.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ffcs.model.TbMenu;
import cn.ffcs.model.TbOperateLog;
import cn.ffcs.model.TbUser;
import cn.ffcs.pojo.MenuGrid;
import cn.ffcs.service.TbMenuService;
import cn.ffcs.service.TbOperateLogService;
import cn.ffcs.service.TbUserService;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.ResultUtils;
import cn.ffcs.util.ShiroUtils;
import cn.ffcs.util.SysConstants;
import cn.ffcs.util.TreeGenera;


@Controller
@RequestMapping("/menu")
public class MenuController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private TbMenuService tbMenuService;
    @Resource
    private TbUserService tbUserService;
    @Resource
    private TbOperateLogService tbOperateLogService;
    
    
    /**
     * 分页列表
     * @param menu
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getPage")
    public void getPage(TbMenu tbMenu, 
    				HttpServletRequest request, HttpServletResponse response) {
    	
    	logger.debug("----->>>>>[getPage]:");

        //使用DataTables的属性接收分页数据
        DataTablePageUtil<TbMenu> dataTable = new DataTablePageUtil<TbMenu>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<TbMenu> list = tbMenuService.selectListByParam(tbMenu);
        //用PageInfo对结果进行包装
        PageInfo<TbMenu> pageInfo = new PageInfo<TbMenu>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        ControllerUtils.writeJson(dataTable, response);
        
    }
    
    /**
     * 获取父菜单列表
     * @param response
     */
    @RequestMapping("/getParentMenus")
    public void getParentMenus(HttpServletResponse response) {
    	// 菜单列表， 只可变更父菜单
    	TbMenu param = new TbMenu();
    	param.setParentid(0L);
    	param.setStatus(SysConstants.ON);//启用
    	List<TbMenu> pMenus = tbMenuService.selectListByParam(param);
    	
    	ControllerUtils.writeJson(pMenus, response);
    	
    }
    
    /**
     * 编辑
     * @param id
     * @param response
     */
    @RequestMapping("/edit")
    public void edit(Long id, HttpServletResponse response) {
    	ControllerUtils.writeJson(tbMenuService.selectByKey(id), response);
    }
    @RequestMapping("/update")
    public void update(TbMenu tbMenu, HttpServletResponse response) {
    	logger.debug("----->>>>>[update]:");
    	
    	// 父菜单，默认为0
    	if( tbMenu.getParentid()==null ){
    		tbMenu.setParentid(0L);
    	}
    	// 创建时间
    	if ( tbMenu.getCreatetime()==null ){
    		tbMenu.setCreatetime(new Date());
    	}
    	
    	if( tbMenu.getId() != null){
    		// 更新时间
    		tbMenu.setUpdatetime(new Date());
    		
    		tbMenuService.updateSelective(tbMenu);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("更新了一个菜单["+tbMenu.getName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    	} else {
    		tbMenuService.insert(tbMenu);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("新增了一个菜单["+tbMenu.getName()+"]");
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
    	
    	TbMenu tbMenu = tbMenuService.selectByKey(id);
    	if( tbMenuService.delete(id) ){
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一个菜单["+tbMenu.getName()+"]");
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
    		TbMenu tbMenu = tbMenuService.selectByKey(id);
    		
    		tbMenuService.delete(id);
    		
    		TbOperateLog tbOperateLog = buildOperateLog("删除了一个菜单["+tbMenu.getName()+"]");
        	tbOperateLogService.insert(tbOperateLog);
    	}
    	
    	ResultUtils.renderSuccess("success", response);
    }
    
    
    /**
     * 树形列表
     * @param tbMenu
     * @param request
     * @param response
     */
    @RequestMapping("/getTreeGrid")
    public void getTreeGrid(TbMenu tbMenu, 
    				HttpServletRequest request, HttpServletResponse response) {
    	
    	List<MenuGrid> gridList = new ArrayList<MenuGrid>();
    	// 获取list
    	List<TbMenu> list = tbMenuService.selectListByParam(tbMenu);
    	// 转化
    	for(TbMenu t : list){
    		MenuGrid m = new MenuGrid();
    		// 节点
    		m.setParent(t.getParentid() + "");
    		m.setExpanded(true);
    		
    		// 属性
    		m.setId(t.getId());
    		m.setParentid(t.getParentid());
    		m.setName(t.getName());
    		m.setUrl(t.getUrl());
    		m.setIcon(t.getIcon());
    		m.setStatus(t.getStatus());
    		m.setUpdatetime(t.getUpdatetime());
    		
    		gridList.add(m);
    	}
    	// 排序
    	TreeGenera<MenuGrid> tg = new TreeGenera<>(gridList);
    	gridList = tg.startSorting();
    	
    	ControllerUtils.writeJson(gridList, response);
    	
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
