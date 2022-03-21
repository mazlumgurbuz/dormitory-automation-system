
package entity;


public class Odemeler {
    private Long odeme_id;
    private String ogrenciAdi;
    private int ucret;

    public Odemeler(Long odeme_id, String ogrenciAdi, int ucret) {
        this.odeme_id = odeme_id;
        this.ogrenciAdi = ogrenciAdi;
        this.ucret = ucret;
    }

    public Odemeler() {
    }

    public Long getOdeme_id() {
        return odeme_id;
    }

    public void setOdeme_id(Long odeme_id) {
        this.odeme_id = odeme_id;
    }

    public String getOgrenciAdi() {
        return ogrenciAdi;
    }

    public void setOgrenciAdi(String ogrenciAdi) {
        this.ogrenciAdi = ogrenciAdi;
    }

    public int getUcret() {
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }

    @Override
    public String toString() {
        return "Odemeler{" + "odeme_id=" + odeme_id + ", ogrenciAdi=" + ogrenciAdi + ", ucret=" + ucret + '}';
    }
   
    
}
