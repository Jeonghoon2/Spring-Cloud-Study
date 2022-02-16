package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.mapper.CatalogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    CatalogMapper catalogMapper;

    @Autowired
    public CatalogService(CatalogMapper catalogMapper) {
        this.catalogMapper = catalogMapper;
    }

    public Iterable<CatalogDto> getAllCatalogs(){
        return catalogMapper.getAllCatalogs();
    }

}
