package com.swayam.kbcss.service;

import com.swayam.kbcss.config.CycleEnum;
import com.swayam.kbcss.config.CycleProperties;
import com.swayam.kbcss.request.Request;
import com.swayam.kbcss.response.Response;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class CycleServiceImpl  implements CycleService{
  private Logger logger = LoggerFactory.getLogger(CycleServiceImpl.class);
    @Autowired
    private CycleProperties cycleProperties;

  @SneakyThrows
  @Override
    public List<Response> calculatePrice(List<Request> requestList) {

      List<Response> resultList=new LinkedList<>();;
        for(Request request:requestList ){
            logger.info("  for(Request request:requestList )  ");
            Response response =null;
            ExecutorService service=Executors.newFixedThreadPool(10);
            logger.info("---ExecutorService---");

            Set<String> cycleComponents = null;
            cycleComponents = new LinkedHashSet<>();
            //check elements and store in Set
            for (String cycleComponent : request.getCycleComponents()) {

                if (CycleEnum.contains(cycleComponent))
                    cycleComponents.add(cycleComponent);
                logger.info("  for (String cycleComponent : request.getCycleComponents())  ");

            }
            Future<Double> price=CompletableFuture.supplyAsync(()->calculatePrice( request));

            response=new Response();
            response.setCycleComponent(cycleComponents);
            response.setPriceOfCycle(price.get());
            resultList.add(response);
        }
         return resultList;
    }//End- calculatePrice(List<Request> requestList)


    @Async
    @Override
    public double calculatePrice(Request request) {

        LocalDate dateForRef = LocalDate.of(2016, 11, 30);
        LocalDate dateOfPrice =  request.getDateOfPricing();//LocalDate.of(2010, 01, 31);

        //calculate Tyre price
        double priceOfTyre = dateForRef.isBefore(dateOfPrice) ?
                cycleProperties.getWheel().getTyreAfterPrice():cycleProperties.getWheel().getTyreBeforePrice() ;
//
        logger.info("TyreAfterPrice() ",cycleProperties.getWheel().getTyreAfterPrice());
        logger.info("TyreBeforePrice() ",cycleProperties.getWheel().getTyreBeforePrice());
        logger.info(" priceOfTyre ",priceOfTyre);
//
        //calculate price of wheel
        double priceOfWheel = 4 * cycleProperties.getWheel().getSpokePrice() +
                cycleProperties.getWheel().getSpokePrice() +cycleProperties.getWheel().getTubePrice() + priceOfTyre;

        //calculate price of Cycle
        double priceofCycle = cycleProperties.getFramePrice() +
                              cycleProperties.getHandleWithBarkesPrice() +
                              cycleProperties.getSeatPrice() +
                              cycleProperties.getChainAssemblyPrice() +
                              (2 * priceOfWheel);

        return priceofCycle;

    }//End- calculatePrice(Request request)


}//End of class
