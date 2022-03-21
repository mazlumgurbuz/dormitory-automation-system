/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;


public class Kantintur {
    private Long tur_id;
    private String tur_isim;  

    public Kantintur(Long tur_id, String tur_isim) {
        this.tur_id = tur_id;
        this.tur_isim = tur_isim;
    }

    public Kantintur() {
    }

    public Long getTur_id() {
        return tur_id;
    }

    public void setTur_id(Long tur_id) {
        this.tur_id = tur_id;
    }

    public String getTur_isim() {
        return tur_isim;
    }

    public void setTur_isim(String tur_isim) {
        this.tur_isim = tur_isim;
    }

    @Override
    public String toString() {
        return "Kantintur{" + "tur_id=" + tur_id + ", tur_isim=" + tur_isim + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.tur_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kantintur other = (Kantintur) obj;
        if (!Objects.equals(this.tur_id, other.tur_id)) {
            return false;
        }
        return true;
    }
     
    
    
}
