package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

public interface UserService {

    boolean queryUsernameIsExist(String username);

    Users createUser(UserBO userBO);

    /**
     * 检索用户民和密码是否匹配用于登陆
     */
    Users queryUserForLogin(String username, String password);

}
