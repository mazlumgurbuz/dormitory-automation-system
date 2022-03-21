package controller;

import dao.OdemelerDao;

import entity.Odemeler;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import javax.inject.Named;

@Named
@SessionScoped
public class OdemelerController extends BaseBean implements Serializable {

    private List<Odemeler> odemelerList;
    private OdemelerDao odemelerDao;
    private Odemeler odemeler;

    @Override
    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getOdemelerDao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
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
        this.odemeler = new Odemeler();
    }

    public void updateForm(Odemeler odemeler) {
        this.odemeler = odemeler;
    }

    public void update() {
        this.getOdemelerDao().update(this.odemeler);
        this.clearForm();
    }

    public void deleteConfirm(Odemeler odemeler) {
        this.odemeler = odemeler;
    }

    public void delete() {

        this.getOdemelerDao().delete(this.odemeler);
        this.clearForm();
    }

    public void create() {

        this.getOdemelerDao().insert(this.odemeler);
        this.clearForm();
    }

    public List<Odemeler> getOdemelerList() {
        this.odemelerList = this.getOdemelerDao().findAll(page, pageSize, this.getSearchTerm());
        return odemelerList;
    }

    public void setOdemelerList(List<Odemeler> odemelerList) {
        this.odemelerList = odemelerList;
    }

    public OdemelerDao getOdemelerDao() {
        if (this.odemelerDao == null) {
            this.odemelerDao = new OdemelerDao();
        }
        return odemelerDao;
    }

    public void setOdemelerDao(OdemelerDao odemelerDao) {
        this.odemelerDao = odemelerDao;
    }

    public Odemeler getOdemeler() {
        if (this.odemeler == null) {
            this.odemeler = new Odemeler();
        }
        return odemeler;
    }

    public void setOdemeler(Odemeler odemeler) {
        this.odemeler = odemeler;
    }
}
