/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.util;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import sql.vendorScreen.maintenance.DtoShippingZone;
import sql.vendorScreen.maintenance.DtoString;
import sql.vendorScreen.maintenance.MaintenanceSQL;
//import sql.masonryAdmin.maintenance.MaintenanceSQL;
//import sql.masonryAdmin.maintenance.DtoMetricsSystem;


/**
 *
 * @author CR104978
 */
public class CombosMaintenance {
    
    
    //class to show the provinces
        public static ArrayList<KeyCombosString> getProvincePostalCodes(Session vdk) {
        ArrayList<KeyCombosString> combo = new ArrayList<>();
//        combo.add(new KeyCombosString("NONE"));
        List<DtoString> provincePostalCodes = MaintenanceSQL.getProvincePostalCodes(vdk);
        for (DtoString c : provincePostalCodes) {
            combo.add(new KeyCombosString (c.getDescription()));
        }
        return combo;
    }
    //class who gets the citys from one province
    
       public static ArrayList<KeyCombosString> getCitiesPostalCodes(Session vdk, String province) {
        ArrayList<KeyCombosString> combo = new ArrayList<>();     
        combo.add(new KeyCombosString("NONE"));
        List<DtoString> cities = MaintenanceSQL.getCitiesPostalCodes(vdk, province);
        for (DtoString c : cities) {
            combo.add(new KeyCombosString(c.getDescription()));
        }
        return combo;
    }
        public static ArrayList<KeyCombosString> getCitiesRegister(Session vdk) {
        ArrayList<KeyCombosString> combo = new ArrayList<>();     
        combo.add(new KeyCombosString("NONE"));
        List<DtoString> cities = MaintenanceSQL.getCitiesRegisterV(vdk);
        for (DtoString c : cities) {
            combo.add(new KeyCombosString(c.getDescription()));
        }
        return combo;
    }
       //this class returns the postal codes from a city 
        public static ArrayList<KeyCombosString> getPostalCodes(Session mdk, String city) {
        ArrayList<KeyCombosString> combo = new ArrayList<>();     
        combo.add(new KeyCombosString("NONE"));
        List<DtoString> postalCodes = MaintenanceSQL.getPostalCodes(mdk, city);
        for (DtoString c : postalCodes) {
            combo.add(new KeyCombosString(c.getDescription()));
        }
        return combo;
    }

//     public static ArrayList<KeyCombos> getMaterials(Session mdk) {
//        ArrayList<KeyCombos> combo = new ArrayList<>();
//        combo.add(new KeyCombos(0, "SELECT ONE CATEGORY"));
//        List<DtoMaterial> materials = MaintenanceSQL.getMaterials(mdk);
//        for (DtoMaterial c : materials) {
//            combo.add(new KeyCombos((c.getId()), c.getDescription()));
//        }
//        return combo;
//    }
//      public static ArrayList<KeyCombos> getUnits(Session mdk) {
//        ArrayList<KeyCombos> combo = new ArrayList<>();
//        combo.add(new KeyCombos(0, "SELECT ONE METRIC SYSTEM"));
//        List<DtoMetricsSystem> units = MaintenanceSQL.getMetricsSystems(mdk);
//        for (DtoMetricsSystem c : units) {
//            combo.add(new KeyCombos((c.getId()), c.getDescription()));
//        }
//        return combo;
//    }

}
