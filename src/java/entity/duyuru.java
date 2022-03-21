package entity;


public class duyuru {

    private long duyuru_id;
    private String bilgi;

    private duyurlar_turu duyurlar_turu;

    public long getDuyuru_id() {
        return duyuru_id;
    }

    public void setDuyuru_id(long duyuru_id) {
        this.duyuru_id = duyuru_id;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public duyurlar_turu getDuyurlar_turu() {
        return duyurlar_turu;
    }

    public void setDuyurlar_turu(duyurlar_turu duyurlar_turu) {
        this.duyurlar_turu = duyurlar_turu;
    }

    
}
