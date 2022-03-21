/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Kantin {
    private Long malzeme_id;
    private String isim;
    
    private Kantintur kantintur;

    public Kantin(Long malzeme_id, String isim) {
        this.malzeme_id = malzeme_id;
        this.isim = isim;
    }

    public Kantin() {
    }
    
    public Long getMalzeme_id() {
        return malzeme_id;
    }

    public void setMalzeme_id(Long malzeme_id) {
        this.malzeme_id = malzeme_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Kantintur getKantintur() {
        return kantintur;
    }

    public void setKantintur(Kantintur kantintur) {
        this.kantintur = kantintur;
    }
    
    
    
    
    
}
