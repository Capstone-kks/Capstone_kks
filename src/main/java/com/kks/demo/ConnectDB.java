package com.kks.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
