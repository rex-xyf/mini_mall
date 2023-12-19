package com.algorithm;

import com.sim.domain.Brand;
import com.sim.domain.Location;
import com.sim.domain.Plan;

import java.util.*;

public class FindBalanceLayout {
    private static List<Brand> largeBrands = new ArrayList<>();
    private static List<Brand> mediumBrands = new ArrayList<>();
    private static List<Brand> smallBrands = new ArrayList<>();
    private static double totalProfit = 0;

    public static Plan getBalance(Plan plan) {
        List<Brand> brands = plan.getBrands();
        Location location = plan.getLocation();
        // 倍率
        double fixedSizeEfficient =plan.getSizeEfficiency();
        double brandBenefits = plan.getBrandBenefits();

        // 根据商品价值降序排序
        Collections.sort(brands, Comparator.comparingInt(Brand::getConsumption).reversed());

        totalProfit = 0;
        largeBrands.clear();
        mediumBrands.clear();
        smallBrands.clear();

        // 尽可能平衡各商店利润
        distributeBrands(brands, fixedSizeEfficient, brandBenefits);

        List<Brand> brandList = new ArrayList<>();
        brandList.addAll(largeBrands);
        brandList.addAll(mediumBrands);
        brandList.addAll(smallBrands);

        List<Brand> layBrands = removeBrands(location, brandList);

        for (Brand brand : layBrands) {
            if (location.getP4().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP4(brandList1);
                continue;
            } else if (location.getP3().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP3(brandList1);
                continue;
            }else if (location.getP18().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP18(brandList1);
                continue;
            }else if (location.getP17().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP17(brandList1);
                continue;
            }else if (location.getP15().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP15(brandList1);
                continue;
            }else if (location.getP6().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP6(brandList1);
                continue;
            }else if (location.getP1().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP1(brandList1);
                continue;
            }else if (location.getP20().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP20(brandList1);
                continue;
            }else if (location.getP9().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP9(brandList1);
                continue;
            }else if (location.getP13().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP13(brandList1);
                continue;
            }else if (location.getP12().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP12(brandList1);
                continue;
            }else if (location.getP8().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP8(brandList1);
                continue;
            }else if (location.getP2().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP2(brandList1);
                continue;
            }else if (location.getP10().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP10(brandList1);
                continue;
            }else if (location.getP7().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP7(brandList1);
                continue;
            }else if (location.getP5().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP5(brandList1);
                continue;
            }else if (location.getP11().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP11(brandList1);
                continue;
            }else if (location.getP16().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP16(brandList1);
                continue;
            }else if (location.getP14().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP14(brandList1);
                continue;
            }else if (location.getP19().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP19(brandList1);
                continue;
            }
        }

        plan.setLocation(location);
        return plan;
    }

    private static void distributeBrands(List<Brand> brands, double sizeEfficient, double brandBenefits) {
        int largeCapacity = 4;
        int mediumCapacity = 8;
        int smallCapacity = 8;

        // 用于记录各商店的当前利润
        double largeProfit = 0;
        double mediumProfit = 0;
        double smallProfit = 0;

        for (Brand brand : brands) {
            double largeProfitWithBrand = calculateSellingPrice(brand, sizeEfficient, brandBenefits, "大");
            double mediumProfitWithBrand = calculateSellingPrice(brand, sizeEfficient, brandBenefits, "中");
            double smallProfitWithBrand = calculateSellingPrice(brand, sizeEfficient, brandBenefits, "小");

            // 计算将商品放入各商店后的利润差值
            double largeDiff = largeProfit + largeProfitWithBrand - Math.min(mediumProfit, smallProfit);
            double mediumDiff = mediumProfit + mediumProfitWithBrand - Math.min(largeProfit, smallProfit);
            double smallDiff = smallProfit + smallProfitWithBrand - Math.min(largeProfit, mediumProfit);

            // 找到差值最小的商店，将商品放入
            if (largeDiff <= mediumDiff && largeDiff <= smallDiff && largeBrands.size() < largeCapacity) {
                largeBrands.add(brand);
                largeProfit += largeProfitWithBrand;
            } else if (mediumDiff <= largeDiff && mediumDiff <= smallDiff && mediumBrands.size() < mediumCapacity) {
                mediumBrands.add(brand);
                mediumProfit += mediumProfitWithBrand;
            } else if (smallDiff <= largeDiff && smallDiff <= mediumDiff && smallBrands.size() < smallCapacity) {
                smallBrands.add(brand);
                smallProfit += smallProfitWithBrand;
            }

            totalProfit += Math.min(Math.min(largeProfitWithBrand, mediumProfitWithBrand), smallProfitWithBrand);
        }
    }

    private static double calculateSellingPrice(Brand brand, double sizeEfficient, double brandBenefits, String shopSize) {
        double basePrice;
        switch (brand.getSize()) {
            case "large":
                basePrice = brand.getConsumption() * (1 + (sizeEfficient / 2));
                break;
            case "moderate":
                basePrice = brand.getConsumption();
                break;
            case "small":
                basePrice = brand.getConsumption() * (1 - (sizeEfficient / 2));
                break;
            default:
                basePrice = 0;
        }

        double profit = basePrice * (1.0 - (brand.getLevel() - 3) * brandBenefits);

        switch (shopSize) {
            case "large":
                return profit * (1 + (sizeEfficient / 2));
            case "moderate":
                return profit;
            case "small":
                return profit * (1 - (sizeEfficient / 2));
            default:
                return 0;
        }
    }

    private static void addNonEmptyToList(List<Brand> allBrands, List<Brand> brandList) {
        if (brandList != null) {
            allBrands.addAll(brandList);
        }
    }

    public static List<Brand> removeBrands(Location location, List<Brand> brands) {
        List<Brand> allBrands = new ArrayList<>();

        addNonEmptyToList(allBrands, location.getP1());
        addNonEmptyToList(allBrands, location.getP2());
        addNonEmptyToList(allBrands, location.getP3());
        addNonEmptyToList(allBrands, location.getP4());
        addNonEmptyToList(allBrands, location.getP5());
        addNonEmptyToList(allBrands, location.getP6());
        addNonEmptyToList(allBrands, location.getP7());
        addNonEmptyToList(allBrands, location.getP8());
        addNonEmptyToList(allBrands, location.getP9());
        addNonEmptyToList(allBrands, location.getP10());
        addNonEmptyToList(allBrands, location.getP11());
        addNonEmptyToList(allBrands, location.getP12());
        addNonEmptyToList(allBrands, location.getP13());
        addNonEmptyToList(allBrands, location.getP14());
        addNonEmptyToList(allBrands, location.getP15());
        addNonEmptyToList(allBrands, location.getP16());
        addNonEmptyToList(allBrands, location.getP17());
        addNonEmptyToList(allBrands, location.getP18());
        addNonEmptyToList(allBrands, location.getP19());
        addNonEmptyToList(allBrands, location.getP20());

        Iterator<Brand> iterator1 = brands.iterator();

        while (iterator1.hasNext()) {
            Brand brand1 = iterator1.next();
            for (Brand brand2 : allBrands) {
                if (brand1.getId() == brand2.getId()) {
                    iterator1.remove();
                }
            }
        }
        return brands;
    }
}
