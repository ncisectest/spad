package mainlibrary;

import java.sql.*;
import javax.swing.JTextField;

public class TransBookDao {

    public static boolean checkBook(String bookcallno) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Books where BookID=?");
            ps.setString(1, bookcallno);
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

    public static int updatebook(String bookcallno) {
        int status = 0;
        int quantity = 0, issued = 0;

        Connection Con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();

            ps1 = Con.prepareStatement("select quantity,issued from books where callno=?");
            ps1.setString(1, bookcallno);
            rs = ps1.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
                issued = rs.getInt("issued");
            }

            if (quantity > 0) {
                ps2 = Con.prepareStatement("update books set quantity=?,issued=? where callno=?");
                ps2.setInt(1, quantity - 1);
                ps2.setInt(2, issued + 1);
                ps2.setString(3, bookcallno);
                status = ps2.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                rs.close();
                ps1.close();
                ps2.close();
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
        boolean status = false;
        int num = 0;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Book_Count where UserID=?");
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
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
