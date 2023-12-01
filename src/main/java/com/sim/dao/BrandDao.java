package com.sim.dao;

import com.sim.domain.Brand;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BrandDao {
    @Select({
            "<script>",
            "SELECT * FROM brand WHERE `delete` = false",
            "<choose>",
            "  <when test='key == \"name\"'>",
            "    AND name LIKE CONCAT('%', #{value}, '%')",
            "  </when>",
            "  <when test='key == \"type\"'>",
            "    AND type LIKE CONCAT('%', #{value}, '%')",
            "  </when>",
            "  <when test='key == \"level\"'>",
            "    AND level LIKE CONCAT('%', #{value}, '%')",
            "  </when>",
            "</choose>",
            "limit #{begin},#{size}",
            "</script>"
    })
    public List<Brand> getAll(@Param("begin") int begin, @Param("size") int size, @Param("key") String key, @Param("value") String value);

    @Select({
            "<script>",
            "SELECT count(*) FROM brand WHERE `delete` = false",
            "<choose>",
            "  <when test='key == \"name\"'>",
            "    AND name LIKE CONCAT('%', #{value}, '%')",
            "  </when>",
            "  <when test='key == \"type\"'>",
            "    AND type LIKE CONCAT('%', #{value}, '%')",
            "  </when>",
            "  <when test='key == \"level\"'>",
            "    AND level LIKE CONCAT('%', #{value}, '%')",
            "  </when>",
            "</choose>",
            "</script>"
    })
    public int getAllCount(@Param("key") String key, @Param("value") String value);

    @Select("select count(*) from brand where `delete` = true")
    public int getDeleteCount();

    @Select("select * from brand where `delete` = true")
    public List<Brand> getDelete();

    @Select("select * from brand where id = #{id}")
    public Brand getById(int id);

    @Insert("insert into brand values (null,#{name},#{img},#{type},#{level},#{delete},#{delect})")
    public void save(Brand Brand);

    @Update("update brand set name = #{name},img= #{img},type= #{type},level= #{level},delete= #{delete},select= #{select} where id = #{id}")
    public void update(Brand Brand);

    @Delete("delete from brand where id = #{id}")
    public void delete(int id);
}
