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
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sql.vendorScreen.admin.AdminSQL;
import sql.vendorScreen.admin.DtoRol;
//import sql.vendorScreen.maintenance.DtoCollection;
import util.Fechas;
import web.session.ORMUtil;

/**
 *
 * @author CR104978
 */
public class Rol extends ActionSupport implements SessionAware {

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

    //Variables de la pantalla
    private ArrayList<DtoRol> roles = new ArrayList<>();//Variable con la lista de datos

    //Variables del mantenimiento
    private String description;
    private int idEdit;

    //structure of the menu
    private boolean user;
    private boolean rol;
    private boolean audit;
    private boolean myProducts;
    private boolean shippingRules;
    private boolean shippingCodes;
    private boolean orders;
    private boolean inventory;

    public Rol() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session.get("en-sesion") != null) {
            sesionActiva = true;
            vdk = ORMUtil.getSesionCMS().openSession();
            usuario = String.valueOf(session.get("user"));
            permiso = true; //AdmConsultas.getPermiso(o2c, "ADMINISTRACIÓN", "Encargados", usuario);            
            menu = "";//AdmConsultas.menuUsuario(o2c, usuario);
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
    public ArrayList<DtoRol> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<DtoRol> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdEdit() {
        return idEdit;
    }

    public void setIdEdit(int idEdit) {
        this.idEdit = idEdit;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public boolean isAudit() {
        return audit;
    }

    public void setAudit(boolean audit) {
        this.audit = audit;
    }
    /// set the user rol detail vars

    public boolean isMyProducts() {
        return myProducts;
    }

    public void setMyProducts(boolean myProducts) {
        this.myProducts = myProducts;
    }

    public boolean isShippingRules() {
        return shippingRules;
    }

    public void setShippingRules(boolean shippingRules) {
        this.shippingRules = shippingRules;
    }

    public boolean isShippingCodes() {
        return shippingCodes;
    }

    public void setShippingCodes(boolean shippingCodes) {
        this.shippingCodes = shippingCodes;
    }

    public boolean isOrders() {
        return orders;
    }

    public void setOrders(boolean orders) {
        this.orders = orders;
    }

    public boolean isInventory() {
        return inventory;
    }

    public void setInventory(boolean inventory) {
        this.inventory = inventory;
    }

    //execute the parameters sended by struts --charging dom area
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
        System.out.println("idEdit value=" + idEdit);
        switch (accion) {
            case 1:
                save();
                break;
            case 2:
                readForUpdate();
                break;
        }
        chargeRols();
    }

    public void clearFields() {
        accion = 0;
        idEdit = 0;
        user = false;
        rol = false;
        audit = false;
        description = "";
        myProducts = false;
        shippingRules = false;
        shippingCodes = false;
        inventory = false;
        orders = false;
    }

    public boolean validateFields() {
        boolean flag = true;
        mensajes = "";
        mensaje = false;
        //VALIDAR QUE CAMPOS NO SEAN BLANCOS NI NULOS
        if ((description == null) || (description.isEmpty())) {
            mensajes = mensajes + "danger<>Error<>Please complete field 'Description'.|";
            flag = false;
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

    public void chargeRols() {
        roles = AdminSQL.getRols(vdk);
    }

    public void insert() {
        if (validateFields()) {//Valido los campos del formulario
            Transaction tn = null;//Inicializo la transacción de la BD en null
            try {
                tn = vdk.beginTransaction();//Inicializo la transacción de la DB 

                DtoRol m = new DtoRol();//Creo un objeto del tipo rol

                //Seteo los datos del objeto excepto el id por que es Auto Incremental
                m.setDescription(description);
                m.setCreated(Fechas.ya());
                m.setCreatedBy(usuario);
                m.setModified(Fechas.ya());
                m.setModifiedBy(usuario);

                AdminSQL.saveRol(vdk, m);
                //AdmConsultas.bitacora(o2c, usuario, "Encargado guardado Tipo: " + tipo + ", Codigo: " + codigo);

                tn.commit();// Hago Commit a la transacción para guardar el registro
                saveRolDetails(AdminSQL.getLastIdRol(vdk, usuario));
                clearFields();
                mensajes = mensajes + "info<>Information<>Rol saved successfully.";
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

    public void saveRolDetails(int _idRol) {
        Transaction tn = null;
        try {
            tn = vdk.beginTransaction();
            int idRol = _idRol;
            AdminSQL.deleteRolDetails(vdk, idRol);
            Date ya = Fechas.ya();

            //Administration
            AdminSQL.saveRolDetail(vdk, idRol, "ADMINISTRATION", "user", user, usuario, ya);
            AdminSQL.saveRolDetail(vdk, idRol, "ADMINISTRATION", "rol", rol, usuario, ya);
            AdminSQL.saveRolDetail(vdk, idRol, "ADMINISTRATION", "audit", audit, usuario, ya);

            //VendorStructure
            AdminSQL.saveRolDetail(vdk, idRol, "PRODUCST&ORDERS", "My products", myProducts, usuario, ya);
            AdminSQL.saveRolDetail(vdk, idRol, "PRODUCST&ORDERS", "Shipping Rules", shippingRules, usuario, ya);
            AdminSQL.saveRolDetail(vdk, idRol, "PRODUCST&ORDERS", "Shipping Codes", shippingCodes, usuario, ya);
            AdminSQL.saveRolDetail(vdk, idRol, "PRODUCST&ORDERS", "Orders", orders, usuario, ya);
            AdminSQL.saveRolDetail(vdk, idRol, "PRODUCST&ORDERS", "Inventory", inventory, usuario, ya);

            tn.commit();
        } catch (HibernateException x) {
            mensajes = mensajes + "danger<>Error<>Error at save rol detail: " + ExceptionUtils.getMessage(x) + ".";
            mensaje = true;
            if (tn != null) {
                tn.rollback();
            }
        }
        mensaje = true;
    }

    public void update() {
        if (validateFields()) {
            Transaction tn = null;
            try {
                tn = vdk.beginTransaction();
                DtoRol m = AdminSQL.getRol(vdk, idEdit);
                if (m != null) {

                    m.setDescription(getDescription());
                    m.setModified(Fechas.ya());
                    m.setModifiedBy(usuario);

                    AdminSQL.updateRol(vdk, m);
                    // AdmConsultas.bitacora(o2c, usuario, "Encargado modificado Tipo: " + tipo + ", Codigo: " + codigo);
                    tn.commit();
                    saveRolDetails(m.getId());
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

    public void readForUpdate() {
        DtoRol m = AdminSQL.getRol(vdk, idEdit);
        if (m != null) {
           idEdit= m.getId();
           description =m.getDescription();

            //Administration
            user = AdminSQL.getRolDetail(vdk, idEdit, "ADMINISTRATION", "user");
            rol = AdminSQL.getRolDetail(vdk, getIdEdit(), "ADMINISTRATION", "rol");
            audit = AdminSQL.getRolDetail(vdk, getIdEdit(), "ADMINISTRATION", "audit");

            //Product Components            
            myProducts = AdminSQL.getRolDetail(vdk, getIdEdit(), "PRODUCST&ORDERS", "My products");
            shippingRules = AdminSQL.getRolDetail(vdk, getIdEdit(), "PRODUCST&ORDERS", "Shipping Rules");
            shippingCodes = AdminSQL.getRolDetail(vdk, getIdEdit(), "PRODUCST&ORDERS", "Shipping Codes");
            orders = AdminSQL.getRolDetail(vdk, getIdEdit(), "PRODUCST&ORDERS", "Orders");
            inventory = AdminSQL.getRolDetail(vdk, getIdEdit(), "PRODUCST&ORDERS", "Inventory");

        } else {
            mensajes = mensajes + "danger<>Error<>Rol does not exist.";
            mensaje = true;
        }
    }

}

