package com.xiashu.spblog.service;


import com.xiashu.spblog.bean.Tag;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface TagsService {
    void saveTag(Tag tag);

    List<Tag> listTag();

    Tag updateTag(Tag tag) throws NotFoundException;

    void deleteTag(Long id);

    List<Tag> listTagAndCount();
}
