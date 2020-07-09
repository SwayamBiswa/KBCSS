package com.swayam.kbcss.service;

import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;

import java.util.List;

public interface CycleService {
    List<Response> calculatePrice(List<Request> requestList);
    double calculatePrice(Request request);
}
