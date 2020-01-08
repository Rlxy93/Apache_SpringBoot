package cn.lixingyu.Apache.entity;

import java.util.Set;

/**
 * @author Rlxy93
 * @time 2020/01/08 16:25
 */
public class Role {
    private String id;
    private String roleName;
    private Set<Permissions> permissionsSet;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", permissionsSet=" + permissionsSet +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Permissions> getPermissionsSet() {
        return permissionsSet;
    }

    public void setPermissionsSet(Set<Permissions> permissionsSet) {
        this.permissionsSet = permissionsSet;
    }

    public Role() {
    }

    public Role(String id, String roleName, Set<Permissions> permissionsSet) {
        this.id = id;
        this.roleName = roleName;
        this.permissionsSet = permissionsSet;
    }
}
