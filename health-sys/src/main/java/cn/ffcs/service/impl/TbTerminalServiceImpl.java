package cn.ffcs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffcs.mapper.TbTerminalMapper;
import cn.ffcs.model.TbOrganization;
import cn.ffcs.model.TbTerminal;
import cn.ffcs.service.TbTerminalService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class TbTerminalServiceImpl extends BaseService<TbTerminal> implements TbTerminalService{
	@Autowired
    protected TbTerminalMapper tbTerminalMapper;

//	@Override
//	public TbUser selectByLoginname(String loginname) {
//		Example example = new Example(TbUser.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andLike("loginname",  loginname);
//        List<TbUser> tbUsers = selectByExample(example);
//        if(tbUsers == null || tbUsers.size()==0){
//        	return null;
//        } else {
//        	return tbUsers.get(0);
//        }
//	}

	@Override
	public List<TbTerminal> selectListByParam(TbTerminal tbTerminal) {
		Example example = new Example(TbOrganization.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbTerminal);
        return selectByExample(example);
	}

	@Override
	public List<TbTerminal> selectListByParam(TbTerminal tbTerminal, Date produceStartDate, Date produceEndDate, Date buyStartDate, Date buyEndDate) {
		Example example = new Example(TbTerminal.class);
        Example.Criteria criteria = example.createCriteria();
        //通用添加条件
        addCriteria(criteria, tbTerminal);
        //时间范围
        if(produceStartDate!=null && produceEndDate!=null){
        	criteria.andBetween("cameraProduceTime", produceStartDate, produceEndDate);
        } else if ( produceStartDate!=null && produceEndDate==null ){
        	criteria.andGreaterThan("cameraProduceTime", produceStartDate);
        } else if ( produceStartDate==null && produceEndDate!=null ){
        	criteria.andLessThan("cameraProduceTime", produceEndDate);
        }
        
        if(buyStartDate!=null && buyEndDate!=null){
        	criteria.andBetween("cameraBuyTime", buyStartDate, buyEndDate);
        } else if ( buyStartDate!=null && buyEndDate==null ){
        	criteria.andGreaterThan("cameraBuyTime", buyStartDate);
        } else if ( buyStartDate==null && buyEndDate!=null ){
        	criteria.andLessThan("cameraBuyTime", buyEndDate);
        } 
        return selectByExample(example);
	}
	
	/**
	 * 通用添加条件
	 * @param criteria
	 * @param tbTerminal
	 */
	public void addCriteria(Criteria criteria, TbTerminal tbTerminal){
		if (StringUtil.isNotEmpty(tbTerminal.getCameraBrand())) {
            criteria.andLike("cameraBrand", "%" + tbTerminal.getCameraBrand() + "%");
        }
        if (StringUtil.isNotEmpty(tbTerminal.getCameraModel())) {
            criteria.andLike("cameraModel", "%" + tbTerminal.getCameraModel() + "%");
        }
        
        if (StringUtil.isNotEmpty(tbTerminal.getCameraProducer())) {
            criteria.andLike("cameraProducer", "%" + tbTerminal.getCameraProducer() + "%");
        }
        if (StringUtil.isNotEmpty(tbTerminal.getOrgId())) {
            criteria.andLike("orgId", "%" + tbTerminal.getOrgId() + "%");
        }
        if (tbTerminal.getCentralResolution() != null){
        	criteria.andEqualTo("centralResolution", tbTerminal.getCentralResolution());
        }
        if (tbTerminal.getCcdResolution() != null){
        	criteria.andEqualTo("ccdResolution", tbTerminal.getCcdResolution());
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

}
