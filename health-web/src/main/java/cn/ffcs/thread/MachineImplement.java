package cn.ffcs.thread;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.ffcs.model.TbHealthInfo;
import cn.ffcs.model.TbReport;
import cn.ffcs.service.TbReportService;
import cn.ffcs.util.AdjustUtil;
import cn.ffcs.util.HttpClientUtil;
import cn.ffcs.util.SysConstants;

/**
 * 机器诊断线程
 * @author huang
 *
 */
public class MachineImplement implements Runnable{

	TbHealthInfo tbHealthInfo;
	TbReport tbReport;
	TbReportService tbReportService;
	
	String realpath;
	String http_reporturl;
	String http_down_url;
	
	public MachineImplement(TbHealthInfo tbHealthInfo, TbReport tbReport, TbReportService tbReportService,
			String realpath, String http_reporturl, String http_down_url) {
		
		this.tbHealthInfo = tbHealthInfo;
		this.tbReport = tbReport;
		this.tbReportService = tbReportService;
		this.realpath = realpath;
		this.http_reporturl = http_reporturl;
		this.http_down_url = http_down_url;
	}
	
	
	@Override
	public void run() {
		String leftaid = tbHealthInfo.getLeftaid();
		String rightaid = tbHealthInfo.getRightaid();
		
		JSONObject leftAdjust = AdjustUtil.getAdjust(http_reporturl+leftaid);
		JSONObject rightAdjust = AdjustUtil.getAdjust(http_reporturl+rightaid);
		
		// 返回的标记结果
		JSONArray leftMark = leftAdjust.getJSONArray("mark");
		JSONArray rightMark = rightAdjust.getJSONArray("mark");
		if(leftMark!=null && !leftMark.isEmpty()){
			tbReport.setLeftresult(leftMark.toString());
		}
		if(rightMark!=null && !rightMark.isEmpty()){
			tbReport.setRightresult(rightMark.toString());
		}
		
		// 机器诊断完成
		if (leftMark!=null && !leftMark.isEmpty() && rightMark!=null && !rightMark.isEmpty() ){
			//tbReport.setInitstatus(SysConstants.STATUS_DOCTOR_WAIT);
			tbReport.setStatus(SysConstants.STATUS_DOCTOR_WAIT);
		}
		
		if(leftAdjust!=null && !leftAdjust.isEmpty()) {
			// 返回的标记图片
			String leftimg = leftAdjust.getJSONObject("result").getString("0");
			// 不重复下载
			if(!leftimg.equals(tbReport.getLeftsignpic())){
				// 图片保存到本地
				String directory_left = realpath + leftimg.substring(0, leftimg.lastIndexOf("/"));
		        String fileName_left = leftimg.substring(leftimg.lastIndexOf("/")+1);
				HttpClientUtil.downloadImg(http_down_url+leftimg, directory_left, fileName_left);
			}
			tbReport.setLeftsignpic(leftimg);
		}
		
		if(rightAdjust!=null && !rightAdjust.isEmpty()) {
			String rightimg = rightAdjust.getJSONObject("result").getString("0");
			if(!rightimg.equals(tbReport.getRightsignpic())){
				// 图片保存到本地
				String directory_right = realpath + rightimg.substring(0, rightimg.lastIndexOf("/"));
		        String fileName_right = rightimg.substring(rightimg.lastIndexOf("/")+1);
				HttpClientUtil.downloadImg(http_down_url+rightimg, directory_right, fileName_right);
			}
			tbReport.setRightsignpic(rightimg);
		}
		
		//更新时间
		tbReport.setUpdatetime(new Date());
		tbReportService.updateSelective(tbReport);
		
	}

}
