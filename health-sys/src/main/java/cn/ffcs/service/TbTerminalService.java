package cn.ffcs.service;

import java.util.Date;
import java.util.List;

import cn.ffcs.model.TbTerminal;

public interface TbTerminalService extends IService<TbTerminal> {
    
    /**
	 * 根据参数查询列表
	 * @param tbTerminal
	 * @return
	 */
	List<TbTerminal> selectListByParam(TbTerminal tbTerminal);
	
	/**
	 * 带时间范围的查询
	 * @param tbTerminal
	 * @param produceStartDate
	 * @param produceEndDate
	 * @param buyStartDate
	 * @param buyEndDate
	 * @return
	 */
	List<TbTerminal> selectListByParam(TbTerminal tbTerminal, Date produceStartDate, Date produceEndDate, Date buyStartDate, Date buyEndDate);
}

