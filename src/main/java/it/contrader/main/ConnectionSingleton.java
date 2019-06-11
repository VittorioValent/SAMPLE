package it.contrader.main;

import java.io.FileInputStream;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class ConnectionSingleton {


    private static Connection connection = null;


    private ConnectionSingleton() {
    }


    public static Connection getInstance() {
        if (connection == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = new FileInputStream("config.properties");
                properties.load(inputStream);
                String vendor = properties.getProperty("db.vendor");
                String driver = properties.getProperty("db.driver");
                String host = properties.getProperty("db.host");
                String port = properties.getProperty("db.port");
                String dbName = properties.getProperty("db.name");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                String jdbcAdditionalParams=properties.getProperty("db.jdbc_params");
                //Class c = Class.forName(driver);
                
                
    			Class<?> c = Class.forName(driver);
    			System.out.println("Ho caricato: " + c.getName());
                String url = "jdbc:" + vendor + "://" + host + ":" + port + "/" + dbName+"?"+jdbcAdditionalParams;
    			connection = (Connection) DriverManager.getConnection(url, username, password);

            
            } catch (Exception e) {
        
            }
        }
        return connection;
    }

}
