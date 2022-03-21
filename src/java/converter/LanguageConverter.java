/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.LanguageDao;
import entity.Language;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "languageConverter")
public class LanguageConverter implements Converter {

    private LanguageDao languageDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return this.getLanguageDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Language l = (Language) arg2;
        return l.getLanguage_id().toString();

    }

    public LanguageDao getLanguageDao() {
        if (this.languageDao == null) {
            this.languageDao = new LanguageDao();
        }
        return languageDao;
    }

}
