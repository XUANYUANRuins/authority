package cn.ffcs.mapper;

import java.util.Map;

import cn.ffcs.model.TbUser;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserMapper extends Mapper<TbUser> {
	TbUser login(Map<String, Object> map);
}