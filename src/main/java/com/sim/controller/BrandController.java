package com.sim.controller;

import com.sim.domain.Brand;
import com.sim.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/main")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result getAll(){
        List<Brand> brands = brandService.getAll();
        int code = brands != null ? Code.GET_OK : Code.GET_ERR;
        String msg = brands != null ? "" : "have no data";
        System.out.println("111");
        return new Result(code,brands,msg);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id){
        Brand brand = brandService.getById(id);
        int code = brand != null ? Code.GET_OK : Code.GET_ERR;
        String msg = brand != null ? "" : "have no data";
        return new Result(code,brand,msg);
    }

    @PostMapping
    public Result save(@RequestBody Brand Brand){
        boolean flag = brandService.save(Brand);
        return new Result(flag == true ? Code.SAVE_OK : Code.SAVE_ERR , flag);
    }

    @PutMapping
    public Result update(@RequestBody Brand Brand){
        boolean flag = brandService.update(Brand);
        return new Result(flag == true ? Code.UPDATE_OK : Code.UPDATE_ERR , flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id){
        boolean flag = brandService.delete(id);
        return new Result(flag == true ? Code.DELETE_OK : Code.DELETE_ERR , flag);
    }
}
