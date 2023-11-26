package com.sim.service.impl;

import com.sim.dao.BrandDao;
import com.sim.domain.Brand;
import com.sim.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> getAll() {
        return brandDao.getAll();
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
}
