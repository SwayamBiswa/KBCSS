package com.swayam.kbcss.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app")
public class AppController {


    @Autowired
    AppProperties myAppProperties;


    @GetMapping("/title")
    public String getAppTitle()
    {
        return myAppProperties.getTitle();
    }

    @GetMapping("/description")
    public String getAppDescription()
    {
        return myAppProperties.getDescription();
    }

}