package com.swayam.kbcss.config;

public enum CycleEnum {

    FRAME ,
    HANDLEBARWITHBRAKE,
    SEAT,
    CHAIN,
    WHEEL,
    SPOKE,
    RIM,
    TUBE,
    TYRE;// semicolon needed when fields / methods follow


    public static boolean contains(String cycleComponent) {

        for ( CycleEnum component : CycleEnum.values()) {
            if (component.name().equalsIgnoreCase(cycleComponent)) {
                return true;
            }
        }
        return false;
    }
}
