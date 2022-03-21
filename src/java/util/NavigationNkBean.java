/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Muhammed ARSLAN
 */
@Named
@RequestScoped
public class NavigationNkBean implements Serializable{
    
    public String page(String p){
        
        return "/Users/module/"+p+"/"+p+"?faces-redirect=true" ;
    }
}