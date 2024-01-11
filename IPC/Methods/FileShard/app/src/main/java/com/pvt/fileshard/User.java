package com.pvt.fileshard;

import java.io.Serializable;

/**
 * @author LIWEI
 */
public class User implements Serializable {
    private static final long SerialVersionUID = User.class.hashCode();
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
