package cn.ffcs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ffcs.model.TbHealthInfo;
import cn.ffcs.service.TbHealthInfoService;
import tk.mybatis.mapper.entity.Example;

@Service("tbHealthInfoService")
@Transactional
public class TbHealthInfoServiceImpl extends BaseService<TbHealthInfo> implements TbHealthInfoService {

	@Override
	public TbHealthInfo selectByBaseinfoid(Integer baseinfoid) {
		Example example = new Example(TbHealthInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("baseinfoid",  baseinfoid);
        List<TbHealthInfo> list = selectByExample(example);
        if(list == null ){
        	return null;
        } else {
        	return list.get(0);
        }
	}


}
