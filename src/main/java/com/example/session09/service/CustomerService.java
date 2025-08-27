package com.example.session09.service;

import com.example.session09.model.constant.Role;
import com.example.session09.model.dto.request.CustomerDTO;
import com.example.session09.model.dto.request.CustomerLoginDTO;
import com.example.session09.model.dto.response.CustomerLoginResponse;
import com.example.session09.model.entity.Customer;
import com.example.session09.repository.CustomerRepository;
import com.example.session09.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer
                .builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .password(passwordEncoder.encode(customerDTO.getPassword()))
                .phoneNumber(customerDTO.getPhoneNumber())
                .role(Role.USER)
                .build();
        try {
            return customerRepository.save(customer);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public CustomerLoginResponse login(CustomerLoginDTO customerLoginDTO) {
        Customer customer = findByEmail(customerLoginDTO.getEmail());
        if (customer != null && passwordEncoder.matches(customerLoginDTO.getPassword(), customer.getPassword())) {
            return CustomerLoginResponse
                    .builder()
                    .email(customer.getEmail())
                    .accessToken(jwtProvider.generateToken(customer.getEmail()))
                    .build();
        }else {
            return null;
        }
    }
}
