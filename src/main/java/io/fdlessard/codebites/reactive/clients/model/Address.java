package io.fdlessard.codebites.reactive.clients.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    private Long id;
    private String number;
    private String street;
    private String city;
    private String postalCode;
    private String province;
    private String country;

}