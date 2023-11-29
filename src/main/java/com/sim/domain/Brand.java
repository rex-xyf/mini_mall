package com.sim.domain;


import java.util.Arrays;

public class Brand {
    private int id;
    private String name;
    private String img;
    private String type;
    private int level;
    private boolean Delete;
    private boolean Select;

    public Brand() {
    }

    public Brand(int id, String name, String img, String type, int level, Boolean delete, Boolean select) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
        this.level = level;
        Delete = delete;
        Select = select;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public boolean isDelete() {
        return Delete;
    }

    public void setDelete(boolean delete) {
        Delete = delete;
    }

    public boolean isSelect() {
        return Select;
    }

    public void setSelect(boolean select) {
        Select = select;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", Delete=" + Delete +
                ", Select=" + Select +
                '}';
    }
}
