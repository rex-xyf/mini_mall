package com.sim.dao;

import com.sim.domain.Brand;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BrandDao {
    //分页查询
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

    //查询表中数据数量
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

    //查询回收站数量
    @Select("select count(*) from brand where `delete` = true")
    public int getDeleteCount();

    //查询回收站数据
    @Select("select * from brand where `delete` = true")
    public List<Brand> getDelete();

    //按id查询
    @Select("select * from brand where id = #{id}")
    public Brand getById(int id);

    //增加add
    @Insert("insert into brand values (null,#{name},#{img},#{type},#{level},#{delete},#{delect})")
    public void save(Brand Brand);

    //修改update
    @Update("update brand set name = #{name},img= #{img},type= #{type},level= #{level},`delete`= #{delete},`select`= #{select} where id = #{id}")
    public void update(Brand Brand);

    //回收站恢复
    @Update("update brand set `delete` = false where id = #{id}")
    public void recover(int id);

    //批量删除
    @Update({
            "<script>",
            "UPDATE brand",
            "SET `delete` = true ",
            "WHERE id IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "   #{id}",
            "</foreach>",
            "</script>"
    })
    public void deleteByIds(@Param("ids") int[] ids);

    //更改选择状态
    @Update("UPDATE brand SET `select` = NOT `select` WHERE id = #{id}")
    public void toggleSelect(int id);

    //删除到回收站
    @Update("UPDATE brand SET `delete` = true WHERE id = #{id}")
    public void deleteById(int id);

    //回收站彻底删除
    @Delete("delete from brand where id = #{id}")
    public void delete(int id);
}
