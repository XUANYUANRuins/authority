package cn.ffcs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffcs.mapper.TbOrganizationMapper;
import cn.ffcs.model.TbDict;
import cn.ffcs.model.TbOrganization;
import cn.ffcs.service.TbOrganizationService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class TbOrganizationServiceImpl extends BaseService<TbOrganization> implements TbOrganizationService{
	@Autowired
    protected TbOrganizationMapper tbOrganizationMapper;

	@Override
	public TbOrganization selectByOrgId(String orgId){
		Example example = new Example(TbOrganization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgId",  orgId);
        List<TbOrganization> tbOrganizations = selectByExample(example);
        if(tbOrganizations == null || tbOrganizations.size()==0){
        	return null;
        } else {
        	return tbOrganizations.get(0);
        }
	}

	@Override
	public List<TbOrganization> selectListByParam(TbOrganization tbOrganization) {
		Example example = new Example(TbOrganization.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbOrganization);
        return selectByExample(example);
	}

	@Override
	public List<TbOrganization> selectListByParam(TbOrganization tbOrganization, Date startDate, Date endDate) {
		Example example = new Example(TbOrganization.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbOrganization);
        //时间范围
        if(startDate!=null && endDate!=null){
        	criteria.andBetween("updateDate", startDate, endDate);
        } else if ( startDate!=null && endDate==null ){
        	criteria.andGreaterThan("updateDate", startDate);
        } else if ( startDate==null && endDate!=null ){
        	criteria.andLessThan("updateDate", endDate);
        } 
        return selectByExample(example);
	}
	
	/**
	 * 通用添加条件
	 * @param criteria
	 * @param tbOrganization
	 */
	public void addCriteria(Criteria criteria, TbOrganization tbOrganization){
		if (StringUtil.isNotEmpty(tbOrganization.getOrgName())) {
            criteria.andLike("orgName", "%" + tbOrganization.getOrgName() + "%");
        }
        if (StringUtil.isNotEmpty(tbOrganization.getOrgId())) {
            criteria.andLike("orgId", "%" + tbOrganization.getOrgId() + "%");
        }
        
        if (StringUtil.isNotEmpty(tbOrganization.getOrgPhone())) {
            criteria.andLike("orgPhone", "%" + tbOrganization.getOrgPhone() + "%");
        }
        if (StringUtil.isNotEmpty(tbOrganization.getLinkman())) {
            criteria.andLike("linkman", "%" + tbOrganization.getLinkman() + "%");
        }
        if (tbOrganization.getStatus() != null){
        	criteria.andEqualTo("status", tbOrganization.getStatus());
        }
	}

//	@Override
//	public TbUser login(String email, String pswd) {
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("email", email);
//		map.put("pswd", pswd);
//		TbUser user = tbUserMapper.login(map);
//		return user;
//	}
	
	@Override
	public List<TbOrganization> selectListByStatus(String status) {	
		List<TbOrganization> list = new ArrayList<TbOrganization>();
		
		Example example = new Example(TbOrganization.class);
		Example.Criteria criteria = example.createCriteria();
	    criteria.andEqualTo("status", status);
	    list = selectByExample(example);
        
		return list;
	}

}
