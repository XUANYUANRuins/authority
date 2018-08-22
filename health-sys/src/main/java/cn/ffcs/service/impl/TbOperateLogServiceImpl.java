package cn.ffcs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffcs.mapper.TbOperateLogMapper;
import cn.ffcs.model.TbOperateLog;
import cn.ffcs.model.TbUser;
import cn.ffcs.service.TbOperateLogService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class TbOperateLogServiceImpl extends BaseService<TbOperateLog> implements TbOperateLogService{
	@Autowired
    protected TbOperateLogMapper tbOperateLogMapper;

	@Override
	public List<TbOperateLog> selectListByParam(TbOperateLog tbOperateLog) {
		Example example = new Example(TbOperateLog.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbOperateLog);
        return selectByExample(example);
	}

	@Override
	public List<TbOperateLog> selectListByParam(TbOperateLog tbOperateLog, Date startDate, Date endDate) {
		Example example = new Example(TbOperateLog.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbOperateLog);
        //时间范围
        if(startDate!=null && endDate!=null){
        	criteria.andBetween("operateTime", startDate, endDate);
        } else if ( startDate!=null && endDate==null ){
        	criteria.andGreaterThan("operateTime", startDate);
        } else if ( startDate==null && endDate!=null ){
        	criteria.andLessThan("operateTime", endDate);
        } 
        
        return selectByExample(example);
	}
	
	/**
	 * 通用添加条件
	 * @param criteria
	 * @param tbOperateLog
	 */
	public void addCriteria(Criteria criteria, TbOperateLog tbOperateLog){
		if (StringUtil.isNotEmpty(tbOperateLog.getUserName())) {
            criteria.andLike("userName", "%" + tbOperateLog.getUserName() + "%");
        }
        if (StringUtil.isNotEmpty(tbOperateLog.getRealName())) {
            criteria.andLike("realName", "%" + tbOperateLog.getRealName() + "%");
        }
        if (StringUtil.isNotEmpty(tbOperateLog.getIp())) {
            criteria.andLike("ip", "%" + tbOperateLog.getIp() + "%");
        }
//        if (StringUtil.isNotEmpty(tbOperateLog.getIp())) {
//            criteria.andEqualTo("ip", tbOperateLog.getIp());
//        }     
	}
}
