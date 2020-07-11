package com.swayam.kbcss.service;

import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CycleService {
   CompletableFuture<List<Response>> calculatePrice(List<Request> requestList);
}
