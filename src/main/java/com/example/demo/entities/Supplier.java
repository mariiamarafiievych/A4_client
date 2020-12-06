package com.example.demo.entities;

import java.util.UUID;

public class Supplier {
    private UUID id;
    private String firstName;
    private String lastName;

    public Supplier(){

    }

    public Supplier(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }



    @Override
    public String toString() {
        return "Supplier{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
