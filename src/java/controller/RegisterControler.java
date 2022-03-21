package controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import dao.RegisterDao;


@Named
// @SessionScoped kapsamlarimizi belerliyoruz * oturum kapsamli
//@SessionScoped
@javax.enterprise.context.SessionScoped

public class RegisterControler implements Serializable {

   // private static final long serialVersionUID = 1094801825228386363L;

   
    private String uname;
    private String password;
    private String userTuru;
     private RegisterDao registerDao;
     
    

    public String create() {
        
        this.getRegisterDao().insert(this.uname, this.password, this.userTuru);
        this.uname = null;
        this.password = null;
        
        return "login";
    }

    public RegisterControler() {
       // RegisterControler u = new RegisterControler();
         // this.clist =new ArrayList();
       // RegisterDao = new RegisterDao();
        this.uname = "";
        this.password = "";
       
    }

    public RegisterControler( String uname, String password, String userTuru) {
        
        this.uname = uname;
        this.password = password;
        this.userTuru = userTuru;
    }
    

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserTuru() {
        return userTuru;
    }

    public void setUserTuru(String userTuru) {
        this.userTuru = userTuru;
    }
    
    

    //validate login
   /* public String validateUsernamePasswordAdmin() {
        boolean valid = RegisterDao.validate(uname, password, "admin");
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", uname);
            session.setAttribute("password", password);
            session.setAttribute("admin", "admin");
            return "secret/secret?faces-redirect=true";
        }
        valid = RegisterDao.validate(uname, password, "user");

        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", uname);
            session.setAttribute("password", password);
            session.setAttribute("USER", "USER");
            return "USER?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "index?faces-redirect=true";
        }
    }
    //logout event, invalidate session

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login_logout";
    }
*/

    public RegisterDao getRegisterDao() {
        return registerDao;
    }
}
