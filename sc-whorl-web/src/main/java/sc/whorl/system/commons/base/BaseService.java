package sc.whorl.system.commons.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import sc.whorl.system.commons.MyMapper;


public abstract class BaseService<M extends MyMapper<T>, T> {
    @Autowired
    protected M mapper;
    public void setMapper(M mapper) {
        this.mapper = mapper;
    }


    protected T selectOne(T entity) {
        return mapper.selectOne(entity);
    }


    protected T selectByPrimaryKey(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    protected List<T> selectList(T entity) {
        return mapper.select(entity);
    }


    protected List<T> selectListAll() {
        return mapper.selectAll();
    }


    protected Long selectCount(T entity) {
        return new Long(mapper.selectCount(entity));
    }


    protected void insert(T entity) {
        mapper.insert(entity);
    }


    protected void insertSelective(T entity) {
        mapper.insertSelective(entity);
    }


    protected void delete(T entity) {
        mapper.delete(entity);
    }


    protected void deleteByPrimaryKey(Object id) {
        mapper.deleteByPrimaryKey(id);
    }


    protected void updateByPrimaryKey(T entity) {
        mapper.updateByPrimaryKey(entity);
    }


    protected void updateByPrimaryKeySelective(T entity) {
        mapper.updateByPrimaryKeySelective(entity);

    }

    protected List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    protected int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    protected int updateByExampleSelective(T entity, Object example) {
        return mapper.updateByExampleSelective(entity, example);
    }

    protected boolean existsWithPrimaryKey(Object primaryKey) {
        return mapper.existsWithPrimaryKey(primaryKey);
    }

    protected T selectOneByExample(Object example) {
        return mapper.selectOneByExample(example);
    }

    protected int insertList(List<T> recordList) {
        return mapper.insertList(recordList);
    }


}
