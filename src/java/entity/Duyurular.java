/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Duyurular {
    
    private Long duyuru_id;
    private String bilgi;

    public Duyurular(Long duyuru_id, String bilgi) {
        this.duyuru_id = duyuru_id;
        this.bilgi = bilgi;
    }

    public Duyurular() {
    }

    public Long getDuyuru_id() {
        return duyuru_id;
    }

    public void setDuyuru_id(Long duyuru_id) {
        this.duyuru_id = duyuru_id;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }
}
