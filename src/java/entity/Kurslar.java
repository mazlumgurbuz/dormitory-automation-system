/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Objects;


public class Kurslar {
    private Long kurs_id;
    private String kurs_adi;

    public Kurslar(Long kurs_id, String kurs_adi) {
        this.kurs_id = kurs_id;
        this.kurs_adi = kurs_adi;
    }

    public Kurslar() {
    }

    public Long getKurs_id() {
        return kurs_id;
    }

    public void setKurs_id(Long kurs_id) {
        this.kurs_id = kurs_id;
    }

    public String getKurs_adi() {
        return kurs_adi;
    }

    public void setKurs_adi(String kurs_adi) {
        this.kurs_adi = kurs_adi;
    }

    @Override
    public String toString() {
        return "Kurslar{" + "kurs_id=" + kurs_id + ", kurs_adi=" + kurs_adi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.kurs_id);
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
        final Kurslar other = (Kurslar) obj;
        if (!Objects.equals(this.kurs_id, other.kurs_id)) {
            return false;
        }
        return true;
    }
    
}
