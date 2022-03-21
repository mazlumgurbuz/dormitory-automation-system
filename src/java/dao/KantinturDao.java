package dao;

import entity.Kantintur;
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

public class KantinturDao extends BaseDao {

   
    public List<Kantintur> findAll() {
        List<Kantintur> kantinturList = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from kantin_tur");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Kantintur tmp = new Kantintur();
                tmp.setTur_id(rs.getLong("tur_id"));
                tmp.setTur_isim(rs.getString("tur_isim"));

                kantinturList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kantinturList;
    }

    public Kantintur find(Long id) {
        Kantintur pt = null;
        try {
            PreparedStatement st = this.getConnection().prepareStatement("select * from kantin_tur where tur_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            pt = new Kantintur();
            pt.setTur_id(rs.getLong("tur_id"));
            pt.setTur_isim(rs.getString("tur_isim"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pt;
    }

   
}
