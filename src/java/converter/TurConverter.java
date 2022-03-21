/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.turdao;
import entity.duyurlar_turu;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="duyuruConverter")
public class TurConverter implements Converter{
    
    private turdao turdao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getTurdao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object arg2) {
        duyurlar_turu d=(duyurlar_turu) arg2;
        return d.getTur_id().toString();
    }

    public turdao getTurdao() {
        if(this.turdao==null)
            this.turdao=new turdao();
        return turdao;
    }
    
}
