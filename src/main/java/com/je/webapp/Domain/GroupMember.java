/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 11. 2. 오전 2:29
 * Description  : 
 ******************************************************/
package com.je.webapp.Domain;

public class GroupMember {

    private String groupName;
    private String member;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "groupName='" + groupName + '\'' +
                ", member='" + member + '\'' +
                '}';
    }
}
