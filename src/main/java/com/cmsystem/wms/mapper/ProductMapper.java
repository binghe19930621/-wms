package com.cmsystem.wms.mapper;

import com.cmsystem.wms.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {

    @Select("select * from product where id = #{id}")
    Product findById(@Param("id") Integer id);
}
