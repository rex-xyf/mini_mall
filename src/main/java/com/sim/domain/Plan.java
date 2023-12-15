package com.sim.domain;

import java.util.List;

public class Plan {
    private int id;
    private String name;
    private int count;
    private String strategy;
    private double competitorInfluence;
    private double typeComplementarity;
    private double brandBenefits;
    private double sizeEfficiency;
    private List<Brand> brands;
    private Location location;

    public Plan() {
    }

    public Plan(int id, String name, int count, String strategy, double competitorInfluence, double typeComplementarity, double brandBenefits, double sizeEfficiency, List<Brand> brands, Location location) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.strategy = strategy;
        this.competitorInfluence = competitorInfluence;
        this.typeComplementarity = typeComplementarity;
        this.brandBenefits = brandBenefits;
        this.sizeEfficiency = sizeEfficiency;
        this.brands = brands;
        this.location = location;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public double getCompetitorInfluence() {
        return competitorInfluence;
    }

    public void setCompetitorInfluence(double competitorInfluence) {
        this.competitorInfluence = competitorInfluence;
    }

    public double getTypeComplementarity() {
        return typeComplementarity;
    }

    public void setTypeComplementarity(double typeComplementarity) {
        this.typeComplementarity = typeComplementarity;
    }

    public double getBrandBenefits() {
        return brandBenefits;
    }

    public void setBrandBenefits(double brandBenefits) {
        this.brandBenefits = brandBenefits;
    }

    public double getSizeEfficiency() {
        return sizeEfficiency;
    }

    public void setSizeEfficiency(double sizeEfficiency) {
        this.sizeEfficiency = sizeEfficiency;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", strategy='" + strategy + '\'' +
                ", competitorInfluence=" + competitorInfluence +
                ", typeComplementarity=" + typeComplementarity +
                ", brandBenefits=" + brandBenefits +
                ", sizeEfficiency=" + sizeEfficiency +
                ", brands=" + brands +
                ", location=" + location +
                '}';
    }
}

