/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vendorAdmin.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sql.vendorScreen.admin.AdminSQL;
import sql.vendorScreen.admin.DtoRol;
import sql.vendorScreen.admin.DtoVendorUser;
import sql.vendorScreen.admin.DtoUserRol;
//import sql.vendorAdmin.maintenance.DtoCollection;
import util.Fechas;
import util.Generales;
import util.Numeros;
import web.session.ORMUtil;
import web.util.CombosAdmin;
import web.util.KeyCombos;

/**
 *
 * @author CR104978
 */
public class User extends ActionSupport implements SessionAware {

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
    String idVendor;
    String menu;//String de los permisos del menu 
    String mensajes = "";//Variable para cargar el texto del resultado de las validaciones o acciones
    boolean mensaje;//Variable bandera para saber si se muestra o no el mensaje

    //Variables de la pantalla
    private ArrayList<DtoVendorUser> users = new ArrayList<>();//Variable con la lista de datos
    private ArrayList<DtoUserRol> rols = new ArrayList<>();//Variable con la lista de datos

    ArrayList<KeyCombos> roles = new ArrayList<>();

    //Variables del mantenimiento
    String codeVendorUser;
    String fullName;
    String email;
    boolean active;
    int idEdit;
    int[] rol;
    boolean menuAdmin;
    boolean menuProdAdmin;
    //vars of the rols
    String[] userRol;
    ArrayList<KeyCombos> userRoles = new ArrayList<>();

    private int id;
    private int idVendorRol;

    public User() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session.get("en-sesion") != null) {
            sesionActiva = true;
            vdk = ORMUtil.getSesionCMS().openSession();
            usuario = String.valueOf(session.get("user"));
            idVendor = String.valueOf(session.get("idVendor"));
            permiso = true; //AdmConsultas.getPermiso(o2c, "ADMINISTRACIÓN", "Encargados", usuario);            
            menu = "";//AdmConsultas.menuUsuario(o2c, usuario);
            chargeSelect();
        } else {
            sesionActiva = false;
        }
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

    public ArrayList<KeyCombos> getRoles() {
        return roles;
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

    //SET GET CUSTUMIZED
    public String getCodeVendorUser() {
        return codeVendorUser;
    }

    public void setCode(String codeVendorUser) {
        this.codeVendorUser = codeVendorUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getIdEdit() {
        return idEdit;
    }

    public void setIdEdit(int idEdit) {
        this.idEdit = idEdit;
    }

    public int[] getRol() {
        return rol;
    }

    public void setRol(int[] rol) {
        this.rol = rol;
    }

    public boolean isMenuAdmin() {
        return menuAdmin;
    }

    public void setMenuAdmin(boolean menuAdmin) {
        this.menuAdmin = menuAdmin;
    }

    public boolean isMenuProdAdmin() {
        return menuProdAdmin;
    }

    public void setMenuProdAdmin(boolean menuProdAdmin) {
        this.menuProdAdmin = menuProdAdmin;
    }

    public ArrayList<DtoVendorUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<DtoVendorUser> users) {
        this.users = users;
    }

    public ArrayList<DtoUserRol> getRols() {
        return rols;
    }

    public void setRols(ArrayList<DtoUserRol> rols) {
        this.rols = rols;
    }

    @Override
    public String execute() {
        if (permiso == true) {
            process();
            vdk.close();//Cerrar la conexión de la base de datos SIEMPRE
        }
        return SUCCESS;
    }

    //METODOS ADICIONALES
    public void process() {
        switch (accion) {
            case 1:
                save();
                break;
            case 2:
                readForUpdate();
                break;
        }
        chargeUsers();
    }

    public void chargeSelect() {
        roles = CombosAdmin.roles(vdk);
    }

    public void clearFields() {
        accion = 0;
        idEdit = 0;
        codeVendorUser = "";
        fullName = "";
        email = "";
        active = true;
        menuAdmin = false;
        menuProdAdmin = false;

        chargeSelect();
    }

    public boolean validateFields() {
        boolean flag = true;
        mensajes = "";
        mensaje = false;
        //VALIDAR QUE CAMPOS NO SEAN BLANCOS NI NULOS
        if ((fullName == null) || (fullName.isEmpty())) {
            mensajes = mensajes + "danger<>Error<>Please complete field 'Full Name'.|";
            flag = false;
            mensaje = true;

        }

        if ((email == null) || (email.isEmpty())) {
            mensajes = mensajes + "danger<>Error<>Please complete field 'Email'.|";
            flag = false;
            mensaje = true;
        }
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        Matcher mather = pattern.matcher(email);
        if (!mather.find()) {
            mensajes = mensajes + "danger<>Error<>The Email hasn't a valid text format.|";
            flag = false;
            mensaje = true;

        }

        if (rol.length == 0) {
            mensajes = mensajes + "danger<>Error<>Select one 'Rol'.|";
            flag = false;
            mensaje = true;
        }

        if (!flag) {
            mensaje = flag;
        }
        return flag;
    }

    public void save() {
        if (idEdit == 0) {
            insert();
        } else {
            update();
        }
    }

    public void chargeUsers() {
        users = AdminSQL.getUsers(vdk);
    }

    public void insert() {
        if (validateFields()) {//Valido los campos del formulario
            Transaction tn = null;//Inicializo la transacción de la BD en null
            try {
                tn = vdk.beginTransaction();//Inicializo la transacción de la DB 

                DtoVendorUser m = new DtoVendorUser();//Creo un objeto del tipo Manufacturer

                String code_ = Generales.generateCode(fullName);
                code_ = code_ + String.format("%03d", AdminSQL.getConsecutive(vdk, "codeVendorUser"));
                codeVendorUser = code_;

                m.setCodeVendorUser(codeVendorUser);
                m.setNickName("");
                m.setFullName(fullName);
                m.setEmail(email);
                m.setPasswordVendorUser("");
                m.setIdVendor(idVendor);
                m.setCreated(Fechas.ya());
                m.setCreatedBy(usuario);
                m.setModified(Fechas.ya());
                m.setModifiedBy(usuario);
                m.setActive(active);
                m.setStatusVendorUser("PENDING");
                //Set id roles by vendor user
                AdminSQL.saveVendorUser(vdk, m);
                tn.commit();// Hago Commit a la transacción para guardar el registro

                saveVendorRoles(codeVendorUser);

                clearFields();
                mensajes = mensajes + "info<>Information<>User saved successfully.";
                mensaje = true;

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

    public void saveVendorRoles(String code) {
        Transaction tn = null;
        try {
            tn = vdk.beginTransaction();
            DtoVendorUser p = AdminSQL.getUser(vdk, code);
            if (p != null) {

                AdminSQL.deleteUserRols(vdk, code);

                for (int i = 0; i < rol.length; i++) {
                    AdminSQL.saveVendorUserRol(vdk, code, rol[i], idVendor);
                }
                tn.commit();
            } else {
                insert();
            }
        } catch (HibernateException x) {
            mensajes = mensajes + "danger<>Error<>Save user isn't possible: " + code + ": " + ExceptionUtils.getMessage(x) + ".";
            mensaje = true;
            if (tn != null) {
                tn.rollback();
            }
        }
    }

    public void readForUpdate() {
        DtoVendorUser m = AdminSQL.getUser(vdk, codeVendorUser);
        if (m != null) {
            idEdit = m.getId();
            fullName = m.getFullName();
            email = m.getEmail();
            active = m.isActive();
             userRol= AdminSQL.getVendorUserRols(vdk, codeVendorUser, idVendor).split(",");
        } else {
            mensajes = mensajes + "danger<>Error<>User doesn't exist";
            mensaje = true;
        }
    }

    public void update() {
        if (validateFields()) {
            Transaction tn = null;
            try {
                tn = vdk.beginTransaction();
                DtoVendorUser m = AdminSQL.getUser(vdk, codeVendorUser);
                if (m != null) {

                    m.setCodeVendorUser(codeVendorUser);
                    m.setNickName("");
                    m.setFullName(fullName);
                    m.setEmail(email);
                    m.setPasswordVendorUser("");
                    m.setIdVendor(idVendor);
//                    m.setMenuAdmin(menuAdmin);
//                    m.setMenuProdAdmin(menuProdAdmin);
                    m.setCreated(Fechas.ya());
                    m.setCreatedBy(usuario);
                    m.setModified(Fechas.ya());
                    m.setModifiedBy(usuario);
                    m.setActive(active);
                    m.setStatusVendorUser("PENDING");

                    AdminSQL.updateUser(vdk, m);
                    // AdmConsultas.bitacora(o2c, usuario, "Encargado modificado Tipo: " + tipo + ", Codigo: " + codigo);
                    tn.commit();
                    saveVendorRoles(codeVendorUser);
                    clearFields();
                    mensajes = mensajes + "info<>Information<>Rol modified successfully.";
                    mensaje = true;
                } else {
                    insert();
                }
            } catch (HibernateException x) {
                //AdmConsultas.error(o2c, x.getMessage());
                // mensajes = mensajes + "danger<>Error<>Error al modificar encargados: " + codigo + ": " + ExceptionUtils.getMessage(x) + ".";
                mensajes = mensajes + "danger<>Error<>Error.|";
                mensaje = true;
                if (tn != null) {
                    tn.rollback();
                }
            }
            mensaje = true;
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idVendorRol
     */
    public int getIdVendorRol() {
        return idVendorRol;
    }

    /**
     * @param idVendorRol the idVendorRol to set
     */
    public void setIdVendorRol(int idVendorRol) {
        this.idVendorRol = idVendorRol;
    }

    /**
     * @return the userRol
     */
    public String[] getUserRol() {
        return userRol;
    }

    /**
     * @param userRol the userRol to set
     */
    public void setUserRol(String[] userRol) {
        this.userRol = userRol;
    }

    /**
     * @return the userRoles
     */
    public ArrayList<KeyCombos> getUserRoles() {
        return userRoles;
    }

    /**
     * @param userRoles the userRoles to set
     */
    public void setUserRoles(ArrayList<KeyCombos> userRoles) {
        this.userRoles = userRoles;
    }

}
