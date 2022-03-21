/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.turdao;
import dao.duyuruDao;
import entity.duyurlar_turu;
import entity.duyuru;
import java.io.Serializable;
import static java.util.Collections.list;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class duyuruController extends BaseBean implements Serializable {

    private duyuru dyr;
    private List<duyuru> dList;
    private duyuruDao ddao;

    private List<duyurlar_turu> turList;
    private turdao turdao;

    public void delete() {
        this.getDDao().remove(this.dyr);
        this.clearForm();
    }

    public void clearForm() {
        this.dyr = new duyuru();

    }

    public void updateForm(duyuru d) {
        this.dyr = d;
    }

    public void create() {
        this.getDDao().create(this.dyr);
        this.clearForm();
    }

    public void update() {
        this.getDDao().edit(this.dyr);
        this.clearForm();
    }

    public duyuru getDyr() {
        if (this.dyr == null) {
            this.dyr = new duyuru();
        }
        return dyr;
    }

    public void setDyr(duyuru dyr) {
        this.dyr = dyr;
    }

    public List<duyuru> getDlist() {
        this.dList = this.getDDao().findAll(page, pageSize ,  this.getSearchTerm());
        return dList;
    }

    public void setDlist(List<duyuru> dList) {
        this.dList = dList;
    }

    public duyuruDao getDDao() {
        if (this.ddao == null) {
            this.ddao = new duyuruDao();
        }
        return ddao;
    }

    public void setDDao(duyuruDao ddao) {
        this.ddao = ddao;
    }

    public turdao getTurDAO() {
        if (this.turdao == null) {
            this.turdao = new turdao();
        }
        return turdao;
    }

    public List<duyurlar_turu> getTurList() {
        this.turList = this.getTurDAO().findAll();
        return turList;
    }

    public void setTurList(List<duyurlar_turu> turList) {
        this.turList = turList;
    }

     @Override
   public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDDao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
    }
    
}
