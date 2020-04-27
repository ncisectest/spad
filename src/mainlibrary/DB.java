/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author bikash
 */
public class DB {
    public static Connection getConnection() {
        Connection con = null;
        try (InputStream input = new FileInputStream("/home/b14ckh34rt/Workspace/projects/spad/src/mainlibrary/config.properties")) {
            Properties props = new Properties();
            props.load(input);
            props.put("useUnicode", "true");
            props.put("useServerPrepStmts", "false"); // use client-side prepared statement
            props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here

            Class.forName("com.mysql.jdbc.Driver");
            String connection = props.getProperty("connection");
            con = DriverManager.getConnection(connection, props);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
