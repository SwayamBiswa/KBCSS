package com.swayam.kbcss.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class Request {
    private String type="TUBE";
    private List<String> cycleComponents;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPricing;

}
