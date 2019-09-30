package com.optal.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EnvConfig {

    public static String getValue(String keyName){
        String env = System.getProperty("env");
        String configPath = "/src/main/resources/envs/"+env+".properties";
        String sysPath = System.getProperty("user.dir");
        Properties config = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(sysPath+configPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config.getProperty(keyName);
    }


}
