package com.example.qlbhcdio.model;

public class Authentication {
    private static Authentication Auth;
    private User user;
    public static Authentication getInstance() {
        if (Auth == null) {
            Auth = new Authentication();
        }
        return Auth;
    }

    public void setAuth(User user) {
        this.user = user;
    }
    public void setName(String name){
        user.setName(name);
    }
    public  void setAddress(String address){
        user.setAddress(address);
    }
    public  void setPhone(String num){
        user.setNumPhone(num);
    }
    public User getUserCurrent() {
        return user;
    }
}
