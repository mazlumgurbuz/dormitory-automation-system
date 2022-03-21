package entity;

import java.util.Objects;


public class Personeltur {

    private Long tur_id;
    private String tur_ismi;

    public Personeltur(Long tur_id, String tur_ismi) {
        this.tur_id = tur_id;
        this.tur_ismi = tur_ismi;
    }

    public Personeltur() {
    }
    
    public Long getTur_id() {
        return tur_id;
    }

    public void setTur_id(Long tur_id) {
        this.tur_id = tur_id;
    }

    public String getTur_ismi() {
        return tur_ismi;
    }

    public void setTur_ismi(String tur_ismi) {
        this.tur_ismi = tur_ismi;
    }

    @Override
    public String toString() {
        return "Personeltur{" + "tur_id=" + tur_id + ", tur_ismi=" + tur_ismi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.tur_id);
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
        final Personeltur other = (Personeltur) obj;
        if (!Objects.equals(this.tur_id, other.tur_id)) {
            return false;
        }
        return true;
    }
    
}
