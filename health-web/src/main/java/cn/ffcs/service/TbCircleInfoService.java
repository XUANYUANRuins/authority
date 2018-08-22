package cn.ffcs.service;

import cn.ffcs.model.TbCircle;

public interface TbCircleInfoService extends IService<TbCircle> {
	TbCircle selectByReportid(Integer reportid);
}
