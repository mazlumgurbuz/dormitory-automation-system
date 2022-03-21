package dao;

import entity.Odalar;
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
public class OdalarDao extends BaseDao {
    
   
    
    private BinaDao binaDao;
    
    public List<Odalar> findAll(int page,int pageSize) {
        List<Odalar> odalarList = new ArrayList<>();
        
        int start=(page-1)*pageSize;
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("select * from odalar order by oda_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Odalar tmp = new Odalar();
                tmp.setOda_id(rs.getLong("oda_id"));
                tmp.setOda_no(rs.getInt("oda_no"));
               
                
                tmp.setBina(this.getBinaDao().find(rs.getLong("bina_id")));
                odalarList.add(tmp);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return odalarList;
    }
    public int count() {
       int count=0;
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("select count(oda_id) as odalar_count from odalar");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("odalar_count");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return count;
    }
    
    public Odalar find(Long id){
        Odalar bb = null;
        try{
            PreparedStatement st = this.getConnection().prepareStatement("select * from odalar where oda_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            bb = new Odalar();
            bb.setOda_id(rs.getLong("oda_id"));
            bb.setOda_no(rs.getInt("oda_no"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bb;
    }
     public void remove(Odalar odalar) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("delete from odalar where oda_id=?");
            pst.setLong(1, odalar.getOda_id());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
     

    
    public void create(Odalar odalar) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into odalar (oda_no,bina_id) values (?,?)");
            pst.setInt(1, odalar.getOda_no());
            pst.setLong(2, odalar.getBina().getBina_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    }
    public void edit(Odalar odalar) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("update odalar set oda_no=?, bina_id=? where oda_id=?");
            pst.setInt(1, odalar.getOda_no());
            pst.setLong(2, odalar.getBina().getBina_id());
            pst.setLong(3, odalar.getOda_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public BinaDao getBinaDao() {
        if(this.binaDao == null){
            this.binaDao = new BinaDao();
        }
        return binaDao;
    }
    
   
}
