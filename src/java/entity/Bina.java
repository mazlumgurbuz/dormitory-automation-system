/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;


public class Bina {
    private Long bina_id;
    private String oda_sayisi;
    private String adres;

    public Bina(Long bina_id, String oda_sayisi, String adres) {
        this.bina_id = bina_id;
        this.oda_sayisi = oda_sayisi;
        this.adres = adres;
    }

    public Bina() {
    }
    
    public Long getBina_id() {
        return bina_id;
    }

    public void setBina_id(Long bina_id) {
        this.bina_id = bina_id;
    }

    public String getOda_sayisi() {
        return oda_sayisi;
    }

    public void setOda_sayisi(String oda_sayisi) {
        this.oda_sayisi = oda_sayisi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Bina{" + "bina_id=" + bina_id + ", oda_sayisi=" + oda_sayisi + ", adres=" + adres + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.bina_id);
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
        final Bina other = (Bina) obj;
        if (!Objects.equals(this.bina_id, other.bina_id)) {
            return false;
        }
        return true;
    }
    
}
