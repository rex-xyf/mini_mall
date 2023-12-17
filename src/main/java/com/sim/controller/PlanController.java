package com.sim.controller;

import com.algorithm.FindLayout;
import com.sim.domain.Bean.BrandBean;
import com.sim.domain.Brand;
import com.sim.domain.Plan;
import com.sim.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanService planService;


    @PutMapping("/find")
    public Result findPlan(@RequestBody Plan form){
        Plan layout = FindLayout.getLayout(form);
        return new Result(Code.GET_OK,layout,"");
    }

    @PutMapping("/getChart1")
    public Result getChart1(@RequestBody Plan plan){
        List<int[]> profit = planService.getProfit(plan);
        System.out.println("1");
        System.out.println(profit);
        return new Result(Code.GET_OK,profit,"");
    }

    @PutMapping("/getChart2")
    public Result getChart2(@RequestBody Plan plan){
        List<BrandBean> type = planService.getType(plan);
        System.out.println("2");
        System.out.println(type);
        return new Result(Code.GET_OK,type,"");
    }

    @PutMapping("/getChart3")
    public Result getChart3(@RequestBody Plan plan){
        List<int[]> scatter = planService.getScatter(plan);
        System.out.println("3");
        System.out.println(scatter);
        return new Result(Code.GET_OK,scatter,"");
    }

    @PutMapping("/getChart4")
    public Result getChart4(@RequestBody Plan plan){
        List<BrandBean> level = planService.getLevel(plan);
        System.out.println("4");
        System.out.println(level);
        return new Result(Code.GET_OK,level,"");
    }



}
