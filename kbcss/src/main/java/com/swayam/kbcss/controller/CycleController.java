package com.swayam.kbcss.controller;

import com.swayam.kbcss.config.CycleProperties;
import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;
import com.swayam.kbcss.service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cycle")
public class CycleController {
    @Autowired
    CycleProperties cycleProperties;

    private final CycleService cycleService;

    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }


    @GetMapping("/cycleProperties")
    public CycleProperties getCycleProperties() {
        return cycleProperties;
    }

    @PostMapping("/calculatePrice")
    public List<Response> calculatePrice(@RequestBody List<Request> requestList) {
        return cycleService.calculatePrice(requestList);

    }

}
