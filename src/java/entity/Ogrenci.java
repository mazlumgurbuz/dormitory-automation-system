/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Ogrenci {
    
    private Long ogr_id;
    private String adi;
    private String soyadi;
    
    private Odalar odalar;

    public Ogrenci(Long ogr_id, String adi, String soyadi) {
        this.ogr_id = ogr_id;
        this.adi = adi;
        this.soyadi = soyadi;
    }

    public Ogrenci() {
    }

    public Long getOgr_id() {
        return ogr_id;
    }

    public void setOgr_id(Long ogr_id) {
        this.ogr_id = ogr_id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public Odalar getOdalar() {
        return odalar;
    }

    public void setOdalar(Odalar odalar) {
        this.odalar = odalar;
    }
}
