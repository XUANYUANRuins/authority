package cn.ffcs.thread;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.ffcs.model.TbHealthInfo;
import cn.ffcs.model.TbReport;
import cn.ffcs.service.TbHealthInfoService;
import cn.ffcs.service.TbReportService;
import cn.ffcs.util.ApplicationContextUtil;
import cn.ffcs.util.SysConstants;
import tk.mybatis.mapper.entity.Example;

@Component
public class ReportTimerTask {

	protected static Logger logger = LoggerFactory.getLogger(ReportTimerTask.class);

	//private static final String http_reporturl = "http://120.24.19.84:8080/health-api/reportInfo?ap=web&aid=";
	//private static final String http_down_url = "http://120.24.19.84:8080/health-api/imageDown?ap=web&imageUrl=";
	
	private static final String http_reporturl = "http://117.27.128.186:7001/health-api/reportInfo?ap=web&aid=";
	private static final String http_down_url = "http://117.27.128.186:7001/health-api/imageDown?ap=web&imageUrl=";

	@Scheduled(cron = "0 0/3 * * * ?")
	public static void taskCycle() {
		logger.info("定时器执行任务");
		
		TbReportService tbReportService = (TbReportService)ApplicationContextUtil.getBean("tbReportService");
		// 获取机器诊断中的报告
		Example example = new Example(TbReport.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",  SysConstants.STATUS_MACHINE);
        List<TbReport> tbReportList = tbReportService.selectByExample(example);
        for(TbReport tbReport : tbReportList) {
        	TbHealthInfoService tbHealthInfoService = (TbHealthInfoService)ApplicationContextUtil.getBean("tbHealthInfoService");
        	
        	TbHealthInfo tbHealthInfo = tbHealthInfoService.selectByKey(tbReport.getHealthinfoid());
        	
        	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        	ServletContext servletContext = webApplicationContext.getServletContext();
        	String realpath = servletContext.getRealPath("/");
        	
        	// 机器诊断
    		MachineImplement mi = new MachineImplement(tbHealthInfo, tbReport, tbReportService, realpath, http_reporturl, http_down_url);
    		Thread t = new Thread(mi);
    		t.start();
        	
        }
        
	}
	
}
