package com.swagger.demo.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenheng
 * @time 2020/4/4 20:14
 */
@RestController
@RequestMapping("/core")
public class CoreController {

    @GetMapping("/{cid}")
    @ApiOperation(value = "核心接口")
    public String demo() {
        return "";
    }
}
