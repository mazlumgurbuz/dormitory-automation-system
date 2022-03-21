 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import entity.Kutuphane;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;


public class KutuphaneDao  extends BaseDao{

  

   
    //instans dao 
    private LanguageDao languageDao;
    //instanse list many to many
    private CategoryDao categoryDao;

    public void create(Kutuphane kutuphane) {
        try {
            // Statement st = this.getConnection().createStatement();
            PreparedStatement pst = this.getConnection().prepareStatement("insert into kutuphane (kitab_adi , description , year ,language_id) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            // st.executeUpdate("insert into kutuphane (kitab_adi , description , year,language_id) values ('" + kutuphane.getKitab_adi() + "','" + kutuphane.getDescription() + "', '" + kutuphane.getYear() + "','" + selectedLanguage + "')", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kutuphane.getKitab_adi());
            pst.setString(2, kutuphane.getDescription());
            pst.setInt(3, kutuphane.getYear());
            pst.setLong(4, kutuphane.getLanguage().getLanguage_id());

            pst.executeUpdate();
            Long kitab_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                kitab_id = gk.getLong(1);
            }

            for (Category c : kutuphane.getKutuphaneCategories()) {
                //Statement st2 = this.getConnection().createStatement();
                pst = this.getConnection().prepareStatement("insert into kutup_category (kitab_id , category_id) values(?,?)");
                //st2.executeUpdate("insert into kutup_category (kitab_id , category_id) values (" + kitab_id + "," + l + ")");
                pst.setLong(1, kitab_id);
                pst.setLong(2, c.getCategory_id());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void edit(Kutuphane kutuphane) {

        try {
            // Statement st = this.getConnection().createStatement();
            PreparedStatement pst = this.getConnection().prepareStatement("update kutuphane set kitab_adi=? , description=? , year=? , language_id=?  values (?,?,?,?) where kitab_id=? ");
            // st.executeUpdate("insert into kutuphane (kitab_adi , description , year,language_id) values ('" + kutuphane.getKitab_adi() + "','" + kutuphane.getDescription() + "', '" + kutuphane.getYear() + "','" + selectedLanguage + "')", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kutuphane.getKitab_adi());
            pst.setString(2, kutuphane.getDescription());
            pst.setInt(3, kutuphane.getYear());
            pst.setLong(4, kutuphane.getLanguage().getLanguage_id());
            pst.setLong(5, kutuphane.getKitab_id());

            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from kutup_category where kitab_id=? ");
            pst.setLong(1, kutuphane.getKitab_id());
            
            pst.executeUpdate();

            for (Category c : kutuphane.getKutuphaneCategories()) {
                //Statement st2 = this.getConnection().createStatement();
                pst = this.getConnection().prepareStatement("insert into kutup_category (kitab_id , category_id) values (?,?) ");
                //st2.executeUpdate("insert into kutup_category (kitab_id , category_id) values (" + kitab_id + "," + l + ")");
                pst.setLong(1, kutuphane.getKitab_id());
                pst.setLong(2, c.getCategory_id());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void remove(Kutuphane kutuphane) {

        try {
            //ilikleri kaldırmak icin
            PreparedStatement pst = this.getConnection().prepareStatement("delete from kutup_category where kitab_id=?");
            pst.setLong(1, kutuphane.getKitab_id());
            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from kutuphane where kitab_id=?");
            pst.setLong(1, kutuphane.getKitab_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Kutuphane> findAll(int page , int pageSize , String searchTerm  ) {
        List<Kutuphane> kutuphaneList = new ArrayList<>();
        int start = (page-1)* pageSize ;
        try {
            
           /* PreparedStatement pst = this.getConnection().prepareStatement("select * from kutuphane order by kitab_id asc limit "+start+" , "+pageSize);
            // Statement st = this.getConnection().createStatement();
            // ResultSet rs = st.executeQuery("select * from kutuphane");
            ResultSet rs = pst.executeQuery();*/
            String query = "select * from kutuphane";

			if (searchTerm != null) {
				query += " where kitab_adi like ? ";
			}

			query += " order by kitab_id asc limit ? offset ?";
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
                Kutuphane tmp = new Kutuphane();
                tmp.setKitab_id(rs.getLong("kitab_id"));
                tmp.setKitab_adi(rs.getString("kitab_adi"));
                tmp.setDescription(rs.getString("description"));
                tmp.setYear(rs.getInt("year"));
                //many to one 
                tmp.setLanguage(this.getLanguageDao().find(rs.getLong("language_id")));
                //many to many 
                tmp.setKutuphaneCategories(this.getCategoryDao().getKutuphaneCategories(tmp.getKitab_id()));
                kutuphaneList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kutuphaneList;
    }
    
    public int count (String searchTerm) {
            int count = 0 ;
        
        try {
            /*PreparedStatement pst = this.getConnection().prepareStatement("select count(kitab_id) as kitab_count from kutuphane ");
           
            ResultSet rs = pst.executeQuery();
            rs.next() ;
            count = rs.getInt("kitab_count") ;*/
            
             String query = "select count(kitab_id) as aa from kutuphane";
			if (searchTerm != null) {
				query += " where kitab_adi like ? ";
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

    public LanguageDao getLanguageDao() {
        if (this.languageDao == null) {
            this.languageDao = new LanguageDao();
        }
        return languageDao;
    }

    public void setLanguageDao(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

   

    public CategoryDao getCategoryDao() {
        if (this.categoryDao == null) {
            this.categoryDao = new CategoryDao();
        }
        return categoryDao;
    }

   
   
}
