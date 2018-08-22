package cn.ffcs.service;

import cn.ffcs.model.TbReport;

public interface TbReportService extends IService<TbReport>{

	/**
	 * 根据healthinfoid获取TbReport
	 * @param healthinfoid
	 * @return
	 */
	TbReport selectByHealthinfoid(Integer healthinfoid);
}
