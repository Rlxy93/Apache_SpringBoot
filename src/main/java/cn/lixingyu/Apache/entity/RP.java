package cn.lixingyu.Apache.entity;

/**
 * @author Lxxxxxxy
 * @time 2020/01/08 17:31
 */
public class RP {
    private String roleName;
    private String permissionsName;

    @Override
    public String toString() {
        return "RP{" +
                "roleName=" + roleName +
                ", permissionsName=" + permissionsName +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }

    public RP(String roleName, String permissionsName) {
        this.roleName = roleName;
        this.permissionsName = permissionsName;
    }

    public RP() {
    }

    public void init(String roleName,String permissionsName){
        this.roleName = roleName;
        this.permissionsName = permissionsName;
    }
}
