package com.sim.domain;


import java.util.Arrays;

public class Brand {
    private int id;
    private String name;
    private byte[] img;
    private int position;
    private String type;
    private int level;

    public Brand() {
    }

    public Brand(int id, String name, byte[] img, int position, String type, int level) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.position = position;
        this.type = type;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img=" + Arrays.toString(img) +
                ", position=" + position +
                ", type='" + type + '\'' +
                ", level=" + level +
                '}';
    }
}
