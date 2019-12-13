package hr.sedamit.demo.security;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
public class JwtResponse {


    private final String jwtToken;


}
