/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.PersonelturDao;
import entity.Personeltur;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="personelturConverter")
public class PersonelturConverter implements Converter{

    private PersonelturDao personelturDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getPersonelturDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Personeltur pt = (Personeltur) value;
        return pt.getTur_id().toString();
    }

    public PersonelturDao getPersonelturDao() {
        if(this.personelturDao == null){
            this.personelturDao = new PersonelturDao();
        }
        return personelturDao;
    }
    
}
