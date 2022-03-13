package com.kks.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@PropertySource("classpath:application.properties")
public class ConnectDB {
    public void main(String[] args){
        String url = "jdbc:mysql://database-1.cltih7jbgequ.ap-northeast-2.rds.amazonaws.com/mydb?autoReconnect=true&useSSL=false";
        String user = "admin";
        String password = "123456789A!";

        //jdbc:mysql://database-1.cltih7jbgequ.ap-northeast-2.rds.amazonaws.com:3306/?user=admin

        try{
            Connection myConn = DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
