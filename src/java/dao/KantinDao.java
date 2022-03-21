package dao;

import entity.Kantin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import util.DBConnection;


public class KantinDao  extends BaseDao{
    
   
    
    private KantinturDao kantinturDao;
    
      public int count (String searchTerm) {
            int count = 0 ;
        
        try {
      
         String query = "select count(malzeme_id) as aa from kantin";
			if (searchTerm != null) {
				query += " where isim like ? ";
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
    
    public List<Kantin> findAll(int page , int pageSize , String searchTerm) {
        List<Kantin> kantinList = new ArrayList<>();
        int start = (page-1)* pageSize ;
        try{
           String query = "select * from kantin";

			if (searchTerm != null) {
				query += " where isim like ? ";
			}

			query += " order by malzeme_id asc limit ? offset ?";
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
            
            while(rs.next()){
                Kantin tmp = new Kantin();
                tmp.setMalzeme_id(rs.getLong("malzeme_id"));
                tmp.setIsim(rs.getString("isim"));
                
                tmp.setKantintur(this.getKantinturDao().find(rs.getLong("tur_id")));
                kantinList.add(tmp);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return kantinList;
    }
     public void remove(Kantin kantin) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("delete from kantin where malzeme_id=?");
            pst.setLong(1, kantin.getMalzeme_id());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    
    public void create(Kantin kantin) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into kantin (isim,tur_id) values (?,?)");
            pst.setString(1, kantin.getIsim());
            pst.setLong(2, kantin.getKantintur().getTur_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    }
    public void edit(Kantin kantin) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("update kantin set isim=?, tur_id=? where malzeme_id=?");
            pst.setString(1, kantin.getIsim());
            pst.setLong(2, kantin.getKantintur().getTur_id());
            pst.setLong(3, kantin.getMalzeme_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public KantinturDao getKantinturDao() {
        if(this.kantinturDao == null){
            this.kantinturDao = new KantinturDao();
        }
        return kantinturDao;
    }
    
  
}
