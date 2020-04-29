/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.sql.SQLException;

/**
 *
 * @author bikash
 */

public class UsersDao {
    public static String CreateHash(String password)  {
        String argonhash = "";
        String saltedhash = "";

        try {
            Argon2 sut = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i,10,10);
            argonhash = sut.hash(2, 65536, 1, password.toCharArray());
            saltedhash = argonhash.substring(29);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(UsersDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return saltedhash;
    }

    public static boolean VerifyHash(String vhash, String password) {
        String finalhash = "";
        String metahash = "$argon2i$v=19$m=65536,t=2,p=1";
        finalhash = metahash+vhash;

        boolean status = false;
        try{
            Argon2 argon2 = Argon2Factory.create();
            status = argon2.verify(finalhash, password.toCharArray());
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(UsersDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return status;
    }

    public static boolean validate(String username, String password) {
        String saltedhash = "";
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select UserPass from Users where UserName = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            status = rs.next();

            if (status) {
                saltedhash = rs.getString(1);
            } else {
                saltedhash = "none";
            }
        }
        catch (SQLException e) {
            java.util.logging.Logger.getLogger(UsersDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(UsersDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
                ps.close();
                Con.close();
            }
            catch (Exception e) {
                /* ignore */
            }
        }
        return VerifyHash(saltedhash, password);
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
            java.util.logging.Logger.getLogger(UsersDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
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
            java.util.logging.Logger.getLogger(UsersDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
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

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("insert into Users(UserPass,RegDate,UserName,Email) values(?,?,?,?)");
            ps.setString(1, CreateHash(UserPass));
            ps.setString(2, Date);
            ps.setString(3, User);
            ps.setString(4, UserEmail);
            status = ps.executeUpdate();
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(UsersDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
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
