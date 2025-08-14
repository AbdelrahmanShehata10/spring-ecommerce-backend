package com.example.E_COMMERCE_PLATFORM.Mapper;

import com.example.E_COMMERCE_PLATFORM.DTO.CartItem_DTO;
import com.example.E_COMMERCE_PLATFORM.Entites.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface CartItemMapper {
//    @Mapping(source = "product.id", target = "productId")
    CartItem_DTO to_DTO(CartItem cartItems);

    @Mapping(target = "product", ignore = true) // ستُعين لاحقًا في السيرفيس
    @Mapping(target = "user", ignore = true)
    CartItem toEntity(CartItem_DTO cartItemDTO);
    @Mapping(target = "id" , ignore = true)
    void update(CartItem_DTO cartItemDTO, @MappingTarget CartItem cartItem);

}
