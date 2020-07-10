package com.swayam.kbcss.response;

import lombok.Data;

import java.util.Set;

@Data
public class Response {
   private Set<String> cycleComponent ;
   private double priceOfCycle;
}
