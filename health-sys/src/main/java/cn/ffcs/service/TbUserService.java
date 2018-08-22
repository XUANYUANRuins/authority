package cn.ffcs.service;

import java.util.Date;
import java.util.List;

import cn.ffcs.model.TbUser;

public interface TbUserService extends IService<TbUser> {

    /**
     * 根据登录名查询
     * 
     * @param loginname
     * @return
     */
    TbUser selectByLoginname(String loginname);
    
    /**
	 * 根据参数查询列表
	 * @param tbMenu
	 * @return
	 */
	List<TbUser> selectListByParam(TbUser tbUser);
	
	/**
	 * 带时间范围的查询
	 * @param tbUser
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TbUser> selectListByParam(TbUser tbUser, Date startDate, Date endDate);
	
	
	TbUser login(String email ,String pswd);
}

