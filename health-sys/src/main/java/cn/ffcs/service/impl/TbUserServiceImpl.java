package cn.ffcs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffcs.mapper.TbRoleMapper;
import cn.ffcs.mapper.TbUserMapper;
import cn.ffcs.model.TbUser;
import cn.ffcs.service.TbUserService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class TbUserServiceImpl extends BaseService<TbUser> implements TbUserService{
	@Autowired
    protected TbUserMapper tbUserMapper;

	@Override
	public TbUser selectByLoginname(String loginname) {
		Example example = new Example(TbUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("loginname",  loginname);
        List<TbUser> tbUsers = selectByExample(example);
        if(tbUsers == null || tbUsers.size()==0){
        	return null;
        } else {
        	return tbUsers.get(0);
        }
	}

	@Override
	public List<TbUser> selectListByParam(TbUser tbUser) {
		Example example = new Example(TbUser.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbUser);
        return selectByExample(example);
	}

	@Override
	public List<TbUser> selectListByParam(TbUser tbUser, Date startDate, Date endDate) {
		Example example = new Example(TbUser.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbUser);
        //时间范围
        if(startDate!=null && endDate!=null){
        	criteria.andBetween("updatetime", startDate, endDate);
        } else if ( startDate!=null && endDate==null ){
        	criteria.andGreaterThan("updatetime", startDate);
        } else if ( startDate==null && endDate!=null ){
        	criteria.andLessThan("updatetime", endDate);
        } 
        return selectByExample(example);
	}
	
	/**
	 * 通用添加条件
	 * @param criteria
	 * @param tbUser
	 */
	public void addCriteria(Criteria criteria, TbUser tbUser){
		if (StringUtil.isNotEmpty(tbUser.getRealname())) {
            criteria.andLike("realname", "%" + tbUser.getRealname() + "%");
        }
        if (StringUtil.isNotEmpty(tbUser.getLoginname())) {
            criteria.andLike("loginname", "%" + tbUser.getLoginname() + "%");
        }
        if (StringUtil.isNotEmpty(tbUser.getPhone())) {
            criteria.andLike("phone", "%" + tbUser.getPhone() + "%");
        }
        if (tbUser.getStatus() != null){
        	criteria.andEqualTo("status", tbUser.getStatus());
        }
        if (StringUtil.isNotEmpty(tbUser.getOrgId())) {
            criteria.andEqualTo("orgId", tbUser.getOrgId());
        }
        if (tbUser.getId() != null) {
            criteria.andEqualTo("id", tbUser.getId());
        }
	}

	@Override
	public TbUser login(String email, String pswd) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("pswd", pswd);
		TbUser user = tbUserMapper.login(map);
		return user;
	}

}
