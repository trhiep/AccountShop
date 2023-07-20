package entity;

/**
 *
 * @author tranh
 */
public class RoleInformation {
    
    private int roleID;
    private String roleName;

    public RoleInformation() {
    }

    public RoleInformation(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleInformation{" + "roleID=" + roleID + ", roleName=" + roleName + '}';
    }
    
}
