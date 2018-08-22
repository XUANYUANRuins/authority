package cn.ffcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffcs.service.IService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 通用sevice
 */
@Service
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    /**
     * 判断数据库操作是否成功
     *
     * @param result
     *            数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(int result) {
        return result >= 1;
    }
    
    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
    
    public boolean insert(T entity) {
        return retBool(mapper.insert(entity));
    }
    
    public boolean insertSelective(T entity) {
    	return retBool(mapper.insertSelective(entity));
    }
    
    public boolean delete(Object key) {
        return retBool(mapper.deleteByPrimaryKey(key));
    }
    
    public boolean update(T entity) {
        return retBool(mapper.updateByPrimaryKey(entity));
    }
    
    public boolean updateSelective(T entity) {
        return retBool(mapper.updateByPrimaryKeySelective(entity));
    }
    
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }
    
    public T selectOne(T entity){
    	return mapper.selectOne(entity);
    }
    
    public int selectCount(T entity){
    	return mapper.selectCount(entity);
    }

}
