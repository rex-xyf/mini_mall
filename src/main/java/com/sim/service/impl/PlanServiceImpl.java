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
    public List<double[]> getProfit(Plan plan) {
        double foodProfit = 0;
        double clotheProfit = 0;
        double entertainmentProfit = 0;
        double shopProfit = 0;
        double i = 1;
        double j = plan.getBrandBenefits();
        List<Brand> brands = plan.getBrands();
        for (Brand brand : brands) {
            if (brand.getSize().equals("large")){
                i = 1+ (plan.getSizeEfficiency()/2);
            }else if (brand.getSize().equals("small")){
                i = 1-(plan.getSizeEfficiency()/2);
            }
            switch (brand.getType()) {
                case "food":
                    foodProfit += brand.getConsumption()*i*(1.0 - (brand.getLevel() - 3) * j);
                    break;
                case "clothe":
                    clotheProfit += brand.getConsumption()*i*(1.0 - (brand.getLevel() - 3) * j);
                    break;
                case "entertainment":
                    entertainmentProfit += brand.getConsumption()*i*(1.0 - (brand.getLevel() - 3) * j);
                    break;
                case "shop":
                    shopProfit += brand.getConsumption()*i*(1.0 - (brand.getLevel() - 3) * j);
                    break;
            }
        }
        double totalProfit = foodProfit + clotheProfit + entertainmentProfit +shopProfit;
        double[] doubles2 = new double[5];
        doubles2[0] = totalProfit;
        doubles2[1] = foodProfit;
        doubles2[2] = clotheProfit;
        doubles2[3] = entertainmentProfit;
        doubles2[4] = shopProfit;

        double[] doubles1 = new double[5];
        doubles1[0] = 0;
        doubles1[1] = totalProfit -foodProfit;
        doubles1[2] = totalProfit -foodProfit -clotheProfit;
        doubles1[3] = totalProfit -foodProfit -clotheProfit -entertainmentProfit;
        doubles1[4] = totalProfit -foodProfit -clotheProfit -entertainmentProfit -shopProfit;

        List<double[]> doubles = new ArrayList<>(2);
        doubles.add(doubles1);
        doubles.add(doubles2);
        return doubles;
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
