package com.sim.dao;

import com.sim.domain.Plan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PlanDao {
    @Select("select * from plan")
    public List<Plan> getAll();

    @Insert("insert into plan values (null,#{name},#{count},#{strategy},#{competitorInfluence},#{typeComplementarity},#{brandBenefits},#{sizeEfficiency},#{brands},#{location})")
    public void save(Plan plan);

    @Delete("delete from plan where id = #{id}")
    public void delete(int id);
}
