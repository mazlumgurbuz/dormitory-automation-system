package controller;

import dao.BinaDao;
import dao.OdalarDao;
import entity.Bina;
import entity.Odalar;
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
public class OdalarController implements Serializable {
    
    private Odalar odalar;
    private List<Odalar> odalarList;
    private OdalarDao odalarDao;
    
    private List<Bina> binaList;
    private BinaDao binaDao;
    
    private int page=1;
    private int pageSize=5;
    private int pageCount;
    
    public void next(){
        if(this.page==this.getPageCount())
            this.page=1;
        else
            this.page++;
    }
    public void previous(){
        if(this.page==1)
            this.page=this.getPageCount();
        else
            this.page--;
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
        this.pageCount =(int) Math.ceil(this.getOdalarDao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String str = value.toString();
        if(str.length()<3){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if(str.length()>6){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 6 hane girilebilir"));
        }
    }
    
    public void delete(){
        this.getOdalarDao().remove(this.odalar);
        this.clearForm();
    }
     public void deleteConfirm(Odalar odalar) {
        this.odalar = odalar;
    }
    
    public void clearForm() {
        this.odalar = new Odalar();
        
    }
    
    public void updateForm(Odalar o){
        this.odalar=o;
       
    }
    
    public void update(){
        this.getOdalarDao().edit(this.odalar);
        this.clearForm();
    }
    
    public void create(){
        this.getOdalarDao().create(this.odalar);
        this.clearForm();
    }

    public Odalar getOdalar() {
        if(this.odalar==null){
            this.odalar = new Odalar();
        }
        return odalar;
    }

    public void setOdalar(Odalar odalar) {
        this.odalar = odalar;
    }

    public List<Odalar> getOdalarList() {
        this.odalarList=this.getOdalarDao().findAll(page,pageSize);
        return odalarList;
    }

    public void setOdalarList(List<Odalar> odalarList) {
        this.odalarList = odalarList;
    }

    public OdalarDao getOdalarDao() {
        if(this.odalarDao == null){
            this.odalarDao = new OdalarDao();
        }
        return odalarDao;
    }

    public void setOdalarDao(OdalarDao odalarDao) {
        this.odalarDao = odalarDao;
    }

    public BinaDao getBinaDao() {
        if(this.binaDao == null){
            this.binaDao = new BinaDao();
        }
        return binaDao;
    }

    public List<Bina> getBinaList() {
        this.binaList = this.getBinaDao().findAll(page,pageSize);
        return binaList;
    }

    public void setBinaList(List<Bina> binaList) {
        this.binaList = binaList;
    }
    
}
