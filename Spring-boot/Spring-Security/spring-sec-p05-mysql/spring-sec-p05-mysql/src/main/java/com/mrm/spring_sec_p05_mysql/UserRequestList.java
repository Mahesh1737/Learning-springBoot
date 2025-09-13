package com.mrm.spring_sec_p05_mysql;

import java.util.List;

public class UserRequestList {

    private List<userRequest> users;

    public List<userRequest> getUsers() {
        return users;
    }

    public void setUsers(List<userRequest> users) {
        this.users = users;
    }
}
