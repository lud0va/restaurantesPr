/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Lucia
 */
@Singleton
@Log4j2
@Getter
public class Configuration {

    private static Configuration instance=null;
    private Properties p;

    private Configuration() {
        try {
            p = new Properties();
            p.load(Configuration.class.getClassLoader().getResourceAsStream("configFiles/properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {

        if (instance==null) {
            instance=new Configuration();

        }
        return instance;
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }

}
