package com.example.demo.model.request.role;

import lombok.Builder;
import lombok.Data;

/**
 * @author Sombath
 * create at 24/1/24 4:02 PM
 */

@Data
@Builder
public class RoleRQ {

    private String name;
    private String code;

}
