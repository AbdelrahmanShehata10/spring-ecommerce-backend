package com.example.E_COMMERCE_PLATFORM.Controller;

import com.example.E_COMMERCE_PLATFORM.DTO.ProductDTO;
import com.example.E_COMMERCE_PLATFORM.DTO.UserDto;
import com.example.E_COMMERCE_PLATFORM.Entites.Product;
import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import com.example.E_COMMERCE_PLATFORM.Mapper.ProductMapper;
import com.example.E_COMMERCE_PLATFORM.REPO.Product_repo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private Product_repo repo;
    @Autowired
    private ProductMapper productMapper;
    @GetMapping("/prod")

public List<ProductDTO> getallproducts(){
     return repo.findAll().stream().map(product -> productMapper.toDto(product)).toList();
}

@PostMapping("/create_product")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<ProductDTO> createproduct(@RequestBody ProductDTO create_request){
var product= productMapper.toEntity(create_request);
repo.save(product);

return  ResponseEntity.ok(productMapper.toDto(product));
}

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> updateproduct(@PathVariable (name = "id") int id,
                                                    @RequestBody  ProductDTO update
    ){
        var product = repo.findById(id).orElse(null);

        if (product == null) {
            System.out.println("bl77777");
            return ResponseEntity.notFound().build();

        } else {
            productMapper.update(update,product);
            repo.save(product);
            return ResponseEntity.ok(productMapper.toDto(product));

        }

    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteproduct(@PathVariable (name = "id") int id)
    {
        var product = repo.findById(id).orElse(null);

        if (product == null) {
            System.out.println("bl77777");
            return ResponseEntity.notFound().build();

        }else{

            repo.delete(product);
            return ResponseEntity.noContent().build();

        }

    }

}
