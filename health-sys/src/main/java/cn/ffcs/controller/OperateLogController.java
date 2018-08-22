package cn.ffcs.controller;

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
import cn.ffcs.service.TbOperateLogService;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.ResultUtils;


@Controller
@RequestMapping("/log")
public class OperateLogController extends BaseController{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TbOperateLogService tbOperateLogService;
    
    /**
     * 分页列表
     * @param userName
     * @param realName
     * @param ip
     * @param startDate
     * @param endDate
     * @param request
     * @param response
     */
    @RequestMapping("/getPage")
    @ResponseBody
    public void getPage(@RequestParam(required = false) String userName, @RequestParam(required = false) String realName, @RequestParam(required = false) String ip, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
    				HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("----->>>>>[getPage]:");
   
    	//logger.debug("userName:"+userName+",realName:"+realName+",ip:"+ip+",startDate:"+startDate+",endDate:"+endDate);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String curentDateTime = sdf.format(new Date());
    	
    	TbOperateLog tbOperateLog = new TbOperateLog();
    	tbOperateLog.setUserName(userName);
    	tbOperateLog.setRealName(realName);
    	tbOperateLog.setIp(ip);
    		
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
    	
    	//默认获取当天的日志列表
    	if(startDate.equals("") && endDate.equals("")){
    		try {
				start_date = sdf.parse(curentDateTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	//logger.debug("start_date:"+start_date+",end_date:"+end_date);
    	
        //使用DataTables的属性接收分页数据
        DataTablePageUtil<TbOperateLog> dataTable = new DataTablePageUtil<TbOperateLog>(request);
        
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<TbOperateLog> list = tbOperateLogService.selectListByParam(tbOperateLog, start_date, end_date);
               
        //用PageInfo对结果进行包装
        PageInfo<TbOperateLog> pageInfo = new PageInfo<TbOperateLog>(list);

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
     * @param logId
     * @param response
     */
    @RequestMapping("/edit")
    public void edit(Long logId, HttpServletResponse response) {
    	ControllerUtils.writeJson(tbOperateLogService.selectByKey(logId), response);
    }
    
    @RequestMapping("/update")
    public void update(TbOperateLog tbOperateLog, HttpServletResponse response) {
    	logger.debug("----->>>>>[update]:");
    	Map<String, Object> map = new HashMap<String, Object>();
    	
		// 创建时间
    	if ( tbOperateLog.getLogId()==null ){
    		tbOperateLogService.insert(tbOperateLog);
        	
        	map.put("operation", "add");//新增
    	}else{
    		tbOperateLogService.updateSelective(tbOperateLog);
    		
    		map.put("operation", "update");//编辑
    	}
    	
    	ControllerUtils.writeJson(map, response);
    	
    	ResultUtils.renderSuccess("success", response);
    }  
    
    /**
     * 删除
     * @param logId
     * @param response
     */
    @RequestMapping("/delete")
    public void delete(Long logId, HttpServletResponse response){
    	logger.debug("----->>>>>[delete]:");
    	
    	if( tbOperateLogService.delete(logId) ){
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
    		tbOperateLogService.delete(id);
    	}
    	
    	ResultUtils.renderSuccess("success", response);
    }  
}
