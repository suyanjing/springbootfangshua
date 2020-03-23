package com.example.bootdemo;

import com.example.bootdemo.config.AccessLimit;
import com.example.bootdemo.config.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/fangshua")
    @AccessLimit(maxCount = 1,seconds = 1000,needLogin = false)
    public ResponseResult fangShuaTest(){
        return new ResponseResult("0", "防刷测试");
    }
}
