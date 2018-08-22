package cn.ffcs.service;

import java.util.Date;
import java.util.List;

import cn.ffcs.model.TbOrganization;

public interface TbOrganizationService extends IService<TbOrganization> {

//    /**
//     * 根据登录名查询
//     * 
//     * @param loginname
//     * @return
//     */
//    TbUser selectByLoginname(String loginname);
	
	
    /**
     * 根据机构状态取得机构列表
     * @param status
     * @return
     */
    List<TbOrganization> selectListByStatus(String status);
    
    /**
	 * 根据参数查询列表
	 * @param tbOrganization
	 * @return
	 */
	List<TbOrganization> selectListByParam(TbOrganization tbOrganization);
	
	/**
	 * 带时间范围的查询
	 * @param tbOrganization
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TbOrganization> selectListByParam(TbOrganization tbOrganization, Date startDate, Date endDate);
	
	
//	TbUser login(String email ,String pswd);
	
	 /**
     * 根据机构编号查询
     * 
     * @param orgId
     * @return
     */
	TbOrganization selectByOrgId(String orgId);
}

