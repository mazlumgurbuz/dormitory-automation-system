/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDao;
import entity.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped

public class CategoryController extends BaseBean implements Serializable {

    private List<Category> categoryList;
    private CategoryDao categoryDao;

    private Category category;

    public void updateForm(Category cat) {
        this.category = cat;

    }

    public void clearForm() {
        this.category = new Category();
    }

    public void deleteConfirm(Category cat) {
        this.category = cat;
    }

    public void update() {
        this.categoryDao.update(this.category);
        this.category = new Category();
    }

    ///no 9 videow
    public void delete() {
        this.getCategoryDao().remove(this.category);
        this.category = new Category();

    }

    public void create() {
        this.getCategoryDao().insert(this.category);
        this.category = new Category();
    }

    public List<Category> getCategoryList() {
        this.categoryList = this.getCategoryDao().findAll(page, pageSize, this.getSearchTerm());
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryDao getCategoryDao() {
        if (this.categoryDao == null) {
            this.categoryDao = new CategoryDao();
        }
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category getCategory() {
        if (this.category == null) {
            this.category = new Category();
        }
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getCategoryDao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
    }

}
