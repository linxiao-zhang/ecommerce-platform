package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Api(value="注册登陆",tags={"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;
    @ApiOperation(value="用户名是否存在",notes = "用户名是否存在",httpMethod="GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){

        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为null或为空");
        }

        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value="用户注册",notes = "用户注册",httpMethod="POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO){


        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        //0. 判断用户名和密码不为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPwd)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        //1. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }

        //2. 密码长度不能少于6位
        if(password.length()<6){
            return IMOOCJSONResult.errorMsg("密码长度不能小于6位");
        }



        //3. 判断两次密码是否一致
        if(!password.equals(confirmPwd)){
            return IMOOCJSONResult.errorMsg("两次密码不一致");
        }
        //4. 实现注册
        userService.createUser(userBO);
        return IMOOCJSONResult.ok();
    }


    @ApiOperation(value="用户登陆",notes = "用户登陆",httpMethod="POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO) throws NoSuchAlgorithmException {


        String username = userBO.getUsername();
        String password = userBO.getPassword();

        //0. 判断用户名和密码不为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
        //1. 实现登陆
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(password));
        if(userResult == null){
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }

        return IMOOCJSONResult.ok(userResult);

    }

}