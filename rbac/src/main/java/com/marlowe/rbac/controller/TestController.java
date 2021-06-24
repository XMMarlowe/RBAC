package com.marlowe.rbac.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: music
 * @description: test
 * @author: Marlowe
 * @create: 2021-05-24 17:31
 **/
@Api(tags = "测试控制类")
@RestController
public class TestController {

    /**
     * 测试接口
     *
     * @return
     */
    @ApiOperation("测试接口")
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

}
