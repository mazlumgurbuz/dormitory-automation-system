/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.YemekEk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.DBConnection;


public class YemekEKDao extends BaseDao {
    


    public List<YemekEk> findAll(int page , int pageSize , String searchTerm) {
        List<YemekEk> yList = new ArrayList<>();
  int start = (page-1)* pageSize ;
        try {

              String query = "select * from yemek";

			if (searchTerm != null) {
				query += " where ek_isim like ? ";
			}

			query += " order by ek_id asc limit ? offset ?";
			PreparedStatement st = this.getConnection().prepareStatement(query);

			if (searchTerm != null) {
				
				st.setString(1, "%"+searchTerm+"%");
				st.setInt(2, pageSize);
				st.setInt(3, (page - 1) * pageSize);
			} else {
				st.setInt(1, pageSize);
				st.setInt(2, (page - 1) * pageSize);
			}
			ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //  System.out.println(rs.getString("yemek_adi"));
                YemekEk t = new YemekEk();
                t.setEk_id(rs.getLong("ek_id"));
                t.setEk_isim(rs.getString("ek_isim"));
                yList.add(t);
            }
        } catch (SQLException ex) {

            Logger.getLogger(YemekEKDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return yList;
    }

    public void insert(YemekEk Y_ek) {
   

        try {

            Statement st = this.getConnection().createStatement();
            st.executeUpdate("insert into yemek (ek_isim) values('" + Y_ek.getEk_isim() + "')");

        } catch (SQLException ex) {

            Logger.getLogger(YemekEKDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(YemekEk yek) {
      

        try {

            Statement st = this.getConnection().createStatement();
            st.executeUpdate("delete from yemek where ek_id=" + yek.getEk_id());

        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            //Logger.getLogger(y_ekDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(YemekEk Y_ek) {
      

        try {

            Statement st = this.getConnection().createStatement();
            st.executeUpdate("update yemek set ek_isim='"+Y_ek.getEk_isim()+"' where ek_id= "+Y_ek.getEk_id());

        } catch (SQLException ex) {

            Logger.getLogger(YemekEKDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
     public int count (String searchTerm) {
            int count = 0 ;
        
        try {
      
         String query = "select count(ek_id) as aa from yemek";
			if (searchTerm != null) {
				query += " where ek_isim like ? ";
			}
			PreparedStatement st = this.getConnection().prepareStatement(query);
			
			if (searchTerm != null) {
				st.setString(1, "%"+searchTerm+"%");
			}
			ResultSet rs = st.executeQuery();

			rs.next();
			count = rs.getInt("aa");
         
          

           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count ;
    }

}
