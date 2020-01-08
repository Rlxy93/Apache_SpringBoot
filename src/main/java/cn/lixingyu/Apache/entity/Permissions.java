package cn.lixingyu.Apache.entity;

/**
 * @author Rlxy93
 * @time 2020/01/08 16:28
 */
public class Permissions {
    private String id;
    private String permissionsName;

    @Override
    public String toString() {
        return "Permissions{" +
                "id='" + id + '\'' +
                ", permissionsName='" + permissionsName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }

    public Permissions() {
    }

    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}
