package cn.ffcs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ffcs.model.TbCircle;
import cn.ffcs.service.TbCircleInfoService;
import tk.mybatis.mapper.entity.Example;

@Service("tbCircleInfoService")
@Transactional
public class TbCircleInfoServiceImpl extends BaseService<TbCircle> implements TbCircleInfoService {

	@Override
	public TbCircle selectByReportid(Integer reportid) {
		Example example = new Example(TbCircle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("reportid",  reportid);
        List<TbCircle> list = selectByExample(example);
        if(list == null ){
        	return null;
        } else {
        	return list.get(0);
        }
	}
}
