/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.KutuphaneDao;
import dao.LanguageDao;
import entity.Category;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entity.Kutuphane;
import entity.Language;
import javax.inject.Inject;

@Named
@SessionScoped
public class KutuphaneController extends BaseBean implements Serializable {

    private Kutuphane kutuphane;  //nesne
    private List<Kutuphane> kutuphaneList;
    private KutuphaneDao kutuphanedao;

   
    @Inject
    private CategoryController categoryController;

    //listeyi gostermek ve bir tane seçmesine izin vercem
    private List<Language> languageList;
    private LanguageDao languageDao;
    
   

    public void delete() {
        this.getKutuphanedao().remove(this.kutuphane);
        this.clearForm();

    }

    public void updateForm(Kutuphane k) {
        this.kutuphane = k;

    }

    public void clearForm() {
        this.kutuphane = new Kutuphane();
    }

    public void update() {
        this.getKutuphanedao().edit(this.kutuphane);
        this.clearForm();

    }

    public void create() {
        this.kutuphanedao.create(this.kutuphane);
        this.clearForm();
    }

    public Kutuphane getKutuphane() {
        if (this.kutuphane == null) {
            this.kutuphane = new Kutuphane();
        }
        return kutuphane;
    }

    public void setKutuphane(Kutuphane kutuphane) {
        this.kutuphane = kutuphane;

    }

    public List<Kutuphane> getKutuphaneList() {
        this.kutuphaneList = this.getKutuphanedao().findAll(page, pageSize ,  this.getSearchTerm() );
        return kutuphaneList;
    }

    public void setKutuphaneList(List<Kutuphane> kutuphaneList) {
        this.kutuphaneList = kutuphaneList;
    
    }
    public KutuphaneDao getKutuphanedao() {
        if (this.kutuphanedao == null) {
            this.kutuphanedao = new KutuphaneDao();
        }
        return kutuphanedao;
    }

    public void setKutuphanedao(KutuphaneDao kutuphanedao) {
        this.kutuphanedao = kutuphanedao;
    }

    public LanguageDao getLanguageDao() {
        if (this.languageDao == null) {
            this.languageDao = new LanguageDao();
        }
        return languageDao;
    }

    public List<Language> getLanguageList() {
        this.languageList = this.getLanguageDao().findAll();
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    /* public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }*/
    public CategoryController getCategoryController() {
        return categoryController;
    }

    public void setCategoryController(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    @Override
   public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getKutuphanedao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
    }


}
