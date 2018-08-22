package cn.ffcs.service;

import cn.ffcs.model.TbHealthInfo;

public interface TbHealthInfoService extends IService<TbHealthInfo>{

	/**
	 * 根据baseinfoid获取TbHealthInfo
	 * @param baseinfoid
	 * @return
	 */
	TbHealthInfo selectByBaseinfoid(Integer baseinfoid);
}
