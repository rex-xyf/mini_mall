package com.sim.service.impl;

import com.sim.domain.Bean.BrandBean;
import com.sim.domain.Brand;
import com.sim.domain.Plan;
import com.sim.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    public List<int[]> getProfit(Plan plan) {
        int foodProfit = 0;
        int clotheProfit = 0;
        int entertainmentProfit = 0;
        int shopProfit = 0;
        List<Brand> brands = plan.getBrands();
        for (Brand brand : brands) {
            if (brand.getType().equals("food")){
                foodProfit += brand.getConsumption();
            }else if (brand.getType().equals("clothe")){
                clotheProfit += brand.getConsumption();
            }else if (brand.getType().equals("entertainment")){
                entertainmentProfit += brand.getConsumption();
            }else if (brand.getType().equals("shop")){
                shopProfit += brand.getConsumption();
            }
        }
        int totalProfit = foodProfit + clotheProfit + entertainmentProfit +shopProfit;
        int[] ints2 = new int[5];
        ints2[0] = totalProfit;
        ints2[1] = foodProfit;
        ints2[2] = clotheProfit;
        ints2[3] = entertainmentProfit;
        ints2[4] = shopProfit;

        int[] ints1 = new int[5];
        ints1[0] = 0;
        ints1[1] = totalProfit -foodProfit;
        ints1[2] = totalProfit -foodProfit -clotheProfit;
        ints1[3] = totalProfit -foodProfit -clotheProfit -entertainmentProfit;
        ints1[4] = totalProfit -foodProfit -clotheProfit -entertainmentProfit -shopProfit;

        List<int[]> ints = new ArrayList<>(2);
        ints.add(ints1);
        ints.add(ints2);
        return ints;
    }

    @Override
    public List<int[]> getScatter(Plan plan) {
        List<Brand> brands = plan.getBrands();
        List<int[]> ints = new ArrayList<>();
        for (Brand brand:brands){
            int[] i = new int[2];
            i[0] = brand.getDuration();
            i[1] = brand.getConsumption();
            ints.add(i);
        }
        return ints;
    }

    @Override
    public List<BrandBean> getType(Plan plan) {
        List<Brand> brands = plan.getBrands();
        int food = 0;
        int clothe= 0;
        int entertainment = 0;
        int shop = 0;
        for (Brand brand :brands){
            if (brand.getType().equals("food")){
                food += 1;
            }else if (brand.getType().equals("clothe")){
                clothe += 1;
            }else if (brand.getType().equals("entertainment")){
                entertainment += 1;
            }else if (brand.getType().equals("shop")){
                shop +=1;
            }
        }
        List<BrandBean> brandBeans = new ArrayList<>();
        brandBeans.add(new BrandBean(food,"Food"));
        brandBeans.add(new BrandBean(clothe,"Clothe"));
        brandBeans.add(new BrandBean(entertainment,"Entertainment"));
        brandBeans.add(new BrandBean(shop,"Shop"));
        return brandBeans;
    }

    @Override
    public List<BrandBean> getLevel(Plan plan) {
        List<Brand> brands = plan.getBrands();
        int level1 = 0;
        int level2= 0;
        int level3 = 0;
        int level4 = 0;
        int level5 = 0;
        for (Brand brand :brands){
            if (brand.getLevel() == 1){
                level1 += 1;
            }else if (brand.getLevel() == 2){
                level2 += 1;
            }else if (brand.getLevel() == 3){
                level3 += 1;
            }else if (brand.getLevel() == 4){
                level4 += 1;
            }else if (brand.getLevel() == 5){
                level5 += 1;
            }
        }
        List<BrandBean> brandBeans = new ArrayList<>();
        brandBeans.add(new BrandBean(level1,"Level1"));
        brandBeans.add(new BrandBean(level2,"Level2"));
        brandBeans.add(new BrandBean(level3,"Level3"));
        brandBeans.add(new BrandBean(level4,"Level4"));
        brandBeans.add(new BrandBean(level5,"Level5"));
        return brandBeans;
    }
}
