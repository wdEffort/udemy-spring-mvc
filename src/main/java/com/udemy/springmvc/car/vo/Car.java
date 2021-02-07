package com.udemy.springmvc.car.vo;

public class Car {

    private String carName;
    private String userName;
    private String userHp;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHp() {
        return userHp;
    }

    public void setUserHp(String userHp) {
        this.userHp = userHp;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carName='" + carName + '\'' +
                ", userName='" + userName + '\'' +
                ", userHp='" + userHp + '\'' +
                '}';
    }
}
