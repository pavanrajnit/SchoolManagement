package org.school.tool.management.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="PermissionTable")
public class PermissionTableModel {

	@Id
	private int id;
	private String roleType;
	private boolean canRegister;
	private boolean canPayFees;
	private boolean canViewStudent;
	private boolean canEditStudent;
	private boolean canViewTimeTable;
	private boolean canEditTimeTable;
	private boolean canViewTeacher;
	private boolean canEditTeacher;
	private boolean canViewNonTeachingStaff;
	private boolean canEditNonTeachingStaff;
	private boolean canViewHolidayList;
	private boolean canEditHolidayList;
	private boolean canAccessAttendanceReport;
	private boolean canAccessAcademicReport;
	private boolean canAccessHistory;
	private boolean canAccessOrganizationalSettings;
	private boolean canAccessUserSettings;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public boolean isCanRegister() {
		return canRegister;
	}
	public void setCanRegister(boolean canRegister) {
		this.canRegister = canRegister;
	}
	public boolean isCanPayFees() {
		return canPayFees;
	}
	public void setCanPayFees(boolean canPayFees) {
		this.canPayFees = canPayFees;
	}
	public boolean isCanViewStudent() {
		return canViewStudent;
	}
	public void setCanViewStudent(boolean canViewStudent) {
		this.canViewStudent = canViewStudent;
	}
	public boolean isCanEditStudent() {
		return canEditStudent;
	}
	public void setCanEditStudent(boolean canEditStudent) {
		this.canEditStudent = canEditStudent;
	}
	public boolean isCanViewTimeTable() {
		return canViewTimeTable;
	}
	public void setCanViewTimeTable(boolean canViewTimeTable) {
		this.canViewTimeTable = canViewTimeTable;
	}
	public boolean isCanEditTimeTable() {
		return canEditTimeTable;
	}
	public void setCanEditTimeTable(boolean canEditTimeTable) {
		this.canEditTimeTable = canEditTimeTable;
	}
	public boolean isCanViewTeacher() {
		return canViewTeacher;
	}
	public void setCanViewTeacher(boolean canViewTeacher) {
		this.canViewTeacher = canViewTeacher;
	}
	public boolean isCanEditTeacher() {
		return canEditTeacher;
	}
	public void setCanEditTeacher(boolean canEditTeacher) {
		this.canEditTeacher = canEditTeacher;
	}
	public boolean isCanViewNonTeachingStaff() {
		return canViewNonTeachingStaff;
	}
	public void setCanViewNonTeachingStaff(boolean canViewNonTeachingStaff) {
		this.canViewNonTeachingStaff = canViewNonTeachingStaff;
	}
	public boolean isCanEditNonTeachingStaff() {
		return canEditNonTeachingStaff;
	}
	public void setCanEditNonTeachinfStaff(boolean canEditNonTeachingStaff) {
		this.canEditNonTeachingStaff = canEditNonTeachingStaff;
	}
	public boolean isCanViewHolidayList() {
		return canViewHolidayList;
	}
	public void setCanViewHolidayList(boolean canViewHolidayList) {
		this.canViewHolidayList = canViewHolidayList;
	}
	public boolean isCanEditHolidayList() {
		return canEditHolidayList;
	}
	public void setCanEditHolidayList(boolean canEditHolidayList) {
		this.canEditHolidayList = canEditHolidayList;
	}
	public boolean isCanAccessAttendanceReport() {
		return canAccessAttendanceReport;
	}
	public void setCanAccessAttendanceReport(boolean canAccessAttendanceReport) {
		this.canAccessAttendanceReport = canAccessAttendanceReport;
	}
	public boolean isCanAccessAcademicReport() {
		return canAccessAcademicReport;
	}
	public void setCanAccessAcademicReport(boolean canAccessAcademicReport) {
		this.canAccessAcademicReport = canAccessAcademicReport;
	}
	public boolean isCanAccessHistory() {
		return canAccessHistory;
	}
	public void setCanAccessHistory(boolean canAccessHistory) {
		this.canAccessHistory = canAccessHistory;
	}
	public boolean isCanAccessOrganizationalSettings() {
		return canAccessOrganizationalSettings;
	}
	public void setCanAccessOrganizationalSettings(boolean canAccessOrganizationalSettings) {
		this.canAccessOrganizationalSettings = canAccessOrganizationalSettings;
	}
	public boolean isCanAccessUserSettings() {
		return canAccessUserSettings;
	}
	public void setCanAccessUserSettings(boolean canAccessUserSettings) {
		this.canAccessUserSettings = canAccessUserSettings;
	}
	
	
}
