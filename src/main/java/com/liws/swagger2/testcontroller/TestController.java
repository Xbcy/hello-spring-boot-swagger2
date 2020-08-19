package com.liws.swagger2.testcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "liws/test", tags = {"testController测试"})
@RequestMapping("liws/test")
@RestController
public class TestController {

    @ApiOperation(value = "测试", httpMethod = "POST")
    @PostMapping("/add")
    public Object query() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "hello,test");
        map.put("status", true);
        return map;
    }
}
