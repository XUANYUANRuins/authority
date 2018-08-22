package cn.ffcs.service;

import java.util.List;

import cn.ffcs.model.TbDict;

public interface TbDictService extends IService<TbDict> {

    /**
     * 根据父code查询子列表
     * @param ids
     * @return
     */
    List<TbDict> selectListByParentCode(String code);
    
    /**
	 * 根据参数查询列表
	 * @param tbMenu
	 * @return
	 */
	List<TbDict> selectListByParam(TbDict tbDict);
}
