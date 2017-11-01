/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 11. 2. 오전 2:30
 * Description  : 
 ******************************************************/
package com.je.webapp.Domain;

public class MemberInfo {

    private String group;
    private String name;
    private String game;
    private int score;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                ", game='" + game + '\'' +
                ", score=" + score +
                '}';
    }
}
