package sql.vendorScreen.maintenance;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
//import sql.masonryAdmin.admin.DtoRol;
import util.Fechas;
import util.Numeros;

/**
 *
 * @author CR104978
 */
public class MaintenanceSQL {
    //maintenance for shipping zones
 public static ArrayList<DtoShippingZone> getShippingZones(Session vdk) {
        ArrayList<DtoShippingZone> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " p.id,"
                + " p.city,"
                + " p.postalCode,"
                + " IF(c.id IS NULL, '0' ,c.id) as idCode,"                
                + " IF(c.idPostalCode IS NULL, '0' ,c.idPostalCode) as idPostalCode,"
                + " IF(c.costPerUnit IS NULL, '0' ,c.costPerUnit) as costPerUnit,"      
                + " IF(c.active IS NULL, false , c.active) as active"       
                + " FROM postalCode as p"
                + " LEFT JOIN vendorPostalCode as c"
                + " ON p.active = 1"
                + " order BY p.city")            
                .setResultTransformer(Transformers.aliasToBean(DtoShippingZone.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoShippingZone) itr.next());
        }
        return a;
    }
 public static ArrayList<DtoShippingZone> getFilteredShippingZones(Session vdk, String city) {
        ArrayList<DtoShippingZone> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " p.id,"
                + " p.city,"
                + " p.postalCode,"
                + " IF(c.id IS NULL, '0' ,c.id) as idCode,"                
                + " IF(c.idPostalCode IS NULL, '0' ,c.idPostalCode) as idPostalCode,"
                + " IF(c.costPerUnit IS NULL, '0' ,c.costPerUnit) as costPerUnit,"      
                + " IF(c.active IS NULL, false , c.active) as active"       
                + " FROM postalCode as p"
                + " LEFT JOIN vendorPostalCode as c"
                + " ON p.active = 1"
                + " order BY p.city"
                + " WHERE p.city := city")
                  .setParameter("id", city)
                .setResultTransformer(Transformers.aliasToBean(DtoShippingZone.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoShippingZone) itr.next());
        }
        return a;
    }


    public static DtoShippingZone getShippingZone(Session vdk, int id) {
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " idPostalCode,"
                + " idVendor,"
                + " costPerUnit,"                
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy,"
                + " active"
                + " FROM vendorPostalCode"
                + " WHERE id = :id")
                .setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(DtoShippingZone.class))
                .list().iterator();
        DtoShippingZone m = null;
        while (itr.hasNext()) {
            m = (DtoShippingZone) itr.next();
        }
        return m;
    }
    // charge in one combo the cities from one province
     public static ArrayList<DtoString> getCitiesPostalCodes(Session vdk, String province) {
        ArrayList<DtoString> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT distinct city as description"           
                + " FROM postalCode"
                + " WHERE province = :province")
                .setParameter("province", province)
                .setResultTransformer(Transformers.aliasToBean(DtoString.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoString) itr.next());
        }
        return a;
    }
      public static ArrayList<DtoString> getPostalCodes(Session vdk, String city) {
        ArrayList<DtoString> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT distinct postalCode as description"           
                + " FROM postalCode"
                + " WHERE city = :city")
                .setParameter("city", city)
                .setResultTransformer(Transformers.aliasToBean(DtoString.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoString) itr.next());
        }
        return a;
    }
      public static ArrayList<DtoString> getProvincePostalCodes(Session vdk) {
        ArrayList<DtoString> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT distinct province as description"      
                + " FROM postalCode")
                .setResultTransformer(Transformers.aliasToBean(DtoString.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoString) itr.next());
        }
        return a;
    }
}

