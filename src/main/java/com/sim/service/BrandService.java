package com.sim.service;

import com.sim.domain.Brand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BrandService {
    public List<Brand> getAll();

    public Brand getById(int id);

    public boolean save(Brand brand);

    public boolean update(Brand brand);

    public boolean delete(int id);
}
