package controller;


import dao.UserAdminDao;

import entity.User;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;


@Named
@SessionScoped
public class UserAdminController extends BaseBean implements Serializable {
    
    private User user;
    private List<User> userList;
    private UserAdminDao userAdminDao;
  
      @Override
   public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getUserAdminDao().count(this.getSearchTerm()) / (double) pageSize);
        return pageCount;
    }
    
   
    
    public void delete(){
        this.getUserAdminDao().remove(this.user);
        this.clearForm();
    }
    public void deleteConfirm(User user) {
        this.user = user;
    }
    
    public void clearForm() {
        this.user = new User();
        
    }
    
    public void updateForm(User u){
        this.user=u;
        
    }
    
    public void update(){
        this.getUserAdminDao().edit(this.user);
        this.clearForm();
    }
    
    public void create(){
        this.getUserAdminDao().create(this.user);
        this.clearForm();
    }

    public User getUser() {
        if(this.user==null){
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        this.userList=this.getUserAdminDao().findAll(page, pageSize ,  this.getSearchTerm());
        return userList;
    }

    public void setUserAdminList(List<User> userList) {
        this.userList = userList;
    }

    public UserAdminDao getUserAdminDao() {
        if(this.userAdminDao == null){
            this.userAdminDao = new UserAdminDao();
        }
        return userAdminDao;
    }

    public void setUserAdminDao(UserAdminDao userAdminDao) {
        this.userAdminDao = userAdminDao;
    }

    

    

    
    
}
