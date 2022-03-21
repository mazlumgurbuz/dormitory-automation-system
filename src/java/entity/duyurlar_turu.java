package entity;


public class duyurlar_turu {

    private Long tur_id;
    private String isim;

    public Long getTur_id() {
        return tur_id;
    }

    public void setTur_id(long tur_id) {
        this.tur_id = tur_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.tur_id ^ (this.tur_id >>> 32));
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
        final duyurlar_turu other = (duyurlar_turu) obj;
        if (this.tur_id != other.tur_id) {
            return false;
        }
        return true;
    }
 
}
