package com.sim.domain;

import java.util.List;

public class Plan {
    private int id;
    private String name;
    private int count;
    private String strategy;
    private List<Double> competitorInfluence;
    private List<Double> typeComplementarity;
    private List<Double> brandBenefits;
    private List<Double> sizeEfficiency;
    private Location location;

    public Plan() {
    }

    public Plan(int id, String name, int count, String strategy, List<Double> competitorInfluence, List<Double> typeComplementarity, List<Double> brandBenefits, List<Double> sizeEfficiency, Location location) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.strategy = strategy;
        this.competitorInfluence = competitorInfluence;
        this.typeComplementarity = typeComplementarity;
        this.brandBenefits = brandBenefits;
        this.sizeEfficiency = sizeEfficiency;
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

    public List<Double> getCompetitorInfluence() {
        return competitorInfluence;
    }

    public void setCompetitorInfluence(List<Double> competitorInfluence) {
        this.competitorInfluence = competitorInfluence;
    }

    public List<Double> getTypeComplementarity() {
        return typeComplementarity;
    }

    public void setTypeComplementarity(List<Double> typeComplementarity) {
        this.typeComplementarity = typeComplementarity;
    }

    public List<Double> getBrandBenefits() {
        return brandBenefits;
    }

    public void setBrandBenefits(List<Double> brandBenefits) {
        this.brandBenefits = brandBenefits;
    }

    public List<Double> getSizeEfficiency() {
        return sizeEfficiency;
    }

    public void setSizeEfficiency(List<Double> sizeEfficiency) {
        this.sizeEfficiency = sizeEfficiency;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
                ", location=" + location +
                '}';
    }
}

