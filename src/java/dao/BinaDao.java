package dao;

import entity.Bina;
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

public class BinaDao extends BaseDao  {

  
 

    public List<Bina> findAll(int page, int pageSize) {
        List<Bina> binaList = new ArrayList<>();
        //Connector connector = new Connector();
        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from bina order by bina_id asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Bina tmp = new Bina(rs.getLong("bina_id"), rs.getString("oda_sayisi"), rs.getString("adres"));
                binaList.add(tmp);
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return binaList;
    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(bina_id) as bina_count from bina");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("bina_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public Bina find(Long id) {
        Bina bb = null;
        try {
            PreparedStatement st = this.getConnection().prepareStatement("select * from bina where bina_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            bb = new Bina();
            bb.setBina_id(rs.getLong("bina_id"));
            bb.setOda_sayisi(rs.getString("oda_sayisi"));
            bb.setAdres(rs.getString("adres"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PersonelturDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bb;
    }

    public void insert(Bina bina) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into bina (oda_sayisi,adres) values (?,?)");
            pst.setString(1, bina.getOda_sayisi());
            pst.setString(2, bina.getAdres());

            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Bina bina) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from bina where bina_id=?");
            pst.setLong(1, bina.getBina_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Bina bina) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update bina set oda_sayisi=?, adres=? where bina_id=?");
            pst.setString(1, bina.getOda_sayisi());
            pst.setString(2, bina.getAdres());
            pst.setLong(3, bina.getBina_id());

            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(BinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
