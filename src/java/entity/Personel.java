package entity;


public class Personel {

    private Long personel_id;
    private String isim;
    private String soyad;
    private String tc;
    
    private Personeltur personeltur;
    
    public Personel(Long personel_id, String isim, String soyad, String tc) {
        this.personel_id = personel_id;
        this.isim = isim;
        this.soyad = soyad;
        this.tc = tc;
    }

    public Personel() {

    }

    public Long getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(Long personel_id) {
        this.personel_id = personel_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    
    public Personeltur getPersoneltur() {
        return personeltur;
    }

    public void setPersoneltur(Personeltur personeltur) {
        this.personeltur = personeltur;
    }
    
}
