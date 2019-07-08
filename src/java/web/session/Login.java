/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sql.vendorScreen.admin.AdminSQL;
import sql.vendorScreen.admin.DtoVendorUser;

/**
 *
 * @author CR104978
 */
public class Login extends ActionSupport implements SessionAware {

    //variables propias de la sesion
    HttpServletRequest request;
    Map session;
    Session cms;
    //validates htmlfields
    boolean parametros = true;//IF THE PARAMETERS ARE WORKING ACCORDING THE VALIDATION PROCESS

    //vars usend in the login form
    String vendorUser; //BRINGS THE USER CODE or EMAIL OR VENDOR CODE FROM  LOGIN
    String vendorPassword; //BRINGS THE PASSWORD 

    //vars who whill be stored in the session
    String vendorUserName; //WILL BE HANDLING THE VENDOR'S NAME
    String idVendor; //WILL BE HANDLING THE VENDOR'S ID
    //VARS, USE IN THE CLASS
    String nombre;
    String mensajes = "";
    boolean mensaje;

    //var mails
    String mailState = "";

    //SET
    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    //GET
    public Map getSession() {
        return session;
    }

    ///custom
    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public boolean isMensaje() {
        return mensaje;
    }

    public void setMensaje(boolean mensaje) {
        this.mensaje = mensaje;
    }

    public void setVendorUser(String vendorUser) {
        this.vendorUser = StringUtils.trimToEmpty(vendorUser).toUpperCase();
    }

    public String getVendorUser() {
        return this.vendorUser;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = StringUtils.trimToEmpty(vendorPassword);
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorUserName(String vendorUserName) {
        this.vendorUserName = StringUtils.trimToEmpty(vendorUserName);
    }

    public String getVendorUserName() {
        return vendorUserName;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public String getIdVendor() {
        return idVendor;
    }

    @Override
    public String execute() {
        cms.close();
        return SUCCESS;
    }

    @Override
    public void validate() {
        //REQUEST
        request = ServletActionContext.getRequest();

        //VALIDAR QUE CAMPOS NO SEAN BLANCOS NI NULOS
        if ((vendorUser == null) || (vendorUser.isEmpty() == true)) {
            mensajes = mensajes + "danger<>Error<> Vendor code, email or vendor user code must be input";
            mensaje = true;
            //   addFieldError("vendorUser", "Complete \"User\".");
            parametros = false;
        }
        if ((vendorPassword == null) || (vendorPassword.isEmpty() == true)) {
            // addFieldError("vendorPassword", "Complete \"Password\".");
            mensajes = mensajes + "danger<>Error<> Vendor password must be input";
            mensaje = true;
            parametros = false;
        }

        if (parametros == true) {
            try {

                session = ActionContext.getContext().getSession();

                cms = ORMUtil.getSesionCMS().openSession();

                boolean access = false;
                access = getAccount(cms, vendorUser, vendorPassword);
                if (access) {
                    //   System.out.println("Resultado de BD: " + vendorUser);
                    session = ActionContext.getContext().getSession();
                    session.put("en-sesion", "true");
                    session.put("user", vendorUser);
                    session.put("userName", vendorUserName);
                    session.put("idVendor", idVendor);
                    session.put("ip", request.getRemoteHost());
//            SendMails send = new SendMails();
//            send.sendMail("dqalta@gmail.com");

                    //Lógica para pegar Base de Datos
                    /* }*/
                    cms.close();
                } else {
                    mensajes = mensajes + "danger<>Error<> Vendor account doesn't active or doesn't exist: " + vendorUser;
                    mensaje = true;

                    addActionError("");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                addActionError(e.getMessage());
            }
        }
    }

    public boolean getAccount(Session cms, String user, String password) {
        Boolean flag = false;
        Transaction tn = null;
        try {
            tn = cms.beginTransaction();

            DtoVendorUser p = AdminSQL.getUserLogin(cms, user);

            if (p != null) {
                if (p.getPasswordVendorUser().equals(password)) {
                    vendorUserName = p.getFullName();
                    idVendor = p.getIdVendor();
                    flag = true;
                } else {
                    mensajes = mensajes + "danger<>Error<> The password is not valid for the user: " + user;
                    mensaje = true;
                    addActionError("");
                    flag = false;
                }
            } else {
                mensajes = mensajes + "danger<>Error<> User is not valid. Check again.";
                mensaje = true;
                addActionError("");
                flag = false;
            }
        } catch (HibernateException x) {
            mensajes = mensajes + "danger<>Error<> Vendor account doesn't active or doesn't exist: " + user + ": " + ExceptionUtils.getMessage(x) + ".";
            mensaje = true;
            if (tn != null) {
                tn.rollback();
            }

        }
        return flag;
    }

}
