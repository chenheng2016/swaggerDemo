package com.swagger.demo.controller;

import com.swagger.demo.model.Person;
import com.swagger.demo.model.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chenheng
 * @time 2020/4/3 13:04
 */
@RestController
@Api(tags = "用户模块")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/{cid}")
    @ApiOperation(value = "根据用户id查询用户信息")
    public User getUserById(@ApiParam(value = "用户id", example = "1", required = true, name = "cid", allowableValues = "range(1, 5)") @PathVariable(name = "cid") Integer id) {
        log.info("id是： " + id);
        return new User();
    }

    @PostMapping("/")
    @ApiOperation(value = "创建用户")
    public User createUser(HttpServletRequest httpServletRequest, @RequestBody User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String token1 = httpServletRequest.getHeader("token");
        log.info(token);
        log.info(token1);
        log.info("user是： " + user);
        return new User();
    }


    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "id", name = "id", example = "1", dataType = "int"),
            @ApiImplicitParam(value = "username", name = "username", example = "cc", dataType = "string"),
            @ApiImplicitParam(value = "email", name = "email", example = "cc@qq.com", dataType = "string"),
    })
    @PutMapping("/")
    @ApiOperation(value = "更新用户")
    public User updateUser(User user) {
        log.info("user是： " + user);
        return new User();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public List<User> getUserList(Person person) {
        return null;
    }
}
