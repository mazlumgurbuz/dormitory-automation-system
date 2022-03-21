package controller;

import dao.KurslarDao;
import entity.Kurslar;
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
public class KurslarController extends BaseBean implements Serializable {

    private List<Kurslar> kurslarList;
    private KurslarDao kurslarDao;
    private Kurslar kurslar;
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String str = value.toString();
        if(str.length()<3){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if(str.length()>6){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 6 hane girilebilir"));
        }
    }
    
    public void clearForm() {
        this.kurslar = new Kurslar();
    }

    public void updateForm(Kurslar kurslar) {
        this.kurslar = kurslar;
    }

    public void update() {
        this.getKurslarDao().update(this.kurslar);
        this.clearForm();
    }

    public void deleteConfirm(Kurslar kurslar) {
        this.kurslar = kurslar;
    }

    public void delete() {
        
        this.getKurslarDao().delete(this.kurslar);
        this.clearForm();
    }

    public void create() {

        this.getKurslarDao().insert(this.kurslar);
        this.clearForm();
    }

    public List<Kurslar> getKurslarList() {
        this.kurslarList = this.getKurslarDao().findAll(page, pageSize ,  this.getSearchTerm());
        return kurslarList;
    }

    public void setKurslarList(List<Kurslar> kurslarList) {
        this.kurslarList = kurslarList;
    }

    public KurslarDao getKurslarDao() {
        if (this.kurslarDao == null) {
            this.kurslarDao = new KurslarDao();
        }
        return kurslarDao;
    }

    public void setKurslarDao(KurslarDao kurslarDao) {
        this.kurslarDao = kurslarDao;
    }

    public Kurslar getKurslar() {
        if (this.kurslar == null) {
            this.kurslar = new Kurslar();
        }
        return kurslar;
    }

    public void setKurslar(Kurslar kurslar) {
        this.kurslar = kurslar;
    }
     @Override
   public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getKurslarDao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
    }
}
