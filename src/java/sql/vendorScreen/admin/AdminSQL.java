package sql.vendorScreen.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import util.Numeros;

/**
 *
 * @author CR104978
 */
public class AdminSQL {

    public static void saveRol(Session vdk, DtoRol m) {
        vdk.createNativeQuery("INSERT INTO vendorRol"
                + " (description, created, createdBy, modified, modifiedBy)"
                + " VALUES"
                + " (:description, :created, :createdBy, :modified, :modifiedBy)")
                .setParameter("description", m.getDescription())
                .setParameter("created", m.getCreated())
                .setParameter("createdBy", m.getCreatedBy())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .executeUpdate();
    }

    public static void deleteRolDetails(Session vdk, int idVendorRol) {
        vdk.createNativeQuery("DELETE FROM vendorRolDetail"
                + " WHERE idVendorRol = :idVendorRol")
                .setParameter("idVendorRol", idVendorRol)
                .executeUpdate();
    }

    public static void saveUser(Session vdk, DtoRol m) {
        vdk.createNativeQuery("INSERT INTO masonryUser"
                + " (code, nickName, fullName, email, password, menuAdmin, menuProdAdmin, menuProdComp, created, createdBy, modified, modifiedBy, active, status)"
                + " VALUES"
                + " (:code, :nickName, :fullName, :email, :password, :menuAdmin, :menuProdAdmin, :menuProdComp, :created, :createdBy, :modified, :modifiedBy, :active, :status)")
                .setParameter("code", m.getDescription())
                .setParameter("nickName", m.getCreated())
                .setParameter("createdBy", m.getCreatedBy())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .executeUpdate();
    }

    public static int getConsecutive(Session vdk, String type) {
        int consecutive = 0;
        Iterator itr = vdk.createSQLQuery("SELECT consecutive"
                + " FROM consecutive"
                + " WHERE type = :type")
                .setParameter("type", type)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list().iterator();
        while (itr.hasNext()) {
            consecutive = Numeros.entero(String.valueOf(((Map) itr.next()).get("consecutive")));
        }

        return consecutive;
    }

    public static DtoUser getUser(Session vdk, String code) {
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " code,"
                + " nickName,"
                + " fullName,"
                + " email,"
                + " password,"
                + " menuAdmin,"
                + " menuProdAdmin,"
                + " menuProdComp,"
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy,"
                + " active,"
                + " status"
                + " FROM masonryUser"
                + " WHERE code = :code")
                .setParameter("code", code)
                .setResultTransformer(Transformers.aliasToBean(DtoUser.class))
                .list().iterator();
        DtoUser m = null;
        while (itr.hasNext()) {
            m = (DtoUser) itr.next();
        }
        return m;
    }

    public static ArrayList<DtoUser> getUsers(Session vdk) {
        ArrayList<DtoUser> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " code,"
                + " nickName,"
                + " fullName,"
                + " email,"
                + " password,"
                + " menuAdmin,"
                + " menuProdAdmin,"
                + " menuProdComp,"
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy,"
                + " active,"
                + " status"
                + " FROM vendorUser")
                .setResultTransformer(Transformers.aliasToBean(DtoUser.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoUser) itr.next());
        }
        return a;
    }

    public static ArrayList<DtoRol> getRols(Session vdk) {
        ArrayList<DtoRol> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " description,"
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy"
                + " FROM vendorRol")
                .setResultTransformer(Transformers.aliasToBean(DtoRol.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoRol) itr.next());
        }
        return a;
    }

    public static DtoRol getRol(Session vdk, int id) {
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " description,"
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy"
                + " FROM vendorRol"
                + " WHERE id = :id")
                .setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(DtoRol.class))
                .list().iterator();
        DtoRol m = null;
        while (itr.hasNext()) {
            m = (DtoRol) itr.next();
        }
        return m;
    }

    public static int getLastIdRol(Session vdk, String user) {
        int result = 0;
        Iterator itr = vdk.createSQLQuery("SELECT MAX(id) as result"
                + " FROM vendorRol"
                + " WHERE createdBy = :user")
                .setParameter("user", user)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list().iterator();
        while (itr.hasNext()) {
            result = Numeros.entero(String.valueOf(((Map) itr.next()).get("result")));
        }
        return result;
    }

    public static void saveRolDetail(Session vdk, int idVendorRol, String module, String description, boolean permission, String user, Date ya) {
        if (permission) {
            vdk.createNativeQuery("INSERT INTO vendorRolDetail"
                    + " (idVendorRol, module, description, permission, created, createdBy, modified, modifiedBy)"
                    + " VALUES"
                    + " (:idVendorRol, :module, :description, :permission, :created, :createdBy, :modified, :modifiedBy)")
                    .setParameter("idVendorRol", idVendorRol)
                    .setParameter("module", module)
                    .setParameter("description", description)
                    .setParameter("permission", permission)
                    .setParameter("created", ya)
                    .setParameter("createdBy", user)
                    .setParameter("modified", ya)
                    .setParameter("modifiedBy", user)
                    .executeUpdate();
        }
    }

    public static void updateRol(Session vdk, DtoRol m) {
        vdk.createNativeQuery("UPDATE vendorRol SET"
                + " description = :description,"
                + " modified = :modified,"
                + " modifiedBy = :modifiedBy"
                + " WHERE id = :id")
                .setParameter("id", m.getId())
                .setParameter("description", m.getDescription())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .executeUpdate();
    }
     public static boolean getRolDetail(Session vdk, int idVendorRol, String module, String description) {
        boolean permiso = false;
        Iterator itr = vdk.createSQLQuery("SELECT permission"
                + " FROM vendorRolDetail"
                + " WHERE idVendorRol = :idVendorRol"
                + " AND module = :module"
                + " AND description = :description")
                .setParameter("idVendorRol", idVendorRol)
                .setParameter("module", module)
                .setParameter("description", description).list().iterator();
        while (itr.hasNext()) {
            permiso = (Boolean) itr.next();
        }
        return permiso;
    }
}
