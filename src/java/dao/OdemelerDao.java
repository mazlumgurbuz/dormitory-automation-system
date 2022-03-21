package dao;

import entity.Odemeler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;


public class OdemelerDao extends BaseDao {

    
       public int count (String searchTerm) {
            int count = 0 ;
        
        try {

         String query = "select count(odeme_id) as aa from odemeler";
			if (searchTerm != null) {
				query += " where ogrenciAdi like ? ";
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

    public List<Odemeler> findAll(int page , int pageSize , String searchTerm) {
        List<Odemeler> odemelerList = new ArrayList<>();
        int start = (page-1)* pageSize ;
        //Connector connector = new Connector();
        try {
             String query = "select * from odemeler";

			if (searchTerm != null) {
				query += " where ogrenciAdi like ? ";
			}

			query += " order by odeme_id asc limit ? offset ?";
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
                Odemeler tmp = new Odemeler(rs.getLong("odeme_id"), rs.getString("ogrenciAdi"),rs.getInt("ucret"));
                odemelerList.add(tmp);
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odemelerList;
    }
    public Odemeler find(Long id){
        Odemeler bb = null;
        try{
            PreparedStatement st = this.getConnection().prepareStatement("select * from odemeler where odeme_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            bb = new Odemeler();
            bb.setOdeme_id(rs.getLong("odeme_id"));
            bb.setOgrenciAdi(rs.getString("ogrenciAdi"));
            bb.setUcret(rs.getInt("ucret"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bb;
    }

    public void insert(Odemeler odemeler) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into odemeler (ogrenciAdi,ucret) values (?,?)");
            pst.setString(1, odemeler.getOgrenciAdi());
            pst.setInt(2, odemeler.getUcret());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Odemeler odemeler) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from odemeler where odeme_id=?");
            pst.setLong(1, odemeler.getOdeme_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Odemeler odemeler) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update odemeler set ogrenciAdi=?, ucret=? where odeme_id=?");
            pst.setString(1, odemeler.getOgrenciAdi());
            pst.setInt(2, odemeler.getUcret());
            pst.setLong(3, odemeler.getOdeme_id());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(OdemelerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
