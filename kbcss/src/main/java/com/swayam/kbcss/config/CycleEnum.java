package com.swayam.kbcss.config;

public enum CycleEnum {


    FRAME  (1 ),
    HANDLEBARWITHBRAKES(2),
    SEAT(3),
    CHAIN(4),
    WHEELS(5),
    SPOKES(6),
    RIM(7),
    TUBE(8),
    TYRE(9);// semicolon needed when fields / methods follow

//TO-DO-- set the price of component by getting it from properties file
    private final int price;

    CycleEnum(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public static boolean contains(String cycleComponent) {

        for ( CycleEnum component : CycleEnum.values()) {
            if (component.name().equalsIgnoreCase(cycleComponent)) {
                return true;
            }
        }
        return false;
    }
}
/*

public enum CycleEnum {


    @Component
    public class CycleEnumInjector {
        @Autowired
        private CycleProperties cycleProperties;

        @PostConstruct
        public void postConstruct() {
            for (CycleEnum cEnum : EnumSet.allOf(CycleEnum.class))
                cEnum.setPrice(cycleProperties);
        }
    }

    private void setPrice(CycleProperties cycleProperties) {
    }

    FRAME(1),

    HANDLEBARWITHBRAKES(2),

    SEAT(1),

    CHAIN(2),

    WHEELS(3),

    SPOKES(4),

    RIM(5),

    TUBE(5),

    TYRE(6);// semicolon needed when fields / methods follow
}*/
