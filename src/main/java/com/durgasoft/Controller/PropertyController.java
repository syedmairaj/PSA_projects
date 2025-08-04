package com.durgasoft.Controller;

import com.durgasoft.Entity.Property;
import com.durgasoft.Service.AuthService;
import com.durgasoft.Service.PropertyService;
import com.durgasoft.payload.PropertyDto;
import com.durgasoft.payload.SignUpDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/property")
public class PropertyController {

    private AuthService authService;
    private PropertyService propertyService;

    public PropertyController(AuthService authService, PropertyService propertyService) {
        this.authService = authService;
        this.propertyService = propertyService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authService.signUp(signUpDto), HttpStatus.CREATED);
    }



    //Blog manager signup - only admin can access this url by using jwt token

    @PostMapping("/Blog/signup")

    public ResponseEntity<?> blogSignup(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authService.blogSignup(signUpDto),HttpStatus.CREATED);
    }


   // add property
@PostMapping("/addproperty")

    public ResponseEntity<?> addProperty(@RequestBody PropertyDto propertyDto){
        Property prop = propertyService.addProperty(propertyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prop);
}
//add City
//@PostMapping("/city")
//    public ResponseEntity<PropertyDto> addCity(@RequestBody PropertyDto propertyDto){
//        return new ResponseEntity<>(propertyService.addCity(propertyDto),HttpStatus.CREATED);
//}
////add Country
//
//    @PostMapping("/country")
//    public ResponseEntity<PropertyDto> addCountry(@RequestBody PropertyDto propertyDto){
//
//        return new ResponseEntity<>(propertyService.addCountry(propertyDto),HttpStatus.CREATED);
//    }




    //search property by using city name



}
