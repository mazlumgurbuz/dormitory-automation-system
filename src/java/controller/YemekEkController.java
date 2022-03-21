/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.YemekEKDao;
import entity.YemekEk;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class YemekEkController extends BaseBean implements Serializable {
    private List<YemekEk> yList;
    private YemekEKDao Ydao;
    private YemekEk Y_ek;
    
     @Override
   public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getyDao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
    }
    
    public void updateForm(YemekEk yek){
        this.Y_ek=yek;
       
    }
    
    public void clearForm(){
        this.Y_ek= new YemekEk();
        
    }
    
    public void deleteConfirm(YemekEk yek){
        this.Y_ek=yek;
        
    }
    
    public void delete(){
        this.getyDao().delete(this.Y_ek);
        clearForm();
       
    }
    
    public void update(){
        this.getyDao().update(this.Y_ek);
        this.Y_ek= new YemekEk();
        
    }
    
    public void create(){
        this.Ydao.insert(this.Y_ek);
        this.Y_ek= new YemekEk();
        
    }

    public List<YemekEk> getyList() {
        
     this.yList=this.getyDao().findAll(page, pageSize ,  this.getSearchTerm());
        return yList;
    }

    public void setyList(List<YemekEk> yList) {
        
        this.yList = yList;
    }

    public YemekEKDao getyDao() {
       if(this.Ydao==null)
           this.Ydao= new YemekEKDao();
       return Ydao;
    }

    public void setyDao(YemekEKDao yDao) {
        this.Ydao = yDao;
    }

    public YemekEk getY_ek() {
        if(this.Y_ek==null)
            this.Y_ek= new YemekEk();
        return Y_ek;
    }

    public void setY_ek(YemekEk Y_ek) {
        this.Y_ek = Y_ek;
    }
    
    
    
}
