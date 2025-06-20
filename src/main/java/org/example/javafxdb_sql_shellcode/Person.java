package org.example.javafxdb_sql_shellcode;

import javafx.scene.shape.Path;

public class Person {


    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Path image;
    public Person(Integer id, String name, String email, String phone, String address, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }
    public Person() {

    }
    public Person(Integer id, String name, String email, String phone, String address, String password, Path image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.image = image;
    }
    public Person(int id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Path getImage() {
        return image;
    }
    public void setImage(Path image) {
        this.image = image;
    }



}