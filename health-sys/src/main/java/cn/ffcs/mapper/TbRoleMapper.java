package cn.ffcs.mapper;

import java.util.List;
import java.util.Set;

import cn.ffcs.model.TbRole;
import tk.mybatis.mapper.common.Mapper;

public interface TbRoleMapper extends Mapper<TbRole> {
	
	/**
     * 根据多个id查询列表
     * @param ids
     * @return
     */
    List<TbRole> selectListByIds(List<Long> ids);

	Set<String> findRoleByUserId(Long userId);
}