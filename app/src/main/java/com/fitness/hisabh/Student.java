package com.fitness.hisabh;

public class Student {

    String name,surname,id,date;

    public Student( String id,String name, String surname,String date) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.surname = surname;
    }
    public String getDateId() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
