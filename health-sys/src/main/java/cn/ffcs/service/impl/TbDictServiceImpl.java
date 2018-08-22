package cn.ffcs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import cn.ffcs.model.TbDict;
import cn.ffcs.service.TbDictService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class TbDictServiceImpl extends BaseService<TbDict> implements TbDictService{

	@Override
	public List<TbDict> selectListByParentCode(String code) {
		TbDict tbDict = new TbDict();
		tbDict.setCode(code);
		tbDict = selectOne(tbDict);
		
		List<TbDict> list = new ArrayList<TbDict>();
		
		// 根据父id获取子列表
		Example example = new Example(TbDict.class);
		if( tbDict!=null ) {
	        Example.Criteria criteria = example.createCriteria();
	        criteria.andEqualTo("parentid", tbDict.getId());
	        list = selectByExample(example);
		}
        
		return list;
	}

	@Override
	public List<TbDict> selectListByParam(TbDict tbDict) {
		Example example = new Example(TbDict.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(tbDict.getName())) {
            criteria.andLike("name", "%" + tbDict.getName() + "%");
        }
        if (StringUtil.isNotEmpty(tbDict.getCode())){
        	criteria.andLike("code", "%" + tbDict.getCode() + "%");
        }
        if (tbDict.getParentid() != null){
        	criteria.andEqualTo("parentid", tbDict.getParentid());
        }
        if (tbDict.getStatus() != null){
        	criteria.andEqualTo("status", tbDict.getStatus());
        }
        if (tbDict.getId() != null) {
            criteria.andEqualTo("id", tbDict.getId());
        }
        return selectByExample(example);
	}


}
