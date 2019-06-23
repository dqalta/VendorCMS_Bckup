/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vendorAdmin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sql.vendorScreen.maintenance.DtoShippingZone;
import sql.vendorScreen.maintenance.MaintenanceSQL;
import util.Fechas;
import web.sesion.ORMUtil;
import web.util.CombosMaintenance;
import web.util.KeyCombos;
import web.util.KeyCombosString;

/**
 *
 * @author CR104978
 */
public class ShippingZone extends ActionSupport implements SessionAware {

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
    private ArrayList<DtoShippingZone> shippingZones = new ArrayList<>();//Variable con la lista de datos
    private ArrayList<KeyCombosString> provincePostalCodes = new ArrayList<>();
    private ArrayList<KeyCombosString> cities = new ArrayList<>();

    //Variables del mantenimiento
    private int id;
    private int idPostalCode;
    private String idVendor;
    private float costPerUnit;    
    private boolean active;
    private int idCode ;
    private int idEdit;
    private String province;
        private String city;
    public ShippingZone() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session.get("en-sesion") != null) {
            sesionActiva = true;
            vdk = ORMUtil.getSesionCMS().openSession();
            usuario = String.valueOf(session.get("user"));
            permiso = true; //AdmConsultas.getPermiso(o2c, "ADMINISTRACIÓN", "Encargados", usuario);            
            menu = "";//AdmConsultas.menuUsuario(o2c, usuario);
           chargeSelect(); // fill the select with the categories
        } else {
            sesionActiva = false;
            System.out.println(sesionActiva);
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
 

    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

   
    public int getIdPostalCode() {
        return idPostalCode;
    }

    public void setIdPostalCode(int idPostalCode) {
        this.idPostalCode = idPostalCode;
    }

   
    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    
    public float getCostPerUnit() {
        return costPerUnit;
    }

   
    public void setCostPerUnit(float costPerUnit) {
        this.costPerUnit = costPerUnit;
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
      
    
  
    public int getIdCode() {
        return idCode;
    }

   
    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }


    public ArrayList<DtoShippingZone> getShippingZones() {
        return shippingZones;
    }

    public void setShippingZones(ArrayList<DtoShippingZone> shippingZones) {
        this.shippingZones = shippingZones;
    }
  
    public String getProvince() {
        return province;
    }


    public void setProvince(String province) {
        this.province = province;
    }

   
    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<KeyCombosString> getProvincePostalCodes() {
        return provincePostalCodes;
    }

   
    public void setProvincePostalCodes(ArrayList<KeyCombosString> provincePostalCodes) {
        this.provincePostalCodes = provincePostalCodes;
    }

   
    public ArrayList<KeyCombosString> getCities() {
        return cities;
    }

 
    public void setCities(ArrayList<KeyCombosString> cities) {
        this.cities = cities;
    }

    
    //// Class structure and logical 
    
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
        
    }

    public void clearFields() {
        setId(0);
        accion = 0;
        setIdEdit(0);
        setActive(false);
    }

    public void chargeSelect() {
        
        //calls the database and save the data in dto object to display them before in the correct combo
        provincePostalCodes= CombosMaintenance.getProvincePostalCodes(vdk);
        cities= CombosMaintenance.getCitiesPostalCodes(vdk, province); 
        chargePostalZones();
      //  postalCodes= CombosMaintenance.getPostalCodes(mdk, city); 
        

  //shippingZones = MaintenanceSQL.getFilteredShippingZones(vdk, city);
    }

//    public boolean validateFields() {
//        boolean flag = true;
//        mensajes = "";
//        mensaje = false;
//        //VALIDAR QUE CAMPOS NO SEAN BLANCOS NI NULOS
//        if ((pname == null) || (pname.isEmpty())) {
//            mensajes = mensajes + "danger<>Error<>Please complete field 'Description'.|";
//            flag = false;
//        }
//        if (!flag) {
//            mensaje = flag;
//        }
//        return flag;
//    }

    public void save() {
//        if (getIdEdit() == 0) {
//            insert();
//        } else {
//            update();
//        }
    }

    public void chargePostalZones() {
      
               shippingZones = MaintenanceSQL.getShippingZones(vdk);

        
    }

    public void insert() {
//        if (validateFields()) {//Valido los campos del formulario
//            Transaction tn = null;//Inicializo la transacción de la BD en null
//            try {
//                tn = mdk.beginTransaction();//Inicializo la transacción de la DB 
//
//                DtoCollection m = new DtoCollection();//Creo un objeto del tipo Manufacturer
//
//                //Seteo los datos del objeto excepto el id por que es Auto Incremental
//                m.setDescription(pname);
//
//                m.setCreated(Fechas.ya());
//                m.setCreatedBy(usuario);
//                m.setModified(Fechas.ya());
//                m.setModifiedBy(usuario);
//                m.setActive(isActive());//Lo puse en true porque se me olvidó crear el check en el formulario, en la noche hacemos eso jajaja
//
//                MaintenanceSQL.saveCollection(mdk, m);
//                //AdmConsultas.bitacora(o2c, usuario, "Encargado guardado Tipo: " + tipo + ", Codigo: " + codigo);
//
//                tn.commit();// Hago Commit a la transacción para guardar el registro
//                clearFields();
//                mensajes = mensajes + "info<>Information<>Collection saved successfully.";
//                mensaje = true;
//
//            } catch (HibernateException x) {
//                //AdmConsultas.error(o2c, x.getMessage());
//                // mensajes = mensajes + "danger<>Error<>Error al guardar encargados: " + codigo + ": " + ExceptionUtils.getMessage(x) + ".";
//                mensajes = mensajes + "danger<>Error<>Error.|";
//                mensaje = true;
//                if (tn != null) {//Si hay error y el transacción es distinto de null, es porque la transacción existe, entoncs hago rollback
//                    tn.rollback();
//                }
//            }
//            mensaje = true;
//        }
    }

    public void update() {
//        if (validateFields()) {
//            Transaction tn = null;
//            try {
//                tn = mdk.beginTransaction();
//                DtoCollection m = MaintenanceSQL.getCollection(mdk, getIdEdit());
//                if (m != null) {
//
//                    m.setDescription(pname);
//                    m.setModified(Fechas.ya());
//                    m.setModifiedBy(usuario);
//                    m.setActive(isActive());//Lo puse en true porque se me olvidó crear el check en el formulario, en la noche hacemos eso jajaja
//
//                    MaintenanceSQL.updateCollection(mdk, m);
//                    // AdmConsultas.bitacora(o2c, usuario, "Encargado modificado Tipo: " + tipo + ", Codigo: " + codigo);
//
//                    tn.commit();
//                    clearFields();
//                    mensajes = mensajes + "info<>Information<>Collection modified successfully.";
//                    mensaje = true;
//                } else {
//                    insert();
//                }
//            } catch (HibernateException x) {
//                //AdmConsultas.error(o2c, x.getMessage());
//                // mensajes = mensajes + "danger<>Error<>Error al modificar encargados: " + codigo + ": " + ExceptionUtils.getMessage(x) + ".";
//                mensajes = mensajes + "danger<>Error<>Error.|";
//                mensaje = true;
//                if (tn != null) {
//                    tn.rollback();
//                }
//            }
//            mensaje = true;
//        }
    }

    public void readForUpdate() {
//        DtoShippingZone m = MaintenanceSQL.getShippingZone(vdk, getIdEdit());
//        if (m != null) {
//            setIdEdit(m.getId());
//            pname = m.getDescription();
//            setActive(m.getActive());
//        } else {
//            mensajes = mensajes + "danger<>Error<>Collection does not exist.";
//            mensaje = true;
//        }
  }

 
    

}