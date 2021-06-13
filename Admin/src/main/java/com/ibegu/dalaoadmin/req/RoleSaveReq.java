package com.ibegu.dalaoadmin.req;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RoleSaveReq {
    private Long rid;

    @NotNull(message = "【角色名称】不能为空")
    private String roleName;

    private String desc;

    private Integer active;

    // private Date createTime;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    // public Date getCreateTime() {
    //     return createTime;
    // }

    // public void setCreateTime(Date createTime) {
    //     this.createTime = createTime;
    // }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rid=").append(rid);
        sb.append(", roleName=").append(roleName);
        sb.append(", desc=").append(desc);
        sb.append(", active=").append(active);
        // sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}