package com.swayam.kbcss.config;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Configuration
@ConfigurationProperties("cycle")
/*@PropertySource("classpath:/cycle.yml")*/
public class Cycle {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateforReference;

    private double framePrice;
    private double handleWithBarkesPrice;
    private double seatPrice;
    private double chainAssemblyPrice;
    private Wheel wheel;
    @Data
    public static class Wheel {
        private double spokePrice;
        private double rimPrice;
        private double tubePrice;
        private double tyreBeforePrice;
        private double tyreAfterPrice;

    }
}
