package com.swayam.kbcss.response;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
   private Map<String,Double> cycleComponentMap ;
   private double priceOfCycle;
}
