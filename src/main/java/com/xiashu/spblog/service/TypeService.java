package com.xiashu.spblog.service;


import com.xiashu.spblog.bean.Type;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface TypeService {

    List<Type> listTypeAndCount();

    Type saveType(Type type);

    Type getType(Long id);

    List<Type> listType() ;

    Type updateType(Type type) throws NotFoundException;

    void deleteType(Long id);
}
