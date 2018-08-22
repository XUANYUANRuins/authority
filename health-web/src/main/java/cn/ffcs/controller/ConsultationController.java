package cn.ffcs.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ffcs.model.TbBaseInfo;
import cn.ffcs.model.TbCircle;
import cn.ffcs.model.TbHealthInfo;
import cn.ffcs.model.TbReport;
import cn.ffcs.model.TbRole;
import cn.ffcs.model.TbUser;
import cn.ffcs.service.TbBaseInfoService;
import cn.ffcs.service.TbCircleInfoService;
import cn.ffcs.service.TbDictService;
import cn.ffcs.service.TbHealthInfoService;
import cn.ffcs.service.TbReportService;
import cn.ffcs.service.TbRoleService;
import cn.ffcs.service.TbUserService;
import cn.ffcs.thread.MachineImplement;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.HttpClientUtil;
import cn.ffcs.util.SysConstants;

/**
 * 会诊
 * @author huangmengwei
 *
 */
@Controller
@RequestMapping("/consultation")
public class ConsultationController extends BaseController {

//    private static final String http_imgurl = "http://120.24.19.84:8080/health-api/imageUpload";
//    private static final String http_reporturl = "http://120.24.19.84:8080/health-api/reportInfo?ap=web&aid=";
//    private static final String http_down_url = "http://120.24.19.84:8080/health-api/imageDown?ap=web&imageUrl=";
    
    private static final String http_imgurl = "http://117.27.128.186:7001/health-api/imageUpload";
    private static final String http_reporturl = "http://117.27.128.186:7001/health-api/reportInfo?ap=web&aid=";
    private static final String http_down_url = "http://117.27.128.186:7001/health-api/imageDown?ap=web&imageUrl=";
	
	@Autowired
	private TbBaseInfoService tbBaseInfoService;
	@Autowired
	private TbHealthInfoService tbHealthInfoService;
	@Autowired
	private TbReportService tbReportService;
	@Autowired
    private TbDictService tbDictService;
	@Autowired
	private TbUserService tbUserService;
	@Autowired
	private TbRoleService tbRoleService;
	@Autowired
	private TbCircleInfoService tbCircleInfoService;
	@Autowired
    private SessionDAO sessionDAO;
	
  
    /**
     * 解决出现下面的问题:界面上传过来的是字符串类型,当用对象接受时并且是Date类型的数据则会发生以下的错误 Cannot convert value
     * of type [java.lang.String] to required type [java.util.Date]
     * 
     * @param binder
     */
    @InitBinder  
    protected void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
	@RequestMapping(value="/getPage")
	public void getPage(String name, String diabetes, String status,@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.debug("----->>>>>[getPage]:");
		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	String curentDateTime = sdf.format(new Date());
//    	
//		Date start_date = null;
//    	Date end_date = null;
//    	
//    	if(!startDate.equals("")){ 		
//    		try {
//				start_date = sdf.parse(startDate);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}   
//    	}
//    	if(!endDate.equals("")){ 		
//    		try {
//				end_date = sdf.parse(endDate);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}   
//    	}
		
		//使用DataTables的属性接收分页数据
        DataTablePageUtil<TbBaseInfo> dataTable = new DataTablePageUtil<TbBaseInfo>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        
        Map<String, Object> serArgs = new HashMap<String, Object>();
        serArgs.put("name", "%"+name+"%");	//姓名
        serArgs.put("diabetes", diabetes);	// 糖尿病史
        serArgs.put("status", status);	//诊断状态
        
        serArgs.put("start_date", startDate);	//搜索开始时间
        serArgs.put("end_date", endDate);	//搜索结束时间
        
        //还是使用List，方便后期用到
        List<TbBaseInfo> list = tbBaseInfoService.getListBySearch(serArgs);
        //用PageInfo对结果进行包装
        PageInfo<TbBaseInfo> pageInfo = new PageInfo<TbBaseInfo>(list);
        
        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        ControllerUtils.writeJson(dataTable, response);
        
	}
	
	/**
	 * 上传问诊资料
	 * @param tbBaseInfo
	 * @param tbHealthInfo
	 * @param leftImg
	 * @param rightImg
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(TbBaseInfo tbBaseInfo, TbHealthInfo tbHealthInfo,TbCircle tbCircle,
			@RequestParam MultipartFile leftImg, @RequestParam MultipartFile rightImg,
			HttpServletRequest request, HttpServletResponse response){
		// 保存眼底图像
		String timepath = "static/eye_image/" + new Date().getTime();
		String realpath = request.getServletContext().getRealPath("/");
		String rtpath = realpath + timepath;
		
		//System.out.println("left:"+leftImg.getOriginalFilename()+",right:"+rightImg.getOriginalFilename());
		
		boolean left_exists = false;
		boolean right_exists = false;
		
		if(leftImg.getOriginalFilename() == null || leftImg.getOriginalFilename().equals("")){
			left_exists = false;
		}else{
			left_exists = true;
		}	
		
		if(rightImg.getOriginalFilename() == null || rightImg.getOriginalFilename().equals("")){
			right_exists = false;
		}else{
			right_exists = true;
		}
				
		String leftpath = "/left/"+leftImg.getOriginalFilename();
		String rightpath = "/right/"+rightImg.getOriginalFilename();
		
		// 相对路径
		tbHealthInfo.setLeftpic("/"+timepath+leftpath);
		tbHealthInfo.setRightpic("/"+timepath+rightpath);
		
		// 物理位置
		leftpath = rtpath+leftpath;
		rightpath = rtpath+rightpath;
		
		try {
			File leftdir = new File(rtpath+"/left/");
			if(!leftdir.exists()) leftdir.mkdirs();
			File rightdir = new File(rtpath+"/right/");
			if(!rightdir.exists()) rightdir.mkdirs();
			
			// 转存文件
			leftImg.transferTo(new File(leftpath));
			rightImg.transferTo(new File(rightpath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 先保存信息表
		tbBaseInfo.setCreatetime(new Date());
		String createid = request.getParameter("createid");
		tbBaseInfo.setCreateid(createid);
		tbBaseInfoService.insert(tbBaseInfo);
		// 健康表信息
		tbHealthInfo.setBaseinfoid(tbBaseInfo.getId());
		
		// 报告表保存为待处理
		TbReport tbReport = new TbReport();
		tbReport.setStatus(SysConstants.STATUS_SUBMIT); //图片未提交
		tbReport.setCreatetime(new Date());
		
		/**
		 * 获取在线的远程会诊中心(无人)
		 */
		String isNone = request.getParameter("isNone");
		if(isNone.equals("1")) {
			//上传左眼图片
			if(left_exists){
				String leftresult = HttpClientUtil.uploadImag(http_imgurl, leftpath);
				if(StringUtils.isNotBlank(leftresult)) {
					// 机器诊断aid
					JSONObject leftjson = JSONObject.parseObject(leftresult);
					String leftaid = leftjson.getString("aid");
					tbHealthInfo.setLeftaid(leftaid);
				}
			}
			
			//上传右眼图片
			if(right_exists){
				String rightresult = HttpClientUtil.uploadImag(http_imgurl, rightpath);
				if(StringUtils.isNotBlank(rightresult)) {
					// 机器诊断aid
					JSONObject rightjson = JSONObject.parseObject(rightresult);
					String rightaid = rightjson.getString("aid");
					tbHealthInfo.setRightaid(rightaid);
				}
			}
			
			tbReport.setStatus(SysConstants.STATUS_MACHINE); //机器自动诊断中
		}
		//保存健康表
		tbHealthInfoService.insert(tbHealthInfo);
		tbReport.setHealthinfoid(tbHealthInfo.getId());
		tbReportService.insert(tbReport);
		tbCircle.setReportid(tbReport.getId());
		tbCircleInfoService.insert(tbCircle);
		
		MachineImplement mi = new MachineImplement(tbHealthInfo, tbReport, tbReportService, realpath, http_reporturl, http_down_url);
		Thread t = new Thread(mi);
		t.start();
		
		return "redirect:/page/health/homepage/index.jsp";
		
	}
	
	/**
	 * 编辑诊断
	 * @param baseInfoid
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Integer baseInfoid, Model model,
			HttpServletRequest request, HttpServletResponse response){
		
		// 取得基本信息
		TbBaseInfo tbBaseInfo = tbBaseInfoService.selectByKey(baseInfoid);
		TbHealthInfo tbHealthInfo = tbHealthInfoService.selectByBaseinfoid(baseInfoid);
		TbReport tbReport = tbReportService.selectByHealthinfoid(tbHealthInfo.getId());
		TbCircle tbCircle = tbCircleInfoService.selectByReportid(tbReport.getId());
		if(tbCircle!=null) {
			model.addAttribute("tbCircle", tbCircle);
		}
		
		String leftResult =  tbReport.getLeftresult();
		String rightResult =  tbReport.getRightresult();
		
		if(leftResult != null && !leftResult.equals("")){
			leftResult = leftResult.replaceAll("\\[", "");
			leftResult = leftResult.replaceAll("\\]", "");
			leftResult = leftResult.replaceAll("\"", "");
//			leftResult = leftResult.replaceAll("\\\\n", "<br/>");
			
			leftResult = leftResult.replaceAll("\\；\\\\n", "；");
			leftResult = leftResult.replaceAll("\\\\n", "；");
			leftResult = leftResult.replaceAll("\\；\\；", "；");
			leftResult = leftResult.replaceAll("\\,\\；", "；");
		}
		

		if(rightResult != null && !rightResult.equals("")){
			rightResult = rightResult.replaceAll("\\[", "");
			rightResult = rightResult.replaceAll("\\]", "");
			rightResult = rightResult.replaceAll("\"", "");	
//			rightResult = rightResult.replaceAll("\\\\n", "<br/>");
			
			rightResult = rightResult.replaceAll("\\；\\\\n", "；");
			rightResult = rightResult.replaceAll("\\\\n", "；");
			rightResult = rightResult.replaceAll("\\；\\；", "；");
			rightResult = rightResult.replaceAll("\\,\\；", "；");
		}
		TbUser tbUser = new TbUser();
		if(tbBaseInfo.getCreateid()!=null) {
			long l = Long.parseLong(tbBaseInfo.getCreateid());
			tbUser.setId(l);
			tbUser = tbUserService.selectOne(tbUser);
			model.addAttribute("tbUser", tbUser);
		}
		
		tbReport.setLeftresult(leftResult);
		tbReport.setRightresult(rightResult);
		
		model.addAttribute("tbBaseInfo", tbBaseInfo);
		model.addAttribute("tbHealthInfo", tbHealthInfo);

		model.addAttribute("tbReport", tbReport);
		
		//数据字典
		model.addAttribute("feature", tbDictService.selectListByParentCode("feature"));
		model.addAttribute("result", tbDictService.selectListByParentCode("result"));
		model.addAttribute("suggest", tbDictService.selectListByParentCode("suggest"));
		
		return "page/health/report/edit";
	}
	
	/**
	 * 更新
	 * @param tbReport
	 * @param leftImg
	 * @param rightImg
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(TbReport tbReport,@RequestParam Integer isSubmit,@RequestParam String position,@RequestParam Integer tbHealthInfoid,
			@RequestParam MultipartFile leftImg, @RequestParam MultipartFile rightImg,
			HttpServletRequest request, HttpServletResponse response){
		// 保存眼底图像
		String timepath = "static/eye_image/" + new Date().getTime();
		String realpath = request.getServletContext().getRealPath("/");
		String rtpath = realpath + timepath;
		
		boolean left_exists = false;
		boolean right_exists = false;
		
		if(leftImg.getOriginalFilename() == null || leftImg.getOriginalFilename().equals("")){
			left_exists = false;
		}else{
			left_exists = true;
		}	
		
		if(rightImg.getOriginalFilename() == null || rightImg.getOriginalFilename().equals("")){
			right_exists = false;
		}else{
			right_exists = true;
		}		
		
		String leftpath = "/left/"+leftImg.getOriginalFilename();
		String rightpath = "/right/"+rightImg.getOriginalFilename();
		
		//TbReport oldT = tbReportService.selectByKey(tbReport.getId());
		TbHealthInfo tbHealthInfo = tbHealthInfoService.selectByKey(tbHealthInfoid);
		
		if(isSubmit==0) { //报告被退回
			tbReport.setStatus(SysConstants.STATUS_RETURN);
		} else if(isSubmit==1) { //报告被提交
			// 状态更新
			if(position.equals("p05")) {
				if(!leftImg.isEmpty()){
					tbHealthInfo.setLeftpic("/"+timepath+leftpath);
				}
				if(!rightImg.isEmpty()){
					tbHealthInfo.setRightpic("/"+timepath+rightpath);
				}
				
				// 物理位置
				leftpath = rtpath+leftpath;
				rightpath = rtpath+rightpath;
				try {
					File leftdir = new File(rtpath+"/left/");
					if(!leftdir.exists()) leftdir.mkdirs();
					File rightdir = new File(rtpath+"/right/");
					if(!rightdir.exists()) rightdir.mkdirs();
					
					// 转存文件
					leftImg.transferTo(new File(leftpath));
					rightImg.transferTo(new File(rightpath));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				tbReport.setStatus(SysConstants.STATUS_SUBMIT);
				tbReport.setUpdatetime(new Date());
			} else if(position.equals("p06")) {
				double centerX_left1 = Double.parseDouble(request.getParameter("centerX_left1"));
				double centerY_left1 = Double.parseDouble(request.getParameter("centerY_left1"));
				double radius_left1 = Double.parseDouble(request.getParameter("radius_left1"));
				double centerX_right1 = Double.parseDouble(request.getParameter("centerX_right1"));
				double centerY_right1 = Double.parseDouble(request.getParameter("centerY_right1"));
				double radius_right1 = Double.parseDouble(request.getParameter("radius_right1"));
				double centerX_left2 = Double.parseDouble(request.getParameter("centerX_left2"));
				double centerY_left2 = Double.parseDouble(request.getParameter("centerY_left2"));
				double radius_left2 = Double.parseDouble(request.getParameter("radius_left2"));
				double centerX_right2 = Double.parseDouble(request.getParameter("centerX_right2"));
				double centerY_right2 = Double.parseDouble(request.getParameter("centerY_right2"));
				double radius_right2 = Double.parseDouble(request.getParameter("radius_right2"));
				TbCircle tbCircle = tbCircleInfoService.selectByReportid(tbReport.getId());
				tbCircle.setCenterx_left1(centerX_left1);
				tbCircle.setCenterx_left2(centerX_left2);
				tbCircle.setCenterx_right1(centerX_right1);
				tbCircle.setCenterx_right2(centerX_right2);
				tbCircle.setCentery_left1(centerY_left1);
				tbCircle.setCentery_left2(centerY_left2);
				tbCircle.setCentery_right1(centerY_right1);
				tbCircle.setCentery_right2(centerY_right2);
				tbCircle.setRadius_left1(radius_left1);
				tbCircle.setRadius_left2(radius_left2);
				tbCircle.setRadius_right1(radius_right1);
				tbCircle.setRadius_right2(radius_right2);
				
				tbCircleInfoService.update(tbCircle);
				
				tbReport.setStatus(SysConstants.STATUS_MACHINE);
				tbReport.setUpdatetime(new Date());
				
				if(leftImg.isEmpty()) {
					leftpath = realpath + tbHealthInfo.getLeftpic().substring(1, tbHealthInfo.getLeftpic().length());
				}
				if(rightImg.isEmpty()) {
					rightpath = realpath + tbHealthInfo.getRightpic().substring(1, tbHealthInfo.getRightpic().length());
				}
			
				//上传左眼图片
				if(left_exists){
					String leftresult = HttpClientUtil.uploadImag(http_imgurl, leftpath);
					if(StringUtils.isNotBlank(leftresult)) {
						// 机器诊断aid
						JSONObject leftjson = JSONObject.parseObject(leftresult);
						String leftaid = leftjson.getString("aid");
						tbHealthInfo.setLeftaid(leftaid);
					}
				}
				
				//上传右眼图片
				if(right_exists){
					String rightresult = HttpClientUtil.uploadImag(http_imgurl, rightpath);
					if(StringUtils.isNotBlank(rightresult)) {
						// 机器诊断aid
						JSONObject rightjson = JSONObject.parseObject(rightresult);
						String rightaid = rightjson.getString("aid");
						tbHealthInfo.setRightaid(rightaid);
					}
				}
			}
		} else if(isSubmit==2) { //报告被保存
			tbReport.setStatus(SysConstants.STATUS_DOCTOR_ON);
			tbReport.setUpdatetime(new Date());
		} else if(isSubmit==3) { //报告不经过远程会诊中心，机器自动诊断
			if(leftImg.isEmpty()) {
				leftpath = realpath + tbHealthInfo.getLeftpic().substring(1, tbHealthInfo.getLeftpic().length());
			}
			if(rightImg.isEmpty()) {
				rightpath = realpath + tbHealthInfo.getRightpic().substring(1, tbHealthInfo.getRightpic().length());
			}
			//上传左眼图片
			if(left_exists){
				String leftresult = HttpClientUtil.uploadImag(http_imgurl, leftpath);
				if(StringUtils.isNotBlank(leftresult)) {
					// 机器诊断aid
					JSONObject leftjson = JSONObject.parseObject(leftresult);
					String leftaid = leftjson.getString("aid");
					tbHealthInfo.setLeftaid(leftaid);
				}
			}
			
			//上传右眼图片
			if(right_exists){
				String rightresult = HttpClientUtil.uploadImag(http_imgurl, rightpath);
				if(StringUtils.isNotBlank(rightresult)) {
					// 机器诊断aid
					JSONObject rightjson = JSONObject.parseObject(rightresult);
					String rightaid = rightjson.getString("aid");
					tbHealthInfo.setRightaid(rightaid);
				}
			}
			
			tbReport.setStatus(SysConstants.STATUS_MACHINE);
			tbReport.setUpdatetime(new Date());
		}
		tbHealthInfoService.update(tbHealthInfo);
		tbReportService.updateSelective(tbReport);
		
		return "redirect:/page/health/homepage/index.jsp";
	}
	
	/**
	 * 报告
	 * @param baseInfoid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/report")
	public String report(Integer baseInfoid, Model model,
			HttpServletRequest request, HttpServletResponse response){
		
		// 取得基本信息
		TbBaseInfo tbBaseInfo = tbBaseInfoService.selectByKey(baseInfoid);
		TbHealthInfo tbHealthInfo = tbHealthInfoService.selectByBaseinfoid(baseInfoid);
		TbReport tbReport = tbReportService.selectByHealthinfoid(tbHealthInfo.getId());
		
		String leftResult =  tbReport.getLeftresult();
		String rightResult =  tbReport.getRightresult();
		
		if(leftResult != null && !leftResult.equals("")){
			leftResult = leftResult.replaceAll("\\[", "");
			leftResult = leftResult.replaceAll("\\]", "");
			leftResult = leftResult.replaceAll("\"", "");
//			leftResult = leftResult.replaceAll("\\\\n", "<br/>");
			
			leftResult = leftResult.replaceAll("\\；\\\\n", "；");
			leftResult = leftResult.replaceAll("\\\\n", "；");
			leftResult = leftResult.replaceAll("\\；\\；", "；");
			leftResult = leftResult.replaceAll("\\,\\；", "；");
		}
		

		if(rightResult != null && !rightResult.equals("")){
			rightResult = rightResult.replaceAll("\\[", "");
			rightResult = rightResult.replaceAll("\\]", "");
			rightResult = rightResult.replaceAll("\"", "");	
//			rightResult = rightResult.replaceAll("\\\\n", "<br/>");
			
			rightResult = rightResult.replaceAll("\\；\\\\n", "；");
			rightResult = rightResult.replaceAll("\\\\n", "；");
			rightResult = rightResult.replaceAll("\\；\\；", "；");
			rightResult = rightResult.replaceAll("\\,\\；", "；");
		}
		
		tbReport.setLeftresult(leftResult);
		tbReport.setRightresult(rightResult);
		TbCircle tbCircle = tbCircleInfoService.selectByReportid(tbReport.getId());
		model.addAttribute("tbCircle", tbCircle);
		model.addAttribute("tbBaseInfo", tbBaseInfo);
		model.addAttribute("tbHealthInfo", tbHealthInfo);
		model.addAttribute("tbReport", tbReport);
		TbUser tbUser = new TbUser();
		
		if(tbBaseInfo.getCreateid()!=null) {
			long l = Long.parseLong(tbBaseInfo.getCreateid());
			tbUser.setId(l);
			tbUser = tbUserService.selectOne(tbUser);
			model.addAttribute("tbUser", tbUser);
		}
		
		//数据字典
		model.addAttribute("feature", tbDictService.selectListByParentCode("feature"));
		model.addAttribute("result", tbDictService.selectListByParentCode("result"));
		model.addAttribute("suggest", tbDictService.selectListByParentCode("suggest"));
		
		return "page/health/report/index";
	}
	//修改
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	PrintWriter out = response.getWriter();
        JSONObject resultJson = new JSONObject();
        //诊断总人数//复查人数
        List<Map> list = tbBaseInfoService.getListGroupByCardID();
        resultJson.put("allNum", list);
        //已完成诊断
        TbReport tbReport= new TbReport();
        tbReport.setStatus(2);
        int completed = tbReportService.selectCount(tbReport);
        resultJson.put("completed", completed);
        //待处理诊断
        TbReport tbReport1 = new TbReport();
        tbReport1.setStatus(1);
        int treated = tbReportService.selectCount(tbReport1);
        resultJson.put("treated", treated);
        
        
        out.println(resultJson);
        out.flush();
        out.close();
	}
	
	@RequestMapping(value="/getUsersP06", method = RequestMethod.POST)
	public void getUsersP06(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
        JSONObject resultJson = new JSONObject();
		/**
		 * 获取在线的远程会诊中心
		 */
		List<String> list = new ArrayList<String>();
		Collection<Session> sessions = sessionDAO.getActiveSessions();//获取当前已登录的用户session列表
		for(Session loginSession:sessions) {
			Object obj = loginSession.getAttribute(SysConstants.SESSION_USER);
        	if(obj!=null && (!(obj instanceof Boolean))) {
        		TbUser loginUser = (TbUser)obj;
        		if(loginUser!=null) {
    				TbRole tbRole = tbRoleService.selectByKey(Long.parseLong(loginUser.getRoleids()));
    				if(tbRole!=null&&tbRole.getName().contains("远程会诊")) {
    					list.add(loginUser.getLoginname());
    				}
    			}
        	}
		}
		resultJson.put("UsersP06List", list);
		out.println(resultJson);
        out.flush();
        out.close();
	}
}
