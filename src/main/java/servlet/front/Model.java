//package com.example;
package servlet.front;

public class Model {

    public static void main(String[] args) {
        System.out.println("test");
    }

    private String login;

    private String password;

    private long count;

    public Model(String login, String password, long count) {
        this.login = login;
        this.password = password;
        this.count = count;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
