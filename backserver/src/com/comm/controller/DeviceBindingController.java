package com.comm.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeviceBindingController {
    
    //跳至设备绑定页
    @RequestMapping("/user/deviceBinding.do")
    public ModelAndView deviceBinding(HttpServletRequest request)throws IOException {

        return new ModelAndView("deviceBinding");	
    }
}
