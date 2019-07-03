/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sql.vendorScreen.admin.AdminSQL;
import sql.vendorScreen.maintenance.DtoVendorRequest;

import sql.vendorScreen.maintenance.MaintenanceSQL;
import util.Fechas;
import util.Generales;
import web.Cycle.SendMails;
import web.session.ORMUtil;
import web.util.CombosMaintenance;
import web.util.KeyCombos;
import web.util.KeyCombosString;

/**
 *
 * @author CR104978
 */
public class Register extends ActionSupport implements SessionAware {

    HttpServletRequest request;//Variable donde se crea el objeto de la petición
    Map session;//Variable que guarda la sesión del Tomcat

    // Variables del Hibernate-ORM
    Session vdk; //Variable de la conexión a la base de datos

    //Variables del Controlador Struts2 Servidor<->JSP
    //Variables de validaciones
    int accion; //Acción donde se guardar la función a llamar en el submit Ejemplo: 0=Insert, 1=Select...
    boolean sesionActiva = true; //Guardo el estado de la sesión del usuario en el tomcat
    boolean permiso;//Guardo si tiene o no permiso de ingresar a la pantalla 
    String usuario;//Código del usuario logueado
    String menu;//String de los permisos del menu 
    String mensajes = "";//Variable para cargar el texto del resultado de las validaciones o acciones
    boolean mensaje;//Variable bandera para saber si se muestra o no el mensaje
    //private boolean existVendor;
    //Variables de la pantalla
//    private ArrayList<DtoVendor> vendors = new ArrayList<>();//Variable con la lista de datos
//    private ArrayList<DtoVendorContact> vendorsContacts = new ArrayList<>();//Variable con la lista de datos
//    private ArrayList<DtoVendorAddress> vendorsAddress = new ArrayList<>();//Variable con la lista de datos
//    private ArrayList<DtoVendorAddressQuery> vendorsAddressQuery = new ArrayList<>();//Variable con la lista de datos

    //Handles the postal codes 
    // private ArrayList<KeyCombosPostalCodes> postalCodes = new ArrayList<>();//Variable con la lista de datos
//    private ArrayList<KeyCombos> types = new ArrayList<>();
//    private ArrayList<KeyCombosString> provincePostalCodes = new ArrayList<>();
    //register vars vars
    private String id;
    private String companyName;
    private String name;
    private String phoneNumber;
    private String webSite;
    private String email;
    private String email2;
    private String password;
    private String password2;
    private String city;

    private String idEdit;
    private ArrayList<KeyCombosString> citiesRegister = new ArrayList<>();

    public Register() {
        Map<String, Object> session = ActionContext.getContext().getSession();
//        if (session.get("en-sesion") == null) {
//            sesionActiva = true;
        vdk = ORMUtil.getSesionCMS().openSession();
        usuario = String.valueOf(session.get("user"));
        permiso = true;
        menu = "";
        chargeSelect();

    }

    //SET GET DEFAULT
    public Map getSession() {
        return session;
    }

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public boolean getSesionActiva() {
        return sesionActiva;
    }

    public void setSesionActiva(boolean sesionActiva) {
        this.sesionActiva = sesionActiva;
    }

    public boolean getPermiso() {
        return permiso;
    }

    public void setPermiso(boolean permiso) {
        this.permiso = permiso;
    }

    public boolean getMensaje() {
        return mensaje;
    }

    public void setMensaje(boolean mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //SET GET CUSToMIZED
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<KeyCombosString> getCitiesRegister() {
        return citiesRegister;
    }

    public void setCitiesRegister(ArrayList<KeyCombosString> citiesRegister) {
        this.citiesRegister = citiesRegister;
    }

    ////////
    @Override
    public String execute() { //the class start here
      
       if (permiso == true) {
           process();       
           vdk.close();//Cerrar la conexión de la base de datos SIEMPRE
       }else{
         permiso=true;
       }
        return SUCCESS;
    }

    //METODOS ADICIONALES
    
    public void process() {
        switch (accion) {
            case 1: {
                save();
                break;
            }

        }

    }

    public void validateMail() {
        SendMails send = new SendMails();
        send.sendMail(email);

    }

    public boolean validateFields() {
        boolean flag = true;
        mensajes = "";
        mensaje = false;
        //VALIDAR QUE CAMPOS NO SEAN BLANCOS NI NULOS
        if ((companyName == null) || (companyName.isEmpty())) {
            mensajes = mensajes + "danger<>Error<>Please complete field 'Name of Company'.|";
            flag = false;
        }
        if ((name == null) || (name.isEmpty())) {
            mensajes = mensajes + "danger<>Error<>Please complete field 'Name of Company'.|";
            flag = false;
        }

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        Matcher mather = pattern.matcher(email);
        if (!mather.find()) {
            mensajes = mensajes + "danger<>Error<>The Email hasn't a valid text format.|";
            flag = false;
            mensaje = true;

        }
        Pattern pattern2 = Pattern.compile("^(.+)@(.+)$");

        Matcher mather2 = pattern2.matcher(email2);
        if (!mather2.find()) {
            mensajes = mensajes + "danger<>Error<>The Email of validation hasn't a valid text format.|";
            flag = false;
            mensaje = true;

        }
        if ((!(email.equals(email2))) || (email2.isEmpty())) {
            mensajes = mensajes + "danger<>Error<>You provided two differents email address or must complete the form .|";
            flag = false;
            mensaje = true;
        }
        if (!(password.equals(password2))) {
            mensajes = mensajes + "danger<>Error<>You provided two differents passwords .|";
            flag = false;
            mensaje = true;
        }

        if (!flag) {
            mensaje = flag;
        }

        return flag;
    }

    public void save() {
        insert();
    }

    public void chargeSelect() {

        citiesRegister = CombosMaintenance.getCitiesRegister(vdk);

    }
   public class samp extends HttpServlet
{
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            response.setContentType("text/html");
           
                response.sendRedirect("login.jsp");
           
        }
    }
    public void insert() {
        if (validateFields()) {//Valido los campos del formulario
            Transaction tn = null;//Inicializo la transacción de la BD en null
            try {
                tn = vdk.beginTransaction();//Inicializo la transacción de la DB 

                DtoVendorRequest m = new DtoVendorRequest();//Creo un objeto del tipo style

                m.setCompanyName(companyName);
                m.setName(name); //should be vname in vendors table
                m.setPhoneNumber(phoneNumber); //contact1
                m.setWebSite(webSite); //contact2
                m.setCity(city); //address1
                m.setEmail(email); //coontact 3
                m.setPassword(password); //vendorUser

                AdminSQL.saveVendorTemp(vdk, m);
                
                //  AdminSQL.incrementConsecutive(mdk, "codeVendor");
                //AdmConsultas.bitacora(o2c, usuario, "Encargado guardado Tipo: " + tipo + ", Codigo: " + codigo);
                tn.commit();// Hago Commit a la transacción para guardar el registro
                //existVendor = true;
                validateMail();
                mensajes = mensajes + "info<>Information<>Account saved successfully.";
                mensaje = true;
                accion = 5;
              

            } catch (HibernateException x) {
                //AdmConsultas.error(o2c, x.getMessage());
                // mensajes = mensajes + "danger<>Error<>Error al guardar encargados: " + codigo + ": " + ExceptionUtils.getMessage(x) + ".";
                mensajes = mensajes + "danger<>Error<>Error.|";
                mensaje = true;
                if (tn != null) {//Si hay error y el transacción es distinto de null, es porque la transacción existe, entoncs hago rollback
                    tn.rollback();
                }
            }
            mensaje = true;
        }
    }

    /**
     * @return the email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2 the email2 to set
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return the idEdit
     */
    public String getIdEdit() {
        return idEdit;
    }

    /**
     * @param idEdit the idEdit to set
     */
    public void setIdEdit(String idEdit) {
        this.idEdit = idEdit;
    }

    /**
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2 the password2 to set
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

}
