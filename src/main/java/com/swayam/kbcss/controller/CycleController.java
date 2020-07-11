package com.swayam.kbcss.controller;

import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;
import com.swayam.kbcss.service.CycleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("cycle")
public class CycleController {
    private final CycleService cycleService;

    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    @PostMapping("/calculatePrice")
    public CompletableFuture<List<Response>> calculatePrice(@RequestBody List<Request> requestList) {
      return cycleService.calculatePrice(requestList);
    }

}
