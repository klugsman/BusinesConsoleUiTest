package com.optal.hooks;


import cucumber.api.java.Before;

import java.io.File;

public class GlobalHooks {

    private static boolean dunit = false;
    private String dirPath = File.separator;





    @Before(order = 1)
    public static void beforeAllTests() {
        if (!dunit) {
//            Code to run once at the beginning of the entire Test
            System.out.println("Run once at start time.........................");
            //Write any code that needs to run once and for at the very beginning.

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
//           Codes to run at the end of the entire Test
                    System.out.println("Calling System.exit() ..................");
                }
            });
        }
        dunit = true;
    }

}
