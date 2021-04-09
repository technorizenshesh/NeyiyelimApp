package com.laapesca.neyiyelimapp.Model;

public class DiscoversModel {

    String name;
    int img;

    public DiscoversModel(String name, int img) {
        this.name = name;
        this.img = img;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
