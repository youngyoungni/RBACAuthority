package com.young.atcrowdfunding.bean;

public class Role {

	private String roleId;
	private String name;
	private String createTime;
	private String updateTime;
	private String isDelete;
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", isDelete=" + isDelete + "]";
	}
	
	
}
