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
public class DtoRoles implements Serializable {

    private int id;
    private int idVendor;
    private float minValue;
    private boolean freeShipping;
    private float additionalCost;
    private float flatRate;
    private float skidDeposit;
    private Date created;
    private String createdBy;
    private Date modified;
    private String modifiedBy;
    private Boolean active;

    public DtoRoles() {
    }

      public int getId() {
        return id;
    }
  public void setId(int id) {
        this.id = id;
    }
 public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
    }
   public float getMinValue() {
        return minValue;
    }
   public void setMinValue(float minValue) {
        this.minValue = minValue;
    }
    public boolean isFreeShipping() {
        return freeShipping;
    }

      public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public float getAdditionalCost() {
        return additionalCost;
    }

      public void setAdditionalCost(float additionalCost) {
        this.additionalCost = additionalCost;
    }

    public float getFlatRate() {
        return flatRate;
    }
   public void setFlatRate(float flatRate) {
        this.flatRate = flatRate;
    }
  public Date getCreated() {
        return created;
    }

     public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }
  public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
   public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the skidDeposit
     */
    public float getSkidDeposit() {
        return skidDeposit;
    }

    /**
     * @param skidDeposit the skidDeposit to set
     */
    public void setSkidDeposit(float skidDeposit) {
        this.skidDeposit = skidDeposit;
    }

  

}
