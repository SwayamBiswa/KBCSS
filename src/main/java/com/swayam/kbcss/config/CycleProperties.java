package com.swayam.kbcss.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties("cycle")
/*@PropertySource("classpath:/cycle.yml")*/
public class CycleProperties {

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
