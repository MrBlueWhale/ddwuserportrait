package com.ibegu.dalaoadmin.req;

public class RoleDistributeReq{
    private Long uid;

    private String roleName;

     public Long getUid() {
         return uid;
     }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        // sb.append(", rid=").append(rid);
        sb.append(", roleName=").append(roleName);
        sb.append(", uid=").append(uid);
        sb.append("]");
        return sb.toString();
    }
}