package mainlibrary;

import java.sql.*;
import javax.swing.JTextField;

public class TransBookDao {
    public static boolean BookValidate(String BookID) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Books where BookID = ?");
            ps.setString(1, BookID);
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

    public static boolean UserValidate(String UserID) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Users where UserID = ?");
            ps.setString(1, UserID);
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

    public static int IssueBook(int BookID, int UserID, String IDate, String RDate) {
        int status = 0;

        Connection Con = null;
        PreparedStatement ps = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("insert into IssuedBook values(?,?,?,?)");
            ps.setInt(1, BookID);
            ps.setInt(2, UserID);
            ps.setString(3, IDate);
            ps.setString(4, RDate);
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

    public static int ReturnBook(int BookID, int UserID) {
        int status = 0;

        Connection Con = null;
        PreparedStatement ps = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("delete from IssuedBook where BookID=? and UserID=?");
            ps.setInt(1, BookID);
            ps.setInt(2, UserID);
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

    public static boolean CheckIssuedBook(int BookID) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from IssuedBook  where BookID=?");
            ps.setInt(1, BookID);
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

    public static boolean CheckIssuedUserBook(int BookID, int UserID) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from IssuedBook  where BookID=? and UserID=?");
            ps.setInt(1, BookID);
            ps.setInt(2, UserID);
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

    public static int Check(int UserID) {
        int num = 0;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Book_Count where UserID=?");
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
            boolean status = rs.next();
            num = rs.getInt("BookNo");
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
        if (num >= 5) {
            return 0;
        } else {
            return 1;
        }
    }
}
