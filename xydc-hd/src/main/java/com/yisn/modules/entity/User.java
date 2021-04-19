package com.yisn.modules.entity;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String password;
    private String nickname;
    private int sex;
    private int identity;
    private int tel;
}
