package cn.ffcs.service;

import java.util.List;

import cn.ffcs.model.TbMenu;

public interface TbMenuService extends IService<TbMenu> {

    /**
     * 根据多个id查询列表
     * @param ids
     * @return
     */
    List<TbMenu> selectListByIds(List<Long> ids);
    
    /**
	 * 根据参数查询列表
	 * @param tbMenu
	 * @return
	 */
	List<TbMenu> selectListByParam(TbMenu tbMenu);
	
}
