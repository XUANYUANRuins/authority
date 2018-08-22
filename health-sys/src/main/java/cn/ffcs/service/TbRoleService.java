package cn.ffcs.service;

import java.util.List;
import java.util.Set;

import cn.ffcs.model.TbRole;

public interface TbRoleService extends IService<TbRole> {

    /**
     * 根据多个id查询列表
     * @param ids
     * @return
     */
    List<TbRole> selectListByIds(List<Long> ids);
    
    /**
	 * 根据参数查询列表
	 * @param tbMenu
	 * @return
	 */
	List<TbRole> selectListByParam(TbRole tbRole);
	
	//根据用户ID查询角色（role），放入到Authorization里。
	Set<String> findRoleByUserId(Long userId);
}
