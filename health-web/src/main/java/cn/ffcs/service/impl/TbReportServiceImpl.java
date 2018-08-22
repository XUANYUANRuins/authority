package cn.ffcs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ffcs.model.TbReport;
import cn.ffcs.service.TbReportService;
import tk.mybatis.mapper.entity.Example;

@Service("tbReportService")
@Transactional
public class TbReportServiceImpl extends BaseService<TbReport> implements TbReportService {

	@Override
	public TbReport selectByHealthinfoid(Integer healthinfoid) {
		Example example = new Example(TbReport.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("healthinfoid",  healthinfoid);
        List<TbReport> list = selectByExample(example);
        if(list == null ){
        	return null;
        } else {
        	return list.get(0);
        }
	}


}
