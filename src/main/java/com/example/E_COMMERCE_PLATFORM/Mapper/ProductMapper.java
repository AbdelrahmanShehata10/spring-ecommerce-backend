package com.example.E_COMMERCE_PLATFORM.Mapper;

import com.example.E_COMMERCE_PLATFORM.DTO.ProductDTO;
import com.example.E_COMMERCE_PLATFORM.DTO.Register_DTO;
import com.example.E_COMMERCE_PLATFORM.DTO.UserDto;
import com.example.E_COMMERCE_PLATFORM.Entites.Product;
import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface ProductMapper {


    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    ProductDTO toDto(Product prod);
    Product toEntity(ProductDTO create_product);
    @Mapping(target = "id" , ignore = true)
    void update(ProductDTO UPDATE_REQUEST, @MappingTarget Product p);



}

