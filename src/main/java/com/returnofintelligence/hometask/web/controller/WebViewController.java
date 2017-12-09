package com.returnofintelligence.hometask.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by The Diamond Doge on 09.12.2017.
 */
@Controller
public class WebViewController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
