package club.lovety.spring.controller;


import club.lovety.spring.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@RestController
@RequestMapping("one")
public class OneController {
    @Resource
    private IUserService userService;

    @RequestMapping("index")
    public String index(){
        System.out.println(userService.getClass());
        return "你好啊";
    }
}
