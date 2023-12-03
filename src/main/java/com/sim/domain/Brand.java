package com.sim.domain;


import java.util.Arrays;

public class Brand {
    private int id;
    private String name;
    private String img;
    private String type;
    private int level;
    private String size;
    private int consumption;
    private int duration;
    private boolean Delete;
    private boolean Select;

    public Brand() {
    }

    public Brand(int id, String name, String img, String type, int level, String size, int consumption, int duration, boolean delete, boolean select) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
        this.level = level;
        this.size = size;
        this.consumption = consumption;
        this.duration = duration;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", size='" + size + '\'' +
                ", consumption=" + consumption +
                ", duration=" + duration +
                ", Delete=" + Delete +
                ", Select=" + Select +
                '}';
    }
}
