package com.sim.service;

import com.sim.domain.Bean.BrandBean;
import com.sim.domain.Brand;
import com.sim.domain.Plan;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PlanService {
    public List<int[]> getProfit(Plan plan);

    public List<int[]> getScatter(Plan plan);

    public List<BrandBean> getType(Plan plan);

    public List<BrandBean> getLevel(Plan plan);
}
