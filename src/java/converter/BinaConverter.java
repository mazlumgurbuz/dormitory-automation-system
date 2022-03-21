/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.BinaDao;
import entity.Bina;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="binaConverter")
public class BinaConverter implements Converter{
    
    private BinaDao binaDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBinaDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Bina bn = (Bina) value;
        return bn.getBina_id().toString();//bn==null ? null : 
    }

    public BinaDao getBinaDao() {
        if(this.binaDao == null){
            this.binaDao = new BinaDao();
        }
        return binaDao;
    }

    public void setBinaDao(BinaDao binaDao) {
        this.binaDao = binaDao;
    }
}
