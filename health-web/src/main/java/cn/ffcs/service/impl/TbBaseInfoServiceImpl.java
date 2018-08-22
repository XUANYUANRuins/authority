package cn.ffcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ffcs.mapper.TbBaseInfoMapper;
import cn.ffcs.model.TbBaseInfo;
import cn.ffcs.service.TbBaseInfoService;

@Service("tbBaseInfoService")
@Transactional
public class TbBaseInfoServiceImpl extends BaseService<TbBaseInfo> implements TbBaseInfoService {

	@Autowired
	protected TbBaseInfoMapper tbBaseInfoMapper;
	
	@Override
	public List<TbBaseInfo> getListBySearch(Map<String, Object> serArgs) {
        return tbBaseInfoMapper.selectListByArgs(serArgs);
	}
	
	public List<Map> getListGroupByCardID() {
		return tbBaseInfoMapper.selectListGroupByCardID();
	}
	
}
