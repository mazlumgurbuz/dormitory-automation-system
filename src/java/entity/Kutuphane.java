/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;


public class Kutuphane {

    private long kitab_id;
    private String kitab_adi;
    private String description;
    private int year;
    
    
    
    private Language language ;
    
    private List<Category> kutuphaneCategories ;
    
    
    
    public long getKitab_id() {
        return kitab_id;
    }

    public void setKitab_id(long kitab_id) {
        this.kitab_id = kitab_id;
    }

    public String getKitab_adi() {
        return kitab_adi;
    }

    public void setKitab_adi(String kitab_adi) {
        this.kitab_adi = kitab_adi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Language getLanguage() {
      
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Category> getKutuphaneCategories() {
        return kutuphaneCategories;
    }

    public void setKutuphaneCategories(List<Category> kutuphaneCategories) {
        this.kutuphaneCategories = kutuphaneCategories;
    }

    

   
}
