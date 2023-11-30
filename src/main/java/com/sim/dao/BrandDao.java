package com.sim.dao;

import com.sim.domain.Brand;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BrandDao {
    @Select({
            "SELECT * FROM brand",
            "WHERE 1 = 1",
            "<if test='key == \"name\"'>",
            "AND name LIKE #{value}",
            "</if>",
            "<if test='key == \"type\"'>",
            "AND type LIKE #{value}",
            "</if>",
            "<if test='key == \"level\"'>",
            "AND level LIKE #{value}",
            "</if>",
            "LIMIT #{begin}, #{size}"
    })
    public List<Brand> getAll(@Param("begin") int begin, @Param("size") int size, @Param("key") String key, @Param("value") String value);

    @Select("select * from brand where id = #{id}")
    public Brand getById(int id);

    @Insert("insert into brand values (null,#{name},#{img},#{type},#{level},#{delete},#{delect})")
    public void save(Brand Brand);

    @Update("update brand set name = #{name},img= #{img},type= #{type},level= #{level},delete= #{delete},select= #{select} where id = #{id}")
    public void update(Brand Brand);

    @Delete("delete from brand where id = #{id}")
    public void delete(int id);
}
