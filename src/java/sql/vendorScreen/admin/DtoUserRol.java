/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.vendorScreen.admin;

import java.util.Date;

/**
 *
 * @author CR104978
 */
public class DtoUserRol {

  private int id;
  private int idVendorRol;
  private String code;

    public DtoUserRol() {
        
    }

  
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

   
    public int getIdVendorRol() {
        return idVendorRol;
    }

 
    public void setIdVendorRol(int idVendorRol) {
        this.idVendorRol = idVendorRol;
    }

  
    public String getCode() {
        return code;
    }

   
    public void setCode(String code) {
        this.code = code;
    }

  
}
