/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.vendorScreen.maintenance;

import java.io.Serializable;

import java.util.Date;

/**
 *
 * @author CR104978
 */
/*This java file defines every constructor to be used as a DTO from an object*/
public class DtoShippingZone implements Serializable {

    private int id;
    private String city;
    private String idPostalCode;
    private String idVendor;
    private String costPerUnit;    
    private int active;
    private String postalCode;
    
    private String idCode;
    private boolean activeP;

    public DtoShippingZone() {
    }

   
    public int getId() {
        return id;
    }

   
    public void setId(int id) {
        this.id = id;
    }

 
    public String getIdPostalCode() {
        return idPostalCode;
    }

   
    public void setIdPostalCode(String idPostalCode) {
        this.idPostalCode = idPostalCode;
    }

    
    public String getIdVendor() {
        return idVendor;
    }

    
    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    
    public String getCostPerUnit() {
        return costPerUnit;
    }

    
    public void setCostPerUnit(String costPerUnit) {
        this.costPerUnit = costPerUnit;
    }



    public String getPostalCode() {
        return postalCode;
    }

    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    
    public String getIdCode() {
        return idCode;
    }

    
    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    /**
     * @return the activeP
     */
    public boolean isActiveP(String active) {
        activeP= Boolean.parseBoolean(active);
        return activeP;
    }

    /**
     * @param activeP the activeP to set
     */
    public void setActiveP(boolean activeP) {
        this.activeP = activeP;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }


    public void setActive(int active) {
        this.active = active;
    }

  

}
