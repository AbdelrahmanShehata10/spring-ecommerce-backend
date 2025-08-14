package com.example.E_COMMERCE_PLATFORM.Controller;

import com.example.E_COMMERCE_PLATFORM.DTO.CartItem_DTO;
import com.example.E_COMMERCE_PLATFORM.DTO.ProductDTO;
import com.example.E_COMMERCE_PLATFORM.Entites.CartItem;
import com.example.E_COMMERCE_PLATFORM.Entites.Product;
import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import com.example.E_COMMERCE_PLATFORM.Mapper.CartItemMapper;
import com.example.E_COMMERCE_PLATFORM.REPO.CartItemRepository;
import com.example.E_COMMERCE_PLATFORM.REPO.Product_repo;
import com.example.E_COMMERCE_PLATFORM.REPO.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final UsersRepository urepo;
private final Product_repo prepo;
private final CartItemMapper cartItemMapper;
private final CartItemRepository cartItemRepository;
    @PostMapping("/add")

    public ResponseEntity<CartItem_DTO> addtocart(@RequestBody CartItem_DTO addingrequest, Authentication authentication){

        String username=authentication.getName();
        Users user=  urepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Product product= prepo.findById(addingrequest.getProductId()).orElseThrow(() -> new RuntimeException("product not found"));

        CartItem cartItems = cartItemMapper.toEntity(addingrequest);
        cartItems.setUser(user);
        cartItems.setProduct(product);
        cartItemRepository.save(cartItems);
        return ResponseEntity.ok(cartItemMapper.to_DTO(cartItems));

    }

    @GetMapping("/getAll")

    public ResponseEntity<List<CartItem_DTO>> getallcartitems(Authentication authentication){

        String username=authentication.getName();
        Users user=  urepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<CartItem_DTO> cartItems = cartItemRepository.findByUser(user)
                .stream()
                .map(cartItem -> cartItemMapper.to_DTO(cartItem))
                .toList();

        return ResponseEntity.ok(cartItems);    }


    @PutMapping("/{id}")
    public ResponseEntity<CartItem_DTO> updatecartitems(@PathVariable (name = "id") int id,
                                                    @RequestBody  CartItem_DTO update,Authentication authentication
    ){
        String username=authentication.getName();
        Users user=  urepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var cartitem = cartItemRepository.findByIdAndUser(id,user).orElse(null);

        if (cartitem == null) {
            System.out.println("bl77777");
            return ResponseEntity.notFound().build();

        } else {
            cartItemMapper.update(update,cartitem);
            cartItemRepository.save(cartitem);
            return ResponseEntity.ok(cartItemMapper.to_DTO(cartitem));

        }

    }
}
