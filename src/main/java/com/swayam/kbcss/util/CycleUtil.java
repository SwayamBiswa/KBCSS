package com.swayam.kbcss.util;

import com.swayam.kbcss.config.Cycle;
import com.swayam.kbcss.request.Request;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration

public class CycleUtil {

    Map<String, Double> cycleMap = null;

    @Autowired
    private Cycle cycle;

    public Map<String, Double> builder(Request request) {

        LocalDate dateForRef = cycle.getDateforReference();
        LocalDate dateOfPricing = request.getDateOfPricing();

        String type= "TUBELESS".equalsIgnoreCase(request.getType()) ? "TUBELESS": "TUBE";

        cycleMap = new LinkedHashMap<>();
        cycleMap.put("FRAME", cycle.getFramePrice());
        cycleMap.put("HANDLEBARWITHBRAKES", cycle.getHandleWithBarkesPrice());
        cycleMap.put("SEAT", cycle.getSeatPrice());
        cycleMap.put("CHAIN", cycle.getChainAssemblyPrice());

        cycleMap.put("SPOKES", cycle.getWheel().getSpokePrice());
        cycleMap.put("RIM", cycle.getWheel().getRimPrice());
        cycleMap.put("TUBE", cycle.getWheel().getTubePrice());
        if("TUBELESS".equalsIgnoreCase(type)){
            cycleMap.put("TUBE",new Double("0"));
        }
        //calculate price of tyre
        double priceOfTyre = dateForRef.isBefore(dateOfPricing) ?
                cycle.getWheel().getTyreAfterPrice() : cycle.getWheel().getTyreBeforePrice();
        cycleMap.put("TYRE", priceOfTyre);

        //calculate price of wheel
        double priceOfWheel = 4 * cycleMap.get("SPOKES") +
                cycleMap.get("RIM") +
                cycleMap.get("TUBE") +
                cycleMap.get("TYRE");
        cycleMap.put("WHEELS", priceOfWheel);

        return cycleMap;
    }//End of builder(Request request)
}