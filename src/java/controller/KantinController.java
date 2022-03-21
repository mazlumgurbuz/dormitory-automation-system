package controller;

import dao.KantinDao;
import dao.KantinturDao;
import entity.Kantin;
import entity.Kantintur;
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
public class KantinController extends BaseBean implements Serializable {
    
    private Kantin kantin;
    private List<Kantin> kantinList;
    private KantinDao kantinDao;
    
    private List<Kantintur> kantinturList;
    private KantinturDao kantinturDao;
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String str = value.toString();
        if(str.length()<3){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if(str.length()>6){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 6 hane girilebilir"));
        }
    }
    
      @Override
   public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getKantinDao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
    }

    
    public void delete(){
        this.getKantinDao().remove(this.kantin);
        this.clearForm();
    }
    public void deleteConfirm(Kantin kantin) {
        this.kantin = kantin;
    }
    
    public void clearForm() {
        this.kantin = new Kantin();
        
    }
    
    public void updateForm(Kantin k){
        this.kantin=k;
        
    }
    
    public void update(){
        this.getKantinDao().edit(this.kantin);
        this.clearForm();
    }
    
    public void create(){
        this.getKantinDao().create(this.kantin);
        this.clearForm();
    }

    public Kantin getKantin() {
        if(this.kantin==null){
            this.kantin = new Kantin();
        }
        return kantin;
    }

    public void setKantin(Kantin kantin) {
        this.kantin = kantin;
    }

    public List<Kantin> getKantinList() {
        this.kantinList=this.getKantinDao().findAll(page, pageSize ,  this.getSearchTerm());
        return kantinList;
    }

    public void setKantinList(List<Kantin> kantinList) {
        this.kantinList = kantinList;
    }

    public KantinDao getKantinDao() {
        if(this.kantinDao == null){
            this.kantinDao = new KantinDao();
        }
        return kantinDao;
    }

    public void setKantinDao(KantinDao kantinDao) {
        this.kantinDao = kantinDao;
    }

    public KantinturDao getKantinturDao() {
        if(this.kantinturDao == null){
            this.kantinturDao = new KantinturDao();
        }
        return kantinturDao;
    }

    public List<Kantintur> getKantinturList() {
        this.kantinturList = this.getKantinturDao().findAll();
        return kantinturList;
    }

    public void setKantinturList(List<Kantintur> kantinturList) {
        this.kantinturList = kantinturList;
    }
    
}
