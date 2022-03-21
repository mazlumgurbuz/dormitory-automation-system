/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;


import dao.OdalarDao;
import entity.Odalar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="odalarConverter")
public class OdalarConverter implements Converter{
    
    private OdalarDao odalarDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getOdalarDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Odalar od = (Odalar) value;
        return  od.getOda_id().toString();//bn==null ? null : 
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
    
}
