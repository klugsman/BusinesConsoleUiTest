package com.optal.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestData {


    public static String getValue(String keyName){
        if(keyName.isEmpty() || keyName.contains(" ")){
            keyName = keyName.replaceAll("\\s", ".");
        }else{
            throw new IllegalArgumentException("Not formatted properly");
        }

        String configPath = "/src/main/resources/testData/uk.properties";
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
