package com.example.E_COMMERCE_PLATFORM.Controller;

import com.example.E_COMMERCE_PLATFORM.DTO.Register_DTO;
import com.example.E_COMMERCE_PLATFORM.DTO.UserDto;
import com.example.E_COMMERCE_PLATFORM.DTO.change_password_req;
import com.example.E_COMMERCE_PLATFORM.DTO.login_DTO;
import com.example.E_COMMERCE_PLATFORM.Entites.Role;
import com.example.E_COMMERCE_PLATFORM.Mapper.UserMapper;
import com.example.E_COMMERCE_PLATFORM.REPO.UsersRepository;
import com.example.E_COMMERCE_PLATFORM.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class usercontroller {
    private  final UsersRepository urepo;

    private  final  UserMapper usermapper;
    private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;
private final AuthenticationManager authenticationManager;
    @GetMapping("/users")
    public List<UserDto> getAllUsers(@RequestParam(required = false) String sort) {
        Sort sorting = (sort != null && !sort.isBlank()) ? Sort.by(sort) : Sort.unsorted();

        return urepo.findAll(sorting)
                .stream()
                .map(user -> usermapper.toDto(user))
                .toList();
    }

@GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){
    var user = urepo.findById(id).orElse(null);
    if (user == null) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(usermapper.toDto(user));
    }
}
    @PostMapping("/register")
    public AuthenticationResponse createuser(@RequestBody Register_DTO user_data){
    var user =usermapper.toEntity(user_data);
    user.setPassword(passwordEncoder.encode(user_data.getPassword()));
    user.setRole(Role.USER);
    urepo.save(user);

    var jwtToken=jwtService.generateToken(user);
return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody login_DTO login_request){
//        System.out.println("Login attempt for email: " + login_request.getEmail());

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        login_request.getUsername(),
                        login_request.getPassword()

                )

        );
        var user=urepo.findByUsername(login_request.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        System.out.println(jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateuser(@PathVariable (name = "id") int id,
                                             @RequestBody  UserDto update
                              ){

        var user = urepo.findById(id).orElse(null);

        if (user == null) {
            System.out.println("bl77777");
            return ResponseEntity.notFound().build();

        } else {
            usermapper.update(update,user);
            urepo.save(user);
            return ResponseEntity.ok(usermapper.toDto(user));

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteuser(@PathVariable (name = "id") int id)
    {
        var user = urepo.findById(id).orElse(null);

        if (user == null) {
            System.out.println("bl77777");
            return ResponseEntity.notFound().build();

        }else{

            urepo.delete(user);
            return ResponseEntity.noContent().build();

        }

    }
    @PostMapping("/{id}/password")
    public ResponseEntity<Void> changepassword(@PathVariable (name = "id") int id,@RequestBody change_password_req request){
        var user = urepo.findById(id).orElse(null);
        if (user == null) {
            System.out.println("bl77777");
            return ResponseEntity.notFound().build();

        } else if (!user.getPassword().equals(request.getOldpassword())) {

    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            
        }else {

user.setPassword(request.getNewpassword());
urepo.save(user);
            return ResponseEntity.noContent().build();
        }

    }
}
