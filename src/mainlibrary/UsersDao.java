/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author bikash
 */
public class UsersDao {

    public static boolean validate(String name, String password) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Users where UserName=? and UserPass=?");
            ps.setString(1, name);
            ps.setString(2, password);

            rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                rs.close();
                ps.close();
                Con.close();
            }
            catch (Exception e) {
                /* ignored */
            }
        }
        return status;
    }

    public static boolean CheckIfUserNameAlready(String UserName) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Users where UserName=?");
            ps.setString(1, UserName);

            rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                rs.close();
                ps.close();
                Con.close();
            }
            catch (Exception e) {
                /* ignored */
            }
        }
        return status;
    }

    public static boolean CheckIfEmailAlready(String EmailAddress) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Users where Email=?");
            ps.setString(1, EmailAddress);

            rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                rs.close();
                ps.close();
                Con.close();
            }
            catch (Exception e) {
                /* ignored */
            }
        }
        return status;
    }

    public static int AddUser(String User, String UserPass, String UserEmail, String Date) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        int status = 0;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("insert into Users(UserPass,RegDate,UserName,Email) values(?,?,?,?)");
            ps.setString(1, UserPass);
            ps.setString(2, Date);
            ps.setString(3, User);
            ps.setString(4, UserEmail);
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                Con.close();
            }
            catch (Exception e) {
                /* ignored */
            }
        }
        return status;
    }
}
