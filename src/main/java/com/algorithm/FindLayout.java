package com.algorithm;

import com.sim.domain.Brand;
import com.sim.domain.Location;
import com.sim.domain.Plan;

import java.util.*;

public class FindLayout {
    
    public static Plan getLayout(Plan plan){
        List<Brand> brands = plan.getBrands();
        double sizeEfficiency = plan.getSizeEfficiency();
        double brandBenefits = plan.getBrandBenefits();
        Location location = plan.getLocation();
        // 贪心算法的实现，商品的利润计算
        Collections.sort(brands, Comparator.comparingDouble((Brand brand) -> calculateSellingPrice(brand, sizeEfficiency, brandBenefits)).reversed());

//        List<Brand> largeBrands = new ArrayList<>();
//        List<Brand> moderateBrands = new ArrayList<>();
//        List<Brand> smallBrands = new ArrayList<>();
        List<Brand> brandList = new ArrayList<>();

        for (Brand brand : brands) {
//            if (largeBrands.size() < 4) {
//                largeBrands.add(brand);
//            } else if (moderateBrands.size() < 8) {
//                moderateBrands.add(brand);
//            } else if (smallBrands.size() < 8){
//                smallBrands.add(brand);
//            }
            if (brandList.size()<20){
                brandList.add(brand);
            }
        }

        List<Brand> layBrands = removeBrands(location, brandList);

        System.out.println(layBrands);

        for (Brand brand : layBrands) {
            if (location.getP3().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP3(brandList1);
                continue;
            } else if (location.getP4().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP4(brandList1);
                continue;
            }else if (location.getP17().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP17(brandList1);
                continue;
            }else if (location.getP18().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP18(brandList1);
                continue;
            }else if (location.getP1().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP1(brandList1);
                continue;
            }else if (location.getP6().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP6(brandList1);
                continue;
            }else if (location.getP15().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP15(brandList1);
                continue;
            }else if (location.getP20().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP20(brandList1);
                continue;
            }else if (location.getP8().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP8(brandList1);
                continue;
            }else if (location.getP9().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP9(brandList1);
                continue;
            }else if (location.getP12().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP12(brandList1);
                continue;
            }else if (location.getP13().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP13(brandList1);
                continue;
            }else if (location.getP2().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP2(brandList1);
                continue;
            }else if (location.getP5().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP5(brandList1);
                continue;
            }else if (location.getP7().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP7(brandList1);
                continue;
            }else if (location.getP10().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP10(brandList1);
                continue;
            }else if (location.getP11().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP11(brandList1);
                continue;
            }else if (location.getP14().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP14(brandList1);
                continue;
            }else if (location.getP16().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP16(brandList1);
                continue;
            }else if (location.getP19().size() == 0){
                List<Brand> brandList1 = new ArrayList<>();
                brandList1.add(brand);
                location.setP19(brandList1);
                continue;
            }
        }

        System.out.println(location);

        plan.setLocation(location);
        return plan;


//        double largeProfit = sellProducts(largeBrands, sizeEfficiency, brandBenefits);
//        double mediumProfit = sellProducts(moderateBrands, sizeEfficiency, brandBenefits);
//        double smallProfit = sellProducts(smallBrands, sizeEfficiency, brandBenefits);
//
//        displaySoldProducts(largeBrands);
//        displaySoldProducts(moderateBrands);
//        displaySoldProducts(smallBrands);
//
//        System.out.println("大商店利润：" + largeProfit);
//        System.out.println("中商店利润：" + mediumProfit);
//        System.out.println("小商店利润：" + smallProfit);
    }

    private static double calculateSellingPrice(Brand Brand, double sizeEfficient, double brandBenefits) {
        double basePrice;
        switch (Brand.getSize()) {
            case "large":
                basePrice = Brand.getConsumption() * (1 + (sizeEfficient / 2));
                break;
            case "moderate":
                basePrice = Brand.getConsumption();
                break;
            case "small":
                basePrice = Brand.getConsumption() * (1 - (sizeEfficient / 2));
                break;
            default:
                basePrice = 0;
        }

        return basePrice * (1.0 - (Brand.getLevel() - 3) * brandBenefits);
    }

    private static double sellProducts(List<Brand> brands, double sizeEfficient, double brandBenefits) {
        double profit = 0;
        for (Brand Brand : brands) {
            profit += calculateSellingPrice(Brand, sizeEfficient, brandBenefits);
        }
        return profit;
    }

    private static void displaySoldProducts(List<Brand> brands) {
        System.out.println("出售的商品列表：");
        for (Brand Brand : brands) {
            System.out.println(Brand.toString());
        }
        System.out.println();
    }


    private static void addNonEmptyToList(List<Brand> allBrands, List<Brand> brandList) {
        if (brandList != null) {
            allBrands.addAll(brandList);
        }
    }

    public static List<Brand> removeBrands(Location location,List<Brand> brands) {
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

//    public static void main(String[] args) {
//        List<Brand> brands = new ArrayList<>();
//
//        // 添加30个商品到列表中
//        brands.add(new Brand(1,"brand1",null,"food",1,"large",133,50,false,true));
//        brands.add(new Brand(2,"brand1",null,"food",2,"moderate",234,50,false,true));
//        brands.add(new Brand(3,"brand1",null,"food",3,"large",324,50,false,true));
//        brands.add(new Brand(4,"brand1",null,"food",4,"large",123,50,false,true));
//        brands.add(new Brand(5,"brand1",null,"food",5,"moderate",800,50,false,true));
//        brands.add(new Brand(6,"brand1",null,"food",1,"large",200,50,false,true));
//        brands.add(new Brand(7,"brand1",null,"food",3,"small",600,50,false,true));
//        brands.add(new Brand(8,"brand1",null,"food",4,"moderate",300,50,false,true));
//        brands.add(new Brand(9,"brand1",null,"food",2,"large",400,50,false,true));
//        brands.add(new Brand(10,"brand1",null,"food",1,"moderate",500,50,false,true));
//        brands.add(new Brand(11,"brand1",null,"food",2,"moderate",555,50,false,true));
//        brands.add(new Brand(12,"brand1",null,"food",1,"large",345,50,false,true));
//        brands.add(new Brand(13,"brand1",null,"food",4,"small",450,50,false,true));
//        brands.add(new Brand(14,"brand1",null,"food",1,"small",654,50,false,true));
//        brands.add(new Brand(15,"brand1",null,"food",5,"small",349,50,false,true));
//        brands.add(new Brand(16,"brand1",null,"food",2,"large",690,50,false,true));
//        brands.add(new Brand(17,"brand1",null,"food",2,"moderate",453,50,false,true));
//        brands.add(new Brand(18,"brand1",null,"food",3,"large",100,50,false,true));
//        brands.add(new Brand(19,"brand1",null,"food",1,"small",345,50,false,true));
//        brands.add(new Brand(20,"brand1",null,"food",3,"large",768,50,false,true));
//        brands.add(new Brand(21,"brand1",null,"food",4,"moderate",198,50,false,true));
//        brands.add(new Brand(22,"brand1",null,"food",5,"small",144,50,false,true));
//        brands.add(new Brand(23,"brand1",null,"food",2,"moderate",567,50,false,true));
//        brands.add(new Brand(24,"brand1",null,"food",1,"moderate",765,50,false,true));
//        brands.add(new Brand(25,"brand1",null,"food",2,"large",375,50,false,true));
//        brands.add(new Brand(26,"brand1",null,"food",1,"small",643,50,false,true));
//
//        List<Brand> brands1 = new ArrayList<>();
//        brands1.add(new Brand(1,"brand1",null,"food",1,"large",133,50,false,true));
//        List<Brand> brands2 = new ArrayList<>();
//        brands2.add(new Brand(2,"brand1",null,"food",2,"moderate",234,50,false,true));
//        Location location = new Location();
//
//        location.setP1(brands1);
//        location.setP2(brands2);
//
//        List<Brand> brands3 = removeBrands(location, brands);
//
//
//        double sizeEfficiency = 0.2;
//        double brandBenefits = 0.1;
//        Plan plan = new Plan(1,"plan1",brands.size(),"Maxvalue",0.2,0.2,brandBenefits,sizeEfficiency,brands,location);
//        getLayout(plan);
//    }
}
