package com.sim.controller;

import com.sim.domain.Brand;
import com.sim.service.BrandService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/main")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result getAll(@RequestParam("currentPage")int currentPage, @RequestParam("pageSize")int pageSize,@RequestParam("key")String key,@RequestParam("value")String value){
        List<Brand> brands = brandService.getAll(currentPage,pageSize,key,value);
        int count = brandService.getAllCount(key,value);
        int code = brands != null ? Code.GET_OK : Code.GET_ERR;
        String msg = brands != null ? String.valueOf(count) : "have no data";
        return new Result(code,brands,msg);
    }

    @GetMapping("/delete/getAll")
    public Result getDelete(){
        List<Brand> brands = brandService.getDelete();
        int count = brandService.getDeleteCount();
        int code = brands != null ? Code.GET_OK : Code.GET_ERR;
        String msg = brands != null ? String.valueOf(count) : "have no data";
        return new Result(code,brands,msg);
    }

    @PostMapping("/delete/recover/{id}")
    public Result recover(@PathVariable int id){
        boolean flag = brandService.recover(id);
        return new Result(flag == true ? Code.SAVE_OK : Code.SAVE_ERR , flag);
    }

    @DeleteMapping("/delete/one/{id}")
    public Result deleteById(@PathVariable int id){
        boolean flag = brandService.deleteById(id);
        return new Result(flag == true ? Code.DELETE_OK : Code.DELETE_ERR , flag);
    }

    @DeleteMapping("/delete/many/{ids}")
    public Result deleteByIds(@PathVariable int[] ids){
        boolean flag = brandService.deleteByIds(ids);
        return new Result(flag == true ? Code.DELETE_OK : Code.DELETE_ERR , flag);
    }

    @PostMapping("/select/{id}")
    public Result toggleSelect(@PathVariable int id){
        boolean flag = brandService.toggleSelect(id);
        return new Result(flag == true ? Code.UPDATE_OK : Code.UPDATE_ERR  , flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id){
        Brand brand = brandService.getById(id);
        int code = brand != null ? Code.GET_OK : Code.GET_ERR;
        String msg = brand != null ? "" : "have no data";
        return new Result(code,brand,msg);
    }

    @PostMapping("/upload")
    public Result handleFileUpload(@RequestParam MultipartFile file) throws IOException{
        String newFileName = brandService.upload(file);
        return new Result(Code.SAVE_OK,newFileName);
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

    @GetMapping("/select")
    public Result getBySelect(){
        List<Brand> brands = brandService.getBySelect();
        int code = brands != null ? Code.GET_OK : Code.GET_ERR;
        String msg = brands != null ? "" : "have no data";
        return new Result(code,brands,msg);
    }

}
