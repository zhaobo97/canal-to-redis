package com.zhaobo.canal.mapper;

import com.zhaobo.canal.model.UserModel;

public interface UserModelMapper {
    int insert(UserModel record);

    int insertSelective(UserModel record);
}