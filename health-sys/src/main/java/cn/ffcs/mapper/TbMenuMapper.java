package cn.ffcs.mapper;

import java.util.List;

import cn.ffcs.model.TbMenu;
import tk.mybatis.mapper.common.Mapper;

public interface TbMenuMapper extends Mapper<TbMenu> {
	
	/**
     * 根据多个id查询列表
     * @param ids
     * @return
     */
    List<TbMenu> selectListByIds(List<Long> ids);
    
}