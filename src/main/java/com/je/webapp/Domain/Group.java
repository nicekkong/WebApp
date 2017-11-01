/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 11. 2. 오전 2:28
 * Description  : 
 ******************************************************/
package com.je.webapp.Domain;

public class Group {

    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group='" + group + '\'' +
                '}';
    }
}
