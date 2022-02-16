package com.example.catalogservice.mapper;

import com.example.catalogservice.dto.CatalogDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CatalogMapper {

    List<CatalogDto> getAllCatalogs();

}
