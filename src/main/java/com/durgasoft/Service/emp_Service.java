package com.durgasoft.Service;

import com.durgasoft.Entity.Ent_emp;
import com.durgasoft.Exception.ResourceNotFound;
import com.durgasoft.Exception.TestNotFound;
import com.durgasoft.Repository.Ent_empRepository;
import com.durgasoft.payload.emp_Dto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class emp_Service {

    private Ent_empRepository empRepo;

    public emp_Service(Ent_empRepository empRepo) {
        this.empRepo = empRepo;
    }

    @Autowired
    private ModelMapper mapper;
//pagination and sorting
    public List<emp_Dto> getEmployee(int pageSize, int pageNo, String sortBy, String sortDir) {
        //when selecting pagesize, pageNo and sort then .of loaded methods select from that
        // argument mismatch it cannot take string for "sortBy"  so we have to Sort class method by
        //ternary operator use to decide for asc or dsc ordrrs
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
    Pageable page = PageRequest.of(pageSize,pageNo, sort);

    Page<Ent_emp>  emppage = empRepo.findAll(page); // convert this list
        List<Ent_emp> listpage = emppage.getContent();  //this converts from page to list
     //  List<emp_Dto> dtodb = EntityToDto(listpage);
        List<emp_Dto> dtodata = listpage.stream().map(e->EntityToDto(e)).collect(Collectors.toList());

    return dtodata;
    }


    //Employee Adding
    public ResponseEntity<?> addemp(emp_Dto empdto) {
        Ent_emp empdata = DtoToEntity(empdto);
        Ent_emp dbsave = empRepo.save(empdata);
        emp_Dto dtodata = EntityToDto(dbsave);
        return new ResponseEntity<>(dbsave, HttpStatus.CREATED);

    }

    //Employee Updating
    public ResponseEntity<?> updateEmp(Long id, emp_Dto dto) {
        Optional<Ent_emp> findemp = empRepo.findById(id);
        if (findemp.isPresent()) {
            Ent_emp empdata = findemp.get();
            empdata.setEname(dto.getEname());
            empdata.setEemail(dto.getEemail());
            empdata.setEsal(dto.getEsal());
            empdata.setEmobile(dto.getEmobile());
            Ent_emp empsaved = empRepo.save(empdata);
            return new ResponseEntity<>(empsaved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(+id + ": ID is not found", HttpStatus.OK);
        }
    }
    //Del Emp

//    public ResponseEntity<?> DelEmp(Long id) {
//        Optional<Ent_emp> findId = empRepo.findById(id);
//        if(findId.isPresent()){
//           empRepo.deleteById(id);
//            return new ResponseEntity<>("ID Deleted",HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(+id+ "id is not found",HttpStatus.NOT_FOUND);
//
//        }
//
//    }

    public ResponseEntity<?> DelEmp(Long id) {
        Optional <Ent_emp> empdata = empRepo.findById(id);
        if(!empdata.isPresent()){
            throw new TestNotFound("ID:" + id+" is not found to delete: ");
        } else{
            empRepo.deleteById(id);
        }
        return new ResponseEntity<>("ID deleted Succfully",HttpStatus.OK);
    }
    //Get Empdata

    public List<Ent_emp> getEmp() {

           List<Ent_emp> empdata = empRepo.findAll();
           return empdata;
    }

//    public emp_Dto getByid(Long id) {
//        Ent_emp empdata = empRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Record not found with ID:" + id));
//        emp_Dto dtodata = EntityToDto(empdata);
//        return dtodata;
//    }



    //DTOtoEntity

        public Ent_emp DtoToEntity(emp_Dto dto){
            return mapper.map(dto, Ent_emp.class);
        }
        //EntityToDTO

        public emp_Dto EntityToDto (Ent_emp emp){
            return mapper.map(emp, emp_Dto.class);
        }


    public emp_Dto getByid(Long id) {
        Ent_emp empdata = empRepo.findById(id).orElseThrow(() ->new ResourceNotFound("Record not found" + id));
        emp_Dto dtodata = EntityToDto(empdata);
        return dtodata;
    }


}
