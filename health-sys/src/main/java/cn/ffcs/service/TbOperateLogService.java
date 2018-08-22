package cn.ffcs.service;

import java.util.Date;
import java.util.List;

import cn.ffcs.model.TbOperateLog;

public interface TbOperateLogService extends IService<TbOperateLog> {
    
    /**
	 * 根据参数查询列表
	 * @param tbOperateLog
	 * @return
	 */
	List<TbOperateLog> selectListByParam(TbOperateLog tbOperateLog);
	
	/**
	 * 带时间范围的查询
	 * @param tbOperateLog
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TbOperateLog> selectListByParam(TbOperateLog tbOperateLog, Date startDate, Date endDate);
}

