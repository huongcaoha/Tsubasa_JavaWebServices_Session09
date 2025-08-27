package com.example.session09.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private String name ;
    private String email ;
    private String password ;
    private String phoneNumber ;
}
