package dao;


import entity.Ogrenci;
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

/**
 *
 * @author mazlum gurbuz
 */
public class OgrenciDao extends BaseDao {
    
  
    private OdalarDao odalarDao;
    
    public List<Ogrenci> findAll(int page,int pageSize , String searchTerm ) {
 {
        List<Ogrenci> ogrenciList = new ArrayList<>();
        
        int start=(page-1)*pageSize;
        
        try{
            String query = "select * from ogrenci";

			if (searchTerm != null) {
				query += " where adi like ? ";
			}

			query += " order by ogr_id asc limit ? offset ?";
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
                Ogrenci tmp = new Ogrenci();
                tmp.setOgr_id(rs.getLong("ogr_id"));
                tmp.setAdi(rs.getString("adi"));
                tmp.setSoyadi(rs.getString("soyadi"));
               
                
                tmp.setOdalar(this.getOdalarDao().find(rs.getLong("oda_id")));
                //tmp.setOdalar(this.getOdalarDao().getBinaDao().find(rs.getLong("bina_id")));
                ogrenciList.add(tmp);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return ogrenciList;
    }
    }
 
    public int count(String searchTerm) {
       int count=0;
        try{
           String query = "select count(ogr_id) as aa from ogrenci";
			if (searchTerm != null) {
				query += " where adi like ? ";
			}
			PreparedStatement st = this.getConnection().prepareStatement(query);
			
			if (searchTerm != null) {
				st.setString(1, "%"+searchTerm+"%");
			}
			ResultSet rs = st.executeQuery();

			rs.next();
			count = rs.getInt("aa");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return count;
    }
     public void remove(Ogrenci ogrenci) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("delete from ogrenci where ogr_id=?");
            pst.setLong(1, ogrenci.getOgr_id());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    
    public void create(Ogrenci ogrenci) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into ogrenci (adi,soyadi,oda_id) values (?,?,?)");
            pst.setString(1, ogrenci.getAdi());
            pst.setString(2, ogrenci.getSoyadi());
            pst.setLong(3, ogrenci.getOdalar().getOda_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void edit(Ogrenci ogrenci) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("update ogrenci set adi=?,soyadi=?,oda_id=? where ogr_id=?");
            pst.setString(1, ogrenci.getAdi());
            pst.setString(2, ogrenci.getSoyadi());
            pst.setLong(3, ogrenci.getOdalar().getOda_id());
            pst.setLong(4, ogrenci.getOgr_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public OdalarDao getOdalarDao() {
        if(this.odalarDao == null){
            this.odalarDao = new OdalarDao();
        }
        return odalarDao;
    }
   
}
