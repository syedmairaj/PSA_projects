package com.durgasoft.Controller;

import com.durgasoft.Entity.JwtToken;
import com.durgasoft.Entity.Signup;
import com.durgasoft.Exception.InvalidCredentialsException;
import com.durgasoft.Service.AuthService;
//import com.durgasoft.Service.JWTService;
import com.durgasoft.payload.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/vi/app")
public class AuthController {


    private AuthService authService;
    //private JWTService jwtService;
@Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
      //  this.jwtService = jwtService;
    }
//Role USer signup
    @PostMapping("/signup")

    public ResponseEntity<?> signUp(@RequestBody SignUpDto signdto) {
        return new ResponseEntity<>(authService.signup(signdto), HttpStatus.OK);
    }
//
//@DeleteMapping("{/id}")
//public ResponseEntity<String> delRecord(@PathVariable Long id){
//    return authService.delRecord(id);
//
//}

    //new Login

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody SignUpDto signUpDto){
    try{
        String token =authService.login(signUpDto);
        JwtToken jwtToken = new JwtToken();
        if(token !=null){
            jwtToken.setToken(token);
            jwtToken.setType("JWT");
        }
        return  new ResponseEntity<>(jwtToken,HttpStatus.OK);

    }catch(InvalidCredentialsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

}



    }





















//    @GetMapping("/login")
//    public ResponseEntity<?> login(@RequestBody SignUpDto signDto) {
//    String token = authService.login(signDto);
//    if(token!=null){
//        return new ResponseEntity<>(token,HttpStatus.OK);
//    }
//     return new ResponseEntity<>("Invalid",HttpStatus.INTERNAL_SERVER_ERROR);
//    //return new ResponseEntity<>(authService.login(signDto), HttpStatus.CREATED);
//
//        }


}