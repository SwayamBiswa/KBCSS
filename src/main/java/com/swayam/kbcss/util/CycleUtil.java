package com.swayam.kbcss.util;

import com.swayam.kbcss.config.CycleProperties;
import com.swayam.kbcss.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class CycleUtil {

    Map<String, Double> cycleMap = null;

    @Autowired
    private CycleProperties cycleProperties;

    public Map<String, Double> builder(Request request) {
        //  Map<String,Double> cycleMap=null;

        LocalDate dateForRef = LocalDate.of(2016, 11, 30);
        LocalDate dateOfPricing = request.getDateOfPricing();

        cycleMap = new LinkedHashMap<>();
        cycleMap.put("FRAME", cycleProperties.getFramePrice());
        cycleMap.put("HANDLEBARWITHBRAKES", cycleProperties.getHandleWithBarkesPrice());
        cycleMap.put("SEAT", cycleProperties.getSeatPrice());
        cycleMap.put("CHAIN", cycleProperties.getChainAssemblyPrice());

        cycleMap.put("SPOKES", cycleProperties.getWheel().getSpokePrice());
        cycleMap.put("RIM", cycleProperties.getWheel().getRimPrice());
        cycleMap.put("TUBE", cycleProperties.getWheel().getTubePrice());

        double priceOfTyre = dateForRef.isBefore(dateOfPricing) ?
                cycleProperties.getWheel().getTyreAfterPrice() : cycleProperties.getWheel().getTyreBeforePrice();
        cycleMap.put("TYRE", priceOfTyre);

        //calculate price of wheel
        double priceOfWheel = 4 * cycleMap.get("SPOKES") +
                cycleMap.get("RIM") +
                cycleMap.get("TUBE") +
                cycleMap.get("TYRE");
        cycleMap.put("WHEELS", priceOfWheel);


       /* //calculate price of Cycle
        double priceofCycle =
                cycleMap.get("FRAME")+
                        cycleMap.get("HANDLEBARWITHBRAKES")+
                        cycleMap.get("SEAT")+
                        cycleMap.get("CHAIN")+
                        (2 * cycleMap.get("WHEELS"));*/


        return cycleMap;
    }//End of builder(Request request)
}