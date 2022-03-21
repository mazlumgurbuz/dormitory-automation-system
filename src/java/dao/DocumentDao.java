/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Document;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author Muhammed ARSLAN
 */
public class DocumentDao extends BaseDao {

    

    public int count (String searchTerm) {
            int count = 0 ;
        
        try {
       
         String query = "select count(id) as data from document";
			if (searchTerm != null) {
				query += " where name like ? ";
			}
			PreparedStatement st = this.getConnection().prepareStatement(query);
			
			if (searchTerm != null) {
				st.setString(1, "%"+searchTerm+"%");
			}
			ResultSet rs = st.executeQuery();

			rs.next();
			count = rs.getInt("data");
         
          

           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count ;
    }
    public List<Document> findAll(int page , int pageSize , String searchTerm ) {
        List<Document> dlist = new ArrayList<>();
        
        try { 
               String query = "select * from document";

			if (searchTerm != null) {
				query += " where name like ? ";
			}

			query += " order by id asc limit ? offset ?";
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
            while (rs.next())
            {
                Document d = new Document();
                d.setId(rs.getLong("id")) ;
                d.setFilePath(rs.getString("name"));
                d.setFileName(rs.getString("path"));
                d.setFileType(rs.getString("type"));
                
                dlist.add(d) ;
                
                
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return dlist;

    }
    public void insert (Document d) 
    {
        String query = "insert into document (name , path , type) values (?,?,?) " ;
                
        try { 
            PreparedStatement pst =   this.getConnection().prepareStatement(query) ;
            pst.setString(1 , d.getFileName());
            pst.setString(2 , d.getFilePath());
            pst.setString(3 , d.getFileType());
            
            pst.executeUpdate() ;
      
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
