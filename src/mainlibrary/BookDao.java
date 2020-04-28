package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDao {
public static int save(String callno,String name,String author,String publisher,int quantity){
	int status=0;

    Connection Con = null;
    PreparedStatement ps = null;

	try {
        Con=DB.getConnection();
		ps=Con.prepareStatement("insert into books(callno,name,author,publisher,quantity) values(?,?,?,?,?)");
		ps.setString(1,callno);
		ps.setString(2,name);
		ps.setString(3,author);
		ps.setString(4,publisher);
		ps.setInt(5,quantity);
		status=ps.executeUpdate();
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

    /**
     *
     * @param Publisher
     * @return
     */
    public static boolean PublisherValidate( String Publisher)
{
    boolean status = false;

    Connection Con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        Con = DB.getConnection();
        ps = Con.prepareStatement("select * from Publisher where PublisherName = ?");
        ps.setString(1, Publisher);
        rs=ps.executeQuery();
        status=rs.next();
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
    
    public static int AddPublisher( String Publisher)
    {
        int status= 0;

        Connection Con = null;
        PreparedStatement ps = null;

        try {
            Con = DB.getConnection();
            ps=Con.prepareStatement("insert into Publisher(PublisherName) values(?)");
            ps.setString(1,Publisher);
            status=ps.executeUpdate();
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


    public static boolean CheckIfBookExist(String BookName, String Author) {
        boolean status = false;

        Connection Con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Con = DB.getConnection();
            ps = Con.prepareStatement("select * from Books where BookName=? and Author=?");
            ps.setString(1, BookName);
            ps.setString(2, Author);
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


    public static int SaveBook(String BookN, String AuthorN, String PublisherN, String ShelfN, String RowN, String GenreN) {
        int status= 0;

        Connection Con = null;
        PreparedStatement ps = null;

        try {
            Con = DB.getConnection();
            ps=Con.prepareStatement("insert into Books(BookName,Author,Genre,Publisher,Shelf, Row) values(?,?,?,?,?,?)");
            ps.setString(1,BookN);
            ps.setString(2, AuthorN);
            ps.setString(3, GenreN);
            ps.setString(4, PublisherN);
            ps.setString(5, ShelfN);
            ps.setString(6, RowN);
            status=ps.executeUpdate();
	    } catch(Exception e){
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
    
    public static int Delete(int BookID) {
        int status= 0;

        Connection Con = null;
        PreparedStatement ps = null;

        try {
            Con = DB.getConnection();
            ps=Con.prepareStatement("DELETE FROM Books where BookID=?");
            ps.setInt(1,BookID);
            status=ps.executeUpdate();
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
