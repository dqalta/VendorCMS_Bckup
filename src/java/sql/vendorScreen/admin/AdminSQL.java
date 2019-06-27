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
                + " (description, created, createdBy, modified, modifiedBy,idVendor)"
                + " VALUES"
                + " (:description, :created, :createdBy, :modified, :modifiedBy, :idVendor)")
                .setParameter("description", m.getDescription())
                .setParameter("created", m.getCreated())
                .setParameter("createdBy", m.getCreatedBy())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .setParameter("idVendor", m.getModifiedBy())
                .executeUpdate();
    }

    public static void saveVendorUserRol(Session vdk, String code, int id, String idVendor) {
        vdk.createNativeQuery("INSERT INTO vendorUserRol"
                + " (idVendorRol, codeVendorUser, idVendor)"
                + " VALUES"
                + " (:idVendorRol, :codeVendorUser, :idVendor)")
                .setParameter("idVendorRol", id)
                .setParameter("codeVendorUser", code)
                   .setParameter("idVendor", idVendor)
                .executeUpdate();
    }

    public static void deleteRolDetails(Session vdk, int idVendorRol) {
        vdk.createNativeQuery("DELETE FROM vendorRolDetail"
                + " WHERE idVendorRol = :idVendorRol")
                .setParameter("idVendorRol", idVendorRol)
                .executeUpdate();
    }

    public static void deleteUserRols(Session vdk, String code) {
        vdk.createNativeQuery("DELETE FROM vendorUserRol"
                + " WHERE codeVendorUser = :codeVendorUser")
                .setParameter("codeVendorUser", code)
                .executeUpdate();
    }

    public static void saveVendorUser(Session vdk, DtoVendorUser m) {
        vdk.createNativeQuery("INSERT INTO vendorUser"
                + " (codeVendorUser, nickName, fullName, email, passwordVendorUser, idVendor, created, createdBy, modified, modifiedBy, active, statusVendorUser)"
                + " VALUES"
                + " (:codeVendorUser, :nickName, :fullName, :email, :passwordVendorUser, :idVendor, :created, :createdBy, :modified, :modifiedBy, :active, :statusVendorUser)")
                .setParameter("codeVendorUser", m.getCodeVendorUser())
                .setParameter("nickName", m.getNickName())
                .setParameter("fullName", m.getFullName())
                .setParameter("email", m.getEmail())
                .setParameter("passwordVendorUser", m.getPasswordVendorUser())
                .setParameter("idVendor", m.getIdVendor())
                .setParameter("created", m.getCreated())
                .setParameter("createdBy", m.getCreatedBy())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .setParameter("active", m.isActive())
                .setParameter("statusVendorUser", m.getStatusVendorUser())
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

    public static DtoVendorUser getUser(Session vdk, String code) {
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " codeVendorUser,"
                + " nickName,"
                + " fullName,"
                + " email,"
                + " passwordVendorUser,"
                + " idVendor,"
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy,"
                + " active,"
                + " statusVendorUser"
                + " FROM vendorUser"
                + " WHERE codeVendorUser = :codeVendorUser")
                .setParameter("codeVendorUser", code)
                .setResultTransformer(Transformers.aliasToBean(DtoVendorUser.class))
                .list().iterator();
        DtoVendorUser m = null;
        while (itr.hasNext()) {
            m = (DtoVendorUser) itr.next();
        }
        return m;
    }

    public static ArrayList<DtoVendorUser> getUsers(Session vdk) {
        ArrayList<DtoVendorUser> a = new ArrayList<>();
        Iterator itr = vdk.createNativeQuery("SELECT"
                + " id,"
                + " codeVendorUser,"
                + " nickName,"
                + " fullName,"
                + " email,"
                + " passwordVendorUser,"
                + " idVendor,"
                + " created,"
                + " createdBy,"
                + " modified,"
                + " modifiedBy,"
                + " active,"
                + " statusVendorUser"
                + " FROM vendorUser")
                .setResultTransformer(Transformers.aliasToBean(DtoVendorUser.class))
                .list().iterator();

        while (itr.hasNext()) {
            a.add((DtoVendorUser) itr.next());
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
                + " idVendor"
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
                + " idVendor"
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

    public static void saveRolDetail(Session vdk, int idVendorRol, String module, String description, boolean permission, String user, Date ya, String idVendor) {
        if (permission) {
            vdk.createNativeQuery("INSERT INTO vendorRolDetail"
                    + " (idVendorRol, module, description, permission, created, createdBy, modified, modifiedBy, idVendor)"
                    + " VALUES"
                    + " (:idVendorRol, :module, :description, :permission, :created, :createdBy, :modified, :modifiedBy, :idVendor)")
                    .setParameter("idVendorRol", idVendorRol)
                    .setParameter("module", module)
                    .setParameter("description", description)
                    .setParameter("permission", permission)
                    .setParameter("created", ya)
                    .setParameter("createdBy", user)
                    .setParameter("modified", ya)
                    .setParameter("modifiedBy", user)
                    .setParameter("idVendor", idVendor)
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

    public static void updateUser(Session vdk, DtoVendorUser m) {
        vdk.createNativeQuery("UPDATE vendorUser SET"
                + " codeVendorUser = :codeVendorUser,"
                + " nickName = :nickName,"
                + " fullName = :fullName,"
                + " email = :email,"
                + " passwordVendorUser = :passwordVendorUser,"
                + " idVendor = :idVendor,"
//                + " menuAdmin = :menuAdmin,"
//                + " menuProdAdmin = :menuProdAdmin,"
                + " modified = :modified,"
                + " modifiedBy = :modifiedBy"
                + " active = :active"
                + " statusVendorUser = :statusVendorUser"
                + " WHERE id = :id")
                .setParameter("codeVendorUser", m.getCodeVendorUser())
                .setParameter("nickName", m.getNickName())
                .setParameter("fullName", m.getFullName())
                .setParameter("email", m.getEmail())
                .setParameter("passwordVendorUser", m.getPasswordVendorUser())
                .setParameter("idVendor", m.getIdVendor())
//                .setParameter("menuAdmin", m.isMenuAdmin())
//                .setParameter("menuProdAdmin", m.isMenuProdAdmin())
                .setParameter("modified", m.getModified())
                .setParameter("modifiedBy", m.getModifiedBy())
                .setParameter("active", m.isActive())
                .setParameter("statusVendorUser", m.getStatusVendorUser())
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

        public static String getVendorUserRols(Session vdk, String code, String idVendorRole) {
        String resultado = "";
        Iterator itr = vdk.createSQLQuery("SELECT id"
                + " FROM vendorUserRol"
                + " WHERE codeVendorUser = :codeVendorUser"
                + " AND idVendor = :idVendor")
                .setParameter("codeVendorUser", code)
                .setParameter("idVendor", idVendorRole)
                .list().iterator();

        while (itr.hasNext()) {
            resultado = resultado.concat("," + (int) itr.next());
        }
        return resultado;
    }
}
