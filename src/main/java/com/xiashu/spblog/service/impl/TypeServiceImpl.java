package com.xiashu.spblog.service.impl;

import com.xiashu.spblog.bean.Blog;
import com.xiashu.spblog.bean.Type;
import com.xiashu.spblog.mapper.BlogMapper;
import com.xiashu.spblog.mapper.TypeMapper;
import com.xiashu.spblog.service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Type> listTypeAndCount() {
        List<Type> typeList = typeMapper.selectAll();
        for (Type type : typeList) {
            Blog blog = new Blog();
            blog.setTypeId(type.getId());
            int count = blogMapper.selectCount(blog);
            type.setBlogsCount(count);
        }
        return typeList;
    }

    @Override
    public Type saveType(Type type) {
        typeMapper.insert(type);
        return type;
    }

    @Override
    public Type getType(Long id) {
        Type sqlType = new Type();
        sqlType.setId(id);

        return typeMapper.selectOne(sqlType);
    }

    @Override
    public List<Type> listType() {
        List<Type> typesList = typeMapper.selectAll();
        return typesList;

    }

    @Transactional
    @Override
    public Type updateType(Type type) throws NotFoundException {
        if (type != null){
            Example example = new Example(Type.class);
            example.createCriteria().andEqualTo("id",type.getId());
            typeMapper.updateByExample(type,example);
            return type;
        }
        throw new NotFoundException("不存在该资源");
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteByPrimaryKey(id);
    }




}
