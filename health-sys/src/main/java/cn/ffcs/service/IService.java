package cn.ffcs.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IService<T> {

	/**
	 * 根据Key查询
	 * @param key
	 * @return
	 */
    T selectByKey(Object key);

    /**
     * 插入实体
     * @param entity
     * @return
     */
    boolean insert(T entity);
    
    /**
     * 选择性插入
     * @param entity
     * @return
     */
    boolean insertSelective(T entity);

    /**
     * 根据key删除
     * @param key
     * @return
     */
    boolean delete(Object key);

    /**
     * 更新实体
     * @param entity
     * @return
     */
    boolean update(T entity);

    /**
     * 选择性更新
     * @param entity
     * @return
     */
    boolean updateSelective(T entity);

    /**
     * 根据通用对象example查询
     * @param example
     * @return
     */
    List<T> selectByExample(Object example);

    /**
     * 根据实体查询一条记录
     * @param entity
     * @return
     */
    T selectOne(T entity);
    
    /**
     * 根据实体查询记录数
     * @param entity
     * @return
     */
    int selectCount(T entity);
}
