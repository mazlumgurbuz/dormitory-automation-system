package dao;

import entity.Personel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author mazlum gurbuz
 */
public class PersonelDao extends BaseDao {
    
   
    private PersonelturDao personelturDao;
    
    public List<Personel> findAll(int page,int pageSize ,  String searchTerm) {
        List<Personel> personelList = new ArrayList<>();
        
        int start=(page-1)*pageSize;
        
        try{
          /*  PreparedStatement pst = this.getC().prepareStatement("select * from personel order by personel_id asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();*/
           String query = "select * from personel";

			if (searchTerm != null) {
				query += " where isim like ? ";
			}

			query += " order by personel_id asc limit ? offset ?";
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
                Personel tmp = new Personel();
                tmp.setPersonel_id(rs.getLong("personel_id"));
                tmp.setIsim(rs.getString("isim"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setTc(rs.getString("tc"));
                
                tmp.setPersoneltur(this.getPersonelturDao().find(rs.getLong("tur_id")));
                personelList.add(tmp);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return personelList;
    }
    public int count(String searchTerm) {
       int count=0;
        
        try{
          /*  PreparedStatement pst = this.getC().prepareStatement("select count(personel_id) as personel_count from personel");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("personel_count");*/
                String query = "select count(personel_id) as aa from personel";
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
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return count;
    }
     public void remove(Personel personel) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("delete from personel where personel_id=?");
            pst.setLong(1, personel.getPersonel_id());
            pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    
    public void create(Personel personel) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into personel (isim,soyad,tc,tur_id) values (?,?,?,?)");
            pst.setString(1, personel.getIsim());
            pst.setString(2, personel.getSoyad());
            pst.setString(3, personel.getTc());
            pst.setLong(4, personel.getPersoneltur().getTur_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    }
    public void edit(Personel personel) {
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("update personel set isim=?, soyad=?, tc=?, tur_id=? where personel_id=?");
            pst.setString(1, personel.getIsim());
            pst.setString(2, personel.getSoyad());
            pst.setString(3, personel.getTc());
            pst.setLong(4, personel.getPersoneltur().getTur_id());
            pst.setLong(5, personel.getPersonel_id());
            
            pst.executeUpdate();
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public PersonelturDao getPersonelturDao() {
        if(this.personelturDao == null){
            this.personelturDao = new PersonelturDao();
        }
        return personelturDao;
    }
    

}
