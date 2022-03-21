/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kurslar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;


public class KurslarDao extends BaseDao {
   
    
    public List<Kurslar> findAll(int page , int pageSize , String searchTerm) {
        List<Kurslar> kurslarList = new ArrayList<>();
          int start = (page-1)* pageSize ;
    
        try {
              String query = "select * from kurslar";

			if (searchTerm != null) {
				query += " where kurs_adi like ? ";
			}

			query += " order by kurs_id asc limit ? offset ?";
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
                Kurslar tmp = new Kurslar(rs.getLong("kurs_id"), rs.getString("kurs_adi"));
                kurslarList.add(tmp);
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(KurslarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kurslarList;
    }
    public Kurslar find(Long id){
        Kurslar bb = null;
        try{
            PreparedStatement st = this.getConnection().prepareStatement("select * from kurslar where kurs_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            bb = new Kurslar();
            bb.setKurs_id(rs.getLong("kurs_id"));
            bb.setKurs_adi(rs.getString("kurs_adi"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bb;
    }

    public void insert(Kurslar kurslar) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into kurslar (kurs_adi) values (?)");
            pst.setString(1, kurslar.getKurs_adi());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(KurslarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Kurslar kurslar) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from kurslar where kurs_id=?");
            pst.setLong(1, kurslar.getKurs_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(KurslarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Kurslar kurslar) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update kurslar set kurs_adi=? where kurs_id=?");
            pst.setString(1, kurslar.getKurs_adi());
            pst.setLong(2, kurslar.getKurs_id());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(KurslarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
      public int count (String searchTerm) {
            int count = 0 ;
        
        try {
      
         String query = "select count(kurs_id) as k from kurslar";
			if (searchTerm != null) {
				query += " where kurs_adi like ? ";
			}
			PreparedStatement st = this.getConnection().prepareStatement(query);
			
			if (searchTerm != null) {
				st.setString(1, "%"+searchTerm+"%");
			}
			ResultSet rs = st.executeQuery();

			rs.next();
			count = rs.getInt("k");
         
          

           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count ;
    }
}
