package com.swayam.kbcss.service;

import com.swayam.kbcss.config.CycleEnum;
import com.swayam.kbcss.config.CycleProperties;
import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;
import com.swayam.kbcss.util.CycleUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class CycleServiceImpl  implements CycleService{
  private Logger logger = LoggerFactory.getLogger(CycleServiceImpl.class);
    @Autowired
    private CycleProperties cycleProperties;
    @Autowired
    private CycleUtil cycleUtil;

  @SneakyThrows
  @Override
    public List<Response> calculatePrice(List<Request> requestList) {

      List<Response> resultList=new LinkedList<>();;
        for(Request request:requestList ){
            logger.debug("  for(Request request:requestList )  ");
            Response response =null;
            ExecutorService service=Executors.newFixedThreadPool(10);
            logger.debug("---ExecutorService---");

            Map<String, Double> cycleMap = cycleUtil.builder(request);

            Map<String,Double> cycleComponentMap = null;
            cycleComponentMap = new LinkedHashMap<>();
            //check elements and store in Set
            for (String cycleComponent : request.getCycleComponents()) {

                if (CycleEnum.contains(cycleComponent))
                cycleComponentMap.put(cycleComponent,cycleMap.get(cycleComponent));
                logger.debug("  for (String cycleComponent : request.getCycleComponents())  ");

            }
            Map<String, Double> finalCycleMap = cycleMap;
            Future<Double> price=CompletableFuture.supplyAsync(()->calculatePrice( request, finalCycleMap));

            response=new Response();
            response.setCycleComponentMap(cycleComponentMap);
            response.setPriceOfCycle(price.get());
            resultList.add(response);
        }
         return resultList;
    }//End- calculatePrice(List<Request> requestList)


    @Async
    @Override
    public double calculatePrice(Request request,Map<String,Double> cycleMap) {
        //calculate price of Cycle
        double priceofCycle =
                cycleMap.get("FRAME")+
                        cycleMap.get("HANDLEBARWITHBRAKES")+
                        cycleMap.get("SEAT")+
                        cycleMap.get("CHAIN")+
                        (2 * cycleMap.get("WHEELS"));
        System.out.println(cycleMap);
        return priceofCycle;

    }//End- calculatePrice(Request request)


}//End of class
