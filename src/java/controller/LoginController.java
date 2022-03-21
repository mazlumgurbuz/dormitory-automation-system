/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LoginDao;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private User user;
    private LoginDao userdao;

    public LoginDao getUserdao() {
        if (this.userdao == null) {
            this.userdao = new LoginDao();
        }
        return userdao;
    }

    public void setUserdao(LoginDao userdao) {
        this.userdao = userdao;
    }

    public String login() {
        User tmp = getUserdao().validate(getUser().getUsername());
        if (tmp != null && tmp.getPassword().equalsIgnoreCase(getUser().getPassword())) {
            //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);
            //HttpSession session = SessionUtils.getSession();
            // session.setAttribute("username", user);

            setUser(tmp);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);

            if (tmp != null && tmp.getUturu().equals("Admin")) {
                // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);

                return "/secret/secret?faces-redirect=true";

            } else {
                //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);

                return "/Users/View?faces-redirect=true";
            }
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hata "));
            setUser(null);
            // HttpSession session = SessionUtils.getSession();
            // session.invalidate();

            return "login.xhtml";
        }

    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
