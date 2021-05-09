package com.yisn.modules.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String password;
    private String nickname;
    private int sex;
    private int identity;
    private Integer tel;
}
