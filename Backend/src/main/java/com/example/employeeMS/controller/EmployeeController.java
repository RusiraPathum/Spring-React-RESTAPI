package com.example.employeeMS.controller;

import com.example.employeeMS.dto.EmployeeDTO;
import com.example.employeeMS.dto.ResponseDTO;
import com.example.employeeMS.entity.Employee;
import com.example.employeeMS.service.EmployeeService;
import com.example.employeeMS.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {

            String res = employeeService.saveEmployee(employeeDTO);

            if (res.equals("00")){

                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {

                responseDTO.setCode(VarList.RSP_DUPLICATE);
                responseDTO.setMessage("Already Registered");
                responseDTO.setContent(employeeDTO);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);


            }else {

                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception exception){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {

            String res = employeeService.updateEmployee(employeeDTO);

            if (res.equals("00")){

                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {

                responseDTO.setCode(VarList.RSP_DUPLICATE);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(employeeDTO);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);


            }else {

                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception exception){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllEmployee")
    public ResponseEntity getAllEmployee(){

        try {

            List<Employee> employeeDTOList = employeeService.getAllEmployees();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(employeeDTOList);

            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception exception){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID){

        try {

            EmployeeDTO employeeDTO = employeeService.searchEmployee(empID);

            if (employeeDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For This EmpID");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID){

        try {

            String res = employeeService.deleteEmployee(empID);

            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For This EmpID");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
