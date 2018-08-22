package cn.ffcs.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ffcs.mapper.TbRoleMapper;
import cn.ffcs.model.TbRole;
import cn.ffcs.service.TbRoleService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class TbRoleServiceImpl extends BaseService<TbRole> implements TbRoleService{

	@Autowired
    protected TbRoleMapper tbRoleMapper;
	
	@Override
	public List<TbRole> selectListByIds(List<Long> ids) {
		return tbRoleMapper.selectListByIds(ids);
	}

	@Override
	public List<TbRole> selectListByParam(TbRole tbRole) {
		Example example = new Example(TbRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(tbRole.getName())) {
            criteria.andLike("name", "%" + tbRole.getName() + "%");
        }
        if (tbRole.getStatus() != null){
        	criteria.andEqualTo("status", tbRole.getStatus());
        }
        if (tbRole.getId() != null) {
            criteria.andEqualTo("id", tbRole.getId());
        }
        return selectByExample(example);
	}

	@Override
	public Set<String> findRoleByUserId(Long userId) {
		// TODO Auto-generated method stub
		return tbRoleMapper.findRoleByUserId(userId);
	}


}
