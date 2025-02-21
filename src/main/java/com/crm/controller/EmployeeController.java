package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //http://localhost:8080/api/v1/employee/add
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EmployeeDto emp = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/employee?id=1
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee( @RequestParam long id) {
       employeeService.deleteEmployee(id);
       return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee?id=1
    @PutMapping
    public ResponseEntity<EmployeeDto> UpdateEmployee(
            @RequestParam long id,
    @RequestBody EmployeeDto dto
    ) {
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee?pageSize=5&sortBy=email&sortDir=asc
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam(name="pageSize",required = false,defaultValue = "5") int pageSize,
            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(name="sortBy",required = false,defaultValue = "name") String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue = "asc") String sortDir

    ) {
        List<EmployeeDto> employeesDto =employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(employeesDto,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/employeeId/1
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
    @PathVariable long empId

    ) {
        EmployeeDto dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
