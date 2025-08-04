package com.durgasoft.Service;

import com.durgasoft.Entity.Signup;
import com.durgasoft.Exception.InvalidCredentialsException;
import com.durgasoft.Exception.TestNotFound;
import com.durgasoft.Repository.SignupRepository;
import com.durgasoft.payload.SignUpDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

@Service
public class AuthService {


    @Autowired
    private ModelMapper mapper;
    @Autowired
    private JWTService jwtService;

    private SignupRepository signupRepository;

    public AuthService(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

//generate token using JWTService



    //User - signup
    public ResponseEntity<?> signup(SignUpDto signdto) {
        Optional<Signup> Email_val =signupRepository.findByEmail(signdto.getEmail());
        if(Email_val.isPresent()){
            throw new TestNotFound("Email is already Exist:" + signdto.getEmail());

        }
        Optional<Signup> mobile_Val = signupRepository.findByMobile(signdto.getMobile());
        if(mobile_Val.isPresent()) {
            throw new TestNotFound("Mobile is already Exists:" + signdto.getMobile());
        }
        Optional<Signup> login_Val = signupRepository.findByLogin(signdto.getLogin());
        if(login_Val.isPresent()){
            throw new TestNotFound("Login is already Exists:" + signdto.getLogin());
        }
        //password encryption
        signdto.setPassword(BCrypt.hashpw(signdto.getPassword(),BCrypt.gensalt(10)));
        signdto.setRole("ROLE_USER");
        Signup sign_db = DtoToEntity(signdto);
        Signup sign_save = signupRepository.save(sign_db);
        SignUpDto dto_db = EntityToDto(sign_save);
        return new ResponseEntity<>(dto_db, HttpStatus.CREATED);
    }



//Login
//public String login(SignUpDto signDto) {
//    Optional<Signup> findLogin = signupRepository.findByLogin(signDto.getLogin());
//    if(findLogin.isPresent()) {
//        Signup getData = findLogin.get();
//        if(BCrypt.checkpw(signDto.getPassword(),getData.getPassword())){
//            //once the user is verifed then generate token
//           return jwtService.generateToken(getData.getUsername());
//
//
//           // return new ResponseEntity<>("Welcome to SCB", HttpStatus.OK);
//        } else {
//            throw new TestNotFound("Password Incorrect ");
//        }
//
//    }
//    return null;

//    }
//new Login



    public String login(SignUpDto signUpDto) {
        if(signUpDto ==null || signUpDto.getLogin() ==null || signUpDto.getPassword()==null){
            throw new InvalidCredentialsException("Username and password cannot be null or empty");
        }
        Optional<Signup> findlogin =signupRepository.findByLogin(signUpDto.getLogin());
        if(findlogin.isEmpty()){
            throw new InvalidCredentialsException("User not found");
        }
        Signup signdata = findlogin.get();
        if(!BCrypt.checkpw(signUpDto.getPassword(),signdata.getPassword())){
            throw new InvalidCredentialsException("Password is incorrect");
        }
        return jwtService.generateToken(signdata.getUsername());

    }



//del

//    public ResponseEntity<String> delRecord(Long id) {
//        Optional<Signup> findID =signupRepository.findById(id);
//        if(findID.isPresent()){
//            signupRepository.deleteById(id);
//            return new ResponseEntity<>("Deleted" ,HttpStatus.OK);
//        }
//        return new ResponseEntity<>("ID is not found",HttpStatus.NOT_FOUND);
//    }



// Property owners Signup

    public ResponseEntity<?> signUp(SignUpDto signUpDto) {
        if(signUpDto.getUsername().isEmpty() || signUpDto.getMobile().isEmpty() || signUpDto.getEmail().isEmpty() || signUpDto.getUsername() ==null || signUpDto.getMobile()==null
                || signUpDto.getEmail()==null){
            throw new InvalidCredentialsException("Fields cannot be empty");
        }
        Optional<Signup> signup_data =signupRepository.findByLogin(signUpDto.getLogin());
        if(signup_data.isPresent()){
            throw new TestNotFound("username is already available");
        }
        String hasahpwd = BCrypt.hashpw(signUpDto.getPassword(),BCrypt.gensalt(10));
            signUpDto.setPassword(hasahpwd);
            signUpDto.setRole("ROLE_PROPERTYOWNER");
            Signup signconvert = DtoToEntity(signUpDto);
            Signup signsave = signupRepository.save(signconvert);
            SignUpDto dtoconvert = EntityToDto(signsave);
            return new ResponseEntity<>(dtoconvert,HttpStatus.OK);

    }

    //Blog Signup

    public ResponseEntity<?> blogSignup(SignUpDto signUpDto) {
        if(signUpDto.getUsername().isEmpty() || signUpDto.getEmail().isEmpty() || signUpDto.getMobile().isEmpty() || signUpDto.getUsername() ==null ||
                signUpDto.getEmail()==null || signUpDto.getMobile()==null){
            throw new InvalidCredentialsException("Fields cannot be empty or null");
        }
        Optional<Signup> findEmail =signupRepository.findByEmail(signUpDto.getEmail());
        Optional<Signup> findMobile = signupRepository.findByMobile(signUpDto.getMobile());
        if(findMobile.isPresent() || findEmail.isPresent()){
            throw new TestNotFound("User is already available");
        }
        String hashpwd = BCrypt.hashpw(signUpDto.getPassword(),BCrypt.gensalt(10));
        signUpDto.setPassword(hashpwd);
        signUpDto.setRole("ROLE_MANAGER");
        Signup signdata_conv = DtoToEntity(signUpDto);
        Signup signsave = signupRepository.save(signdata_conv);
        SignUpDto dtodata_covn = EntityToDto(signsave);
        return new ResponseEntity<>(dtodata_covn,HttpStatus.OK);

    }






    //modelmapper

    //DtotoEntity

    public Signup DtoToEntity(SignUpDto signdto){
        return mapper.map(signdto,Signup.class);
    }
    //EntityToDto

    public SignUpDto EntityToDto(Signup signup){
        return mapper.map(signup,SignUpDto.class);
    }



}
