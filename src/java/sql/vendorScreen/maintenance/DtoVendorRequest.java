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
public class DtoVendorRequest implements Serializable {

    private int id;
    private String companyName;
    private String vname;
    private String phoneNumber;
    private String webSite;
    private String city;
    private String email;
    private String password;

    public DtoVendorRequest() {
    }

    public int getId() {
        return id;
    }

  
    public void setId(int id) {
        this.id = id;
    }

   
    public String getCompanyName() {
        return companyName;
    }

   
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

   
    public String getVName() {
        return vname;
    }

   
    public void setVName(String vname) {
        this.vname = vname;
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

  
    public String getCity() {
        return city;
    }

   
    public void setCity(String city) {
        this.city = city;
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

   

}
