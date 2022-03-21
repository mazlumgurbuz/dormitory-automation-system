/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.KantinturDao;
import entity.Kantintur;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="kantinturConverter")
public class KantinturConverter implements Converter{

    private KantinturDao kantinturDao;
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKantinturDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Kantintur pt = (Kantintur) value;
        return pt.getTur_id().toString();
    }

    public KantinturDao getKantinturDao() {
        if(this.kantinturDao == null){
            this.kantinturDao = new KantinturDao();
        }
        return kantinturDao;
    }
    
}
