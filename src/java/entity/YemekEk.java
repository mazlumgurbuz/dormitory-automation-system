/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;


public class YemekEk {

    private Long ek_id;
    private String ek_isim;

    public YemekEk() {
    }

    public YemekEk(Long ek_id, String ek_isim) {
        this.ek_id = ek_id;
        this.ek_isim = ek_isim;
    }

    public Long getEk_id() {
        return ek_id;
    }

    public void setEk_id(Long ek_id) {
        this.ek_id = ek_id;
    }

    public String getEk_isim() {
        return ek_isim;
    }

    public void setEk_isim(String ek_isim) {
        this.ek_isim = ek_isim;
    }

    @Override
    public String toString() {
        return "y_ek{" + "ek_id=" + ek_id + ", ek_isim=" + ek_isim + '}';
    }
    
    
}
