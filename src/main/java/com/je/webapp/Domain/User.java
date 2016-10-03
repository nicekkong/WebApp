/*****************************************************************************
 * PROJECT NAME   : JEWebApp
 * SUBSYSTEM NAME :
 * FILE NAME      : .java
 * DESCRIPTION    :
 * <p/>
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
 * 1.0           Kong,Byungil     2016. 9. 30.     init
 *****************************************************************************/
package com.je.webapp.Domain;

import com.je.webapp.util.DateUtil;

import java.sql.Timestamp;

public class User {

    private String userId;
    private String password;
    private String name;
    private String email;
    private String company;
    private String job;
    private String birthday;
    private Timestamp creDate;
    private Timestamp updDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreDate() {
        return DateUtil.getFormatTime(creDate, "yyyy-MM-dd");
    }

    public void setCreDate(Timestamp creDate) {
        this.creDate = creDate;
    }

    public Timestamp getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Timestamp updDate) {
        this.updDate = updDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                ", birthday='" + birthday + '\'' +
                ", creDate=" + creDate +
                ", updDate=" + updDate +
                '}';
    }
}
