package com.sim.service.impl;

import com.sim.dao.BrandDao;
import com.sim.domain.Brand;
import com.sim.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> getAll(int currentPage, int pageSize,String key,String value) {
        int begin = (currentPage-1)*pageSize;
        int size = pageSize;
        return brandDao.getAll(begin,size,key,value);
    }

    @Override
    public List<Brand> getDelete() {
        return brandDao.getDelete();
    }

    @Override
    public int getAllCount(String key, String value) {
        return brandDao.getAllCount(key,value);
    }

    @Override
    public int getDeleteCount() {
        return brandDao.getDeleteCount();
    }

    @Override
    public Brand getById(int id) {
        return brandDao.getById(id);
    }

    @Override
    public boolean save(Brand brand) {
        brandDao.save(brand);
        return true;
    }

    @Override
    public boolean update(Brand brand) {
        brandDao.update(brand);
        return true;
    }

    @Override
    public boolean delete(int id) {
        brandDao.delete(id);
        return true;
    }

    @Override
    public boolean deleteByIds(int[] ids) {
        brandDao.deleteByIds(ids);
        return true;
    }

    @Override
    public boolean recover(int id) {
        brandDao.recover(id);
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        brandDao.deleteById(id);
        return true;
    }

    @Override
    public boolean toggleSelect(int id) {
        brandDao.toggleSelect(id);
        return true;
    }

    @Override
    public String upload(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        // 生成新的文件名（以时间戳命名，确保不重复）
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String newFileName = timestamp + "_" + originalFileName;
        // 保存文件到指定目录
        String uploadDir = "D:/project/mini_mall/src/main/webapp/img";  // 修改为你的目标目录
        File dest = new File(uploadDir, newFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    @Override
    public List<Brand> getBySelect() {
        return brandDao.getBySelect();
    }
}
