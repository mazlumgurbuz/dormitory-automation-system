/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.CategoryDao;
import entity.Category;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="categoryConverter")
public class CategoryConverter implements Converter {
private CategoryDao categoryDao ;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    
        return this.getCategoryDao().find(Long.valueOf(value));
    
    }

    @Override
    public String getAsString(FacesContext atg0, UIComponent arg1, Object arg2) {
    
    Category c = (Category) arg2 ;
    return c.getCategory_id().toString();
    }

    public CategoryDao getCategoryDao() {
        if(this.categoryDao==null)
            this.categoryDao=new CategoryDao();
        return categoryDao;
    }
    
}
