
package entity;

import java.util.Objects;


public class Odalar {
    private Long oda_id;
    private int oda_no;
    
    private Bina bina;

    public Odalar(Long oda_id, int oda_no) {
        this.oda_id = oda_id;
        this.oda_no = oda_no;
    }

    public Odalar() {
    }

    public Long getOda_id() {
        return oda_id;
    }

    public void setOda_id(Long oda_id) {
        this.oda_id = oda_id;
    }

    public int getOda_no() {
        return oda_no;
    }

    public void setOda_no(int oda_no) {
        this.oda_no = oda_no;
    }

    public Bina getBina() {
        return bina;
    }

    public void setBina(Bina bina) {
        this.bina = bina;
    }

    @Override
    public String toString() {
        return "Odalar{" + "oda_id=" + oda_id + ", oda_no=" + oda_no + ", bina=" + bina + '}';
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.oda_id);
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
        final Odalar other = (Odalar) obj;
        if (!Objects.equals(this.oda_id, other.oda_id)) {
            return false;
        }
        return true;
    }
    
}
