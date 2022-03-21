package controller;

import dao.BinaDao;
import entity.Bina;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import javax.inject.Named;

/**
 *
 * @author mazlum gurbuz
 */
@Named
@SessionScoped
public class BinaController implements Serializable {

    private List<Bina> binaList;
    private BinaDao binaDao;
    private Bina bina;

    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getBinaDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String str = value.toString();
        if (str.length() < 3) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if (str.length() > 6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 6 hane girilebilir"));
        }
    }

    public void clearForm() {
        this.bina = new Bina();
    }

    public void updateForm(Bina bina) {
        this.bina = bina;
    }

    public void update() {
        this.getBinaDao().update(this.bina);
        this.clearForm();
    }

    public void deleteConfirm(Bina bina) {
        this.bina = bina;
    }

    public void delete() {

        this.getBinaDao().delete(this.bina);
        this.clearForm();
    }

    public void create() {

        this.getBinaDao().insert(this.bina);
        this.clearForm();
    }

    public List<Bina> getBinaList() {
        this.binaList = this.getBinaDao().findAll(page, pageSize);
        return binaList;
    }

    public void setBinaList(List<Bina> binaList) {
        this.binaList = binaList;
    }

    public BinaDao getBinaDao() {
        if (this.binaDao == null) {
            this.binaDao = new BinaDao();
        }
        return binaDao;
    }

    public void setBinaDao(BinaDao binaDao) {
        this.binaDao = binaDao;
    }

    public Bina getBina() {
        if (this.bina == null) {
            this.bina = new Bina();
        }
        return bina;
    }

    public void setBina(Bina bina) {
        this.bina = bina;
    }
}
