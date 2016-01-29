package com.ftm.itoverview.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
 
import org.primefaces.context.RequestContext;
 
@ManagedBean
public class UserLoginView {
     
    private String username;
     
    private String password;
    
    private boolean loggedIn = false;
    
    private String page;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        String page = "";
        
        if(username != null && username.equals("feeder") && password != null && password.equals("feeder")) {
            page = "http://localhost:8080/ITOverview/faces/distribution.xhtml";
            loggedIn = true;            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else if(username != null && username.equals("dashboard") && password != null && password.equals("dashboard")) { 
            page = "http://localhost:8080/ITOverview/faces/dashboard.xhtml";
            loggedIn = true;            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "feeder", username);
        } else {        
            loggedIn = false;            
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("page", page);
    }
    
    public void logOut(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        loggedIn = false;
    }
}