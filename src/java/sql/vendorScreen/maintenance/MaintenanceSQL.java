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

    public static DtoShipping getShippingZone(Session vdk, int id) {
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
                + " WHERE idPostalCode = :id")
                .setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(DtoShipping.class))
                .list().iterator();
        DtoShipping m = null;
        while (itr.hasNext()) {
            m = (DtoShipping) itr.next();
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

    public static void saveShipping(Session vdk, DtoShipping m) {
        vdk.createNativeQuery("INSERT INTO vendorPostalCode"
                + " (idPostalCode, idVendor, created, createdBy, modified, modifiedBy, active)"
                + " VALUES"
                + " (:idPostalCode, :idVendor, :created, :createdBy, :modified, :modifiedBy, :active)")
                .setParameter("idPostalCode", m.getIdPostalCode())
                .setParameter("idVendor", m.getIdVendor())
                .setParameter("created", m.getCreated())
                .setParameter("createdBy", m.getCreatedBy())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .setParameter("active", m.getActive())
                .executeUpdate();
    }

    public static void saveShippingRol(Session vdk, DtoRoles m) {
        vdk.createNativeQuery("INSERT INTO vendorCostDetails"
                + " ( idVendor, minValue, freeShipping, additionalCost, flatRate, skidDeposit, created, createdBy, modified, modifiedBy, active)"
                + " VALUES"
                + " (:idVendor, :minValue, :freeShipping, :additionalCost, :flatRate, :skidDeposit, :created, :createdBy, :modified, :modifiedBy, :active)")
                .setParameter("idVendor", m.getIdVendor())
                .setParameter("minValue", m.getMinValue())
                .setParameter("freeShipping", m.isFreeShipping())
                .setParameter("additionalCost", m.getAdditionalCost())
                .setParameter("flatRate", m.getFlatRate())
                .setParameter("skidDeposit", m.getSkidDeposit())
                .setParameter("created", m.getCreated())
                .setParameter("createdBy", m.getCreatedBy())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .setParameter("active", m.getActive())
                .executeUpdate();
    }

    public static DtoRoles getRole(Session vdk, int id) {
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " idVendor,"
                + " minValue,"
                + " freeShipping,"
                + " additionalCost,"
                + " flatRate,"
                + " skidDeposit,"
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy,"
                + " active"
                + " FROM vendorCostDetails"
                + " WHERE idVendor = :id")
                .setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(DtoRoles.class))
                .list().iterator();
        DtoRoles m = null;
        while (itr.hasNext()) {
            m = (DtoRoles) itr.next();
        }
        return m;
    }

    public static void updateRole(Session vdk, DtoRoles m) {
        vdk.createNativeQuery("UPDATE vendorCostDetails SET"
                + " minValue = :minValue,"
                + " freeShipping = :freeShipping,"
                + " additionalCost  = :additionalCost,"
                + " flatRate = :flatRate,"
                + " skidDeposit = :skidDeposit,"
                + " modified = :modified,"
                + " modifiedBy = :modifiedBy,"
                + " active = :active"
                + " WHERE id = :id")
                .setParameter("id", m.getId())                
                .setParameter("minValue", m.getMinValue())
                .setParameter("freeShipping", m.isFreeShipping())
                .setParameter("additionalCost", m.getAdditionalCost())
                .setParameter("skidDeposit", m.getSkidDeposit())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .setParameter("active", m.getActive())
                .executeUpdate();
    }
}
