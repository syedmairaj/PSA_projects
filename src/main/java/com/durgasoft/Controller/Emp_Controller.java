package com.durgasoft.Controller;

import com.durgasoft.Entity.Ent_emp;
import com.durgasoft.Service.emp_Service;
import com.durgasoft.payload.emp_Dto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vi/api/emp/")
@RestController
public class Emp_Controller {

    private emp_Service empService;
    public Emp_Controller(emp_Service empService) {
        this.empService = empService;
    }
//Adding employee records

   @PostMapping("add")
    public ResponseEntity<?> addemp(@Valid @RequestBody emp_Dto empdto, BindingResult result){
        if(result.hasErrors()){
            String errormsg = result.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(errormsg, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return empService.addemp(empdto);
    }
  //Updating employee records
  @PutMapping("{id}")
  public ResponseEntity<?> updateEmp(@PathVariable Long id, @RequestBody  emp_Dto dto){
        return empService.updateEmp(id, dto);
  }
//Deleting Employee records
    @DeleteMapping("{id}")

    public ResponseEntity<?> DelEmp(@PathVariable Long id){
        return empService.DelEmp(id);
    }
    //Get all Employeedata

    @GetMapping //pagination and sorting
//  this getempl is use only entity and get the data,
//    public List<Ent_emp> getEmp(){
//        List<Ent_emp> emp =empService.getEmp();
//        return emp;
//    }
// this use dto to get the data
    // http://localhost:8081/vi/api/emp?pageSize=3&pageNo=1&sortBy=name&sortDir=asc     use this url to fetch the data
    public ResponseEntity<List<emp_Dto>> getEmployee(
            @RequestParam(name="pageSize",required = false, defaultValue = "5") int pageSize,
            @RequestParam(name="pageNo",required = false, defaultValue = "0") int pageNo,
            //sortby name
            @RequestParam(name="sortBy", required=false,defaultValue="name") String sortBy,
            //sorting ascending order
            @RequestParam(name="sortDir",required = false,defaultValue = "asc") String sortDir)
    {

            List<emp_Dto> all = empService.getEmployee(pageSize, pageNo,sortBy,sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }


    //Get Emp By id

    @GetMapping("{id}")
    public emp_Dto getById(@PathVariable Long id){
        return empService.getByid(id);
    }

}
