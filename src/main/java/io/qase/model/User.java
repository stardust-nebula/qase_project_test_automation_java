package io.qase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email = "gykyz@mailto.plus";
    private String password = "123456789";
    //  Squash 3-3
}
