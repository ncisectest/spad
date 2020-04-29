package mainlibrary;

import java.sql.*;

public final class LibrarianDao {
    public static boolean validate(String name, String password) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Librarian where UserName=? and Password=?");
            ps.setString(1, name);
            ps.setString(2, password);

            rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(LibrarianDao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
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
}
