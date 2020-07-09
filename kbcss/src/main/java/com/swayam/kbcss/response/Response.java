package com.swayam.kbcss.response;

import lombok.Data;

import java.util.Set;

@Data
public class Response {
    Set<String> cycleComponent ;
    double priceOfCycle;
}
