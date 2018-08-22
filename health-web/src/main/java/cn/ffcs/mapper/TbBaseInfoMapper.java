package cn.ffcs.mapper;

import java.util.List;
import java.util.Map;

import cn.ffcs.model.TbBaseInfo;
import tk.mybatis.mapper.common.Mapper;

public interface TbBaseInfoMapper extends Mapper<TbBaseInfo> {

	/**
	 * 按条件查询
	 * @param serArgs
	 * @return
	 */
	List<TbBaseInfo> selectListByArgs(Map<String, Object> serArgs);
	
	List<Map> selectListGroupByCardID();
}