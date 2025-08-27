package com.example.session09.controller;

import com.example.session09.model.dto.request.CustomerDTO;
import com.example.session09.model.dto.request.CustomerLoginDTO;
import com.example.session09.model.dto.response.CustomerLoginResponse;
import com.example.session09.model.entity.Customer;
import com.example.session09.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.registerCustomer(customerDTO);
        if(customer != null){
            return new ResponseEntity<>(customer , HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Resgister failed" , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginDTO customerLoginDTO) {
        CustomerLoginResponse customerLoginResponse = customerService.login(customerLoginDTO);
        if(customerLoginResponse != null){
            return new ResponseEntity<>(customerLoginResponse , HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Password or email not match !" , HttpStatus.BAD_REQUEST);
        }
    }
}
