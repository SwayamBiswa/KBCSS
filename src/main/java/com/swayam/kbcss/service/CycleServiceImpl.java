package com.swayam.kbcss.service;

import com.swayam.kbcss.config.CycleEnum;
import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;
import com.swayam.kbcss.util.CycleUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class CycleServiceImpl  implements CycleService{
  private final Logger logger = LoggerFactory.getLogger(CycleServiceImpl.class);
    @Autowired
    private CycleUtil cycleUtil;

    @SneakyThrows
    @Async
    @Override
    public CompletableFuture<List<Response>> calculatePrice(List<Request> requestList) {
        List<Response> responseList=new ArrayList<>();
        Map<String,Double> cycleComponentMap;
        Response response;
        for( Request request:requestList) {
            Map<String, Double> cycleMap = cycleUtil.builder(request);
            cycleComponentMap = new LinkedHashMap<>();
            for (String cycleComponent : request.getCycleComponents()) {
                if (CycleEnum.contains(cycleComponent))
                    cycleComponentMap.put(cycleComponent, cycleMap.get(cycleComponent));
            }

            String type = request.getType();
            Future<Double> price = CompletableFuture.supplyAsync(() -> calculate(type, cycleMap));

            // return price of Cycle;
            response = new Response();
            response.setCycleComponentMap(cycleComponentMap);
            response.setPriceOfCycle(price.get());
            responseList.add(response);
        }
        return CompletableFuture.completedFuture(responseList);

    }//End- calculatePrice(Request request)

   private double calculate(String type,Map<String,Double> cycleMap){
       return (cycleMap.get("FRAME")+
               cycleMap.get("HANDLEBARWITHBRAKES")+
               cycleMap.get("SEAT")+
               cycleMap.get("CHAIN")+
               (2 * cycleMap.get("WHEELS")));
}// End

}//End of class
