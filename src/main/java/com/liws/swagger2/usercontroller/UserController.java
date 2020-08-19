package com.liws.swagger2.usercontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "liws/user", tags = {"用户信息测试"})
@RequestMapping("liws/user")
@RestController
public class UserController {

    @ApiOperation(value = "添加用户", httpMethod = "POST")
    @PostMapping("/add")
    public Object query() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "hello,user");
        map.put("status", true);
        return map;
    }
}
