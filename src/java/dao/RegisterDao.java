package dao;

import entity.User;
import filter.DBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;





public class RegisterDao extends BaseDao {

  
    public static  void insert(String uname, String password, String userTuru) {
     //   DBConnection.connect();
         
        DBCon.getConnection();
        try {
             Statement sts = DBCon.getConnection().createStatement();
            String sql = "INSERT INTO user(uname,password,userTuru,c,r,u,d) VALUES('" + uname + "','" + password + "','" + userTuru + "','" +0+ "','" + 0 + "','" + 0 + "','" + 0 + "')";
            sts.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
/*   try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into user (uname,password,userTuru,c,r,u,d) values (?,?,?,?,?,?,?)");
            pst.setString(1, uname);
            pst.setString(2, password);
            pst.setString(3, userTuru);
            
            pst.setLong(4, 0);
            pst.setLong(5, 0);
            pst.setLong(6, 0);
            pst.setLong(7, 0);
            
            

            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    
    
    
    
    
    
    
      /* public static boolean validate2(String user, String password) {
       
        PreparedStatement ps = null;

        try {
            con = DBCon.getConnection();
            ps = con.prepareStatement("Select uname,password from User where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DBCon.close(con);
        }
        return false;
    }
*/
}
