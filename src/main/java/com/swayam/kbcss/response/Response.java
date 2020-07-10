package com.swayam.kbcss.response;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class Response {
  /* private Set<String> cycleComponent ;*/
   private Map<String,Double> cycleComponentMap ;
   private double priceOfCycle;
}
