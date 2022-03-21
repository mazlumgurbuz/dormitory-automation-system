/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.duyuru;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import util.DBConnection;

public class duyuruDao extends BaseDao {


    private turdao turdao;

    public List<duyuru> findAll(int page, int pageSize, String searchTerm) {
        List<duyuru> dList = new ArrayList();
        int start = (page - 1) * pageSize;
        try {

            String query = "select * from duyurular";

            if (searchTerm != null) {
                query += " where bilgi like ? ";
            }

            query += " order by duyuru_id asc limit ? offset ?";
            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (searchTerm != null) {

                st.setString(1, "%" + searchTerm + "%");
                st.setInt(2, pageSize);
                st.setInt(3, (page - 1) * pageSize);
            } else {
                st.setInt(1, pageSize);
                st.setInt(2, (page - 1) * pageSize);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                duyuru tmp = new duyuru();
                tmp.setDuyuru_id(rs.getLong("duyuru_id"));
                tmp.setBilgi(rs.getString("bilgi"));

                rs.getInt("duyurlar_turu");
                tmp.setDuyurlar_turu(this.getTurdao().find(rs.getLong("duyurlar_turu")));
                dList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dList;
    }

    public void create(duyuru dyr) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into duyurular(bilgi,duyurlar_turu) values(?,?)");
            pst.setString(1, dyr.getBilgi());
            pst.setLong(2, dyr.getDuyurlar_turu().getTur_id());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void edit(duyuru dyr) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update duyurular set bilgi=?,duyurlar_turu=? where duyuru_id=?");

            pst.setLong(3, dyr.getDuyuru_id());
            pst.setString(1, dyr.getBilgi());
            pst.setLong(2, dyr.getDuyurlar_turu().getTur_id());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void remove(duyuru dyr) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from duyurular where duyuru_id=?");
            pst.setLong(1, dyr.getDuyuru_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public turdao getTurdao() {
        if (this.turdao == null) {
            this.turdao = new turdao();
        }
        return turdao;
    }

  

    public int count(String searchTerm) {
        int count = 0;

        try {

            String query = "select count(duyuru_id) as aa from duyurular";
            if (searchTerm != null) {
                query += " where bilgi like ? ";
            }
            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (searchTerm != null) {
                st.setString(1, "%" + searchTerm + "%");
            }
            ResultSet rs = st.executeQuery();

            rs.next();
            count = rs.getInt("aa");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
}
