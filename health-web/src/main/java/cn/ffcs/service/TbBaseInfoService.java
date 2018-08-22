package cn.ffcs.service;

import java.util.List;
import java.util.Map;

import cn.ffcs.model.TbBaseInfo;

public interface TbBaseInfoService extends IService<TbBaseInfo>{

	/**
	 * 按条件查询
	 * @param serArgs
	 * @return
	 */
	public List<TbBaseInfo> getListBySearch(Map<String, Object> serArgs);
	
	/**
	 * 按cardID查询
	 * @param serArgs
	 * @return
	 */
	public List<Map> getListGroupByCardID();
	
}
