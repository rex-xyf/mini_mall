package com.sim.dao;

import com.sim.domain.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrandDao {
    @Select("select * from brand")
    public List<Brand> getAll();

    @Select("select * from brand where id = #{id}")
    public Brand getById(int id);

    @Insert("insert into brand values (null,#{name},#{img},#{position},#{type},#{level})")
    public void save(Brand Brand);

    @Update("update brand set name = #{name},img= #{img},position= #{position},type= #{type},level= #{level} where id = #{id}")
    public void update(Brand Brand);

    @Delete("delete from brand where id = #{id}")
    public void delete(int id);
}
