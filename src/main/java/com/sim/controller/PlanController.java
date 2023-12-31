package com.sim.controller;

import com.sim.domain.Plan;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @GetMapping
    public Result getPlan(){
        //到service层查找
        return new Result(Code.GET_OK,null,"");
    }


    @PutMapping("/find")
    public Result findPlan(@RequestBody Plan form){
        System.out.println(form);
        return new Result(Code.GET_OK,null,"");
    }

    @PostMapping("/save")
    public Result savePlan(@RequestBody Plan form){
        System.out.println(form);
        return new Result(Code.SAVE_OK,null,"");
    }

}
