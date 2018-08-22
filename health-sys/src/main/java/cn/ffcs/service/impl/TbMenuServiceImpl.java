package cn.ffcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ffcs.mapper.TbMenuMapper;
import cn.ffcs.model.TbMenu;
import cn.ffcs.service.TbMenuService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class TbMenuServiceImpl extends BaseService<TbMenu> implements TbMenuService{

	@Autowired
	protected TbMenuMapper tbMenuMapper;
	
	@Override
	public List<TbMenu> selectListByIds(List<Long> ids) {
		return tbMenuMapper.selectListByIds(ids);
	}

	@Override
	public List<TbMenu> selectListByParam(TbMenu tbMenu) {
		Example example = new Example(TbMenu.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(tbMenu.getName())) {
            criteria.andLike("name", "%" + tbMenu.getName() + "%");
        }
        if (StringUtil.isNotEmpty(tbMenu.getUrl())) {
            criteria.andLike("url", "%" + tbMenu.getUrl() + "%");
        }
        if (tbMenu.getParentid() != null){
        	criteria.andEqualTo("parentid", tbMenu.getParentid());
        }
        if (tbMenu.getStatus() != null){
        	criteria.andEqualTo("status", tbMenu.getStatus());
        }
        if (tbMenu.getId() != null) {
            criteria.andEqualTo("id", tbMenu.getId());
        }
        return selectByExample(example);
	}


}
