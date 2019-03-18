package com.homeproject;

public class User {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String age;
    private String gender;
    private String dateOfBorn;
    private String inn;
    private String index;
    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String flat;

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPatronymic(){
        return patronymic;
    }

    public String getAge(){
        return age;
    }

    public String getGender(){
        return gender;
    }

    public String getState(){
        return state;
    }

    public String getCity(){
        return city;
    }

    public String getStreet(){
        return street;
    }

    public String getDateOfBorn(){
        return dateOfBorn;
    }

    public String getInn(){
        return inn;
    }

    public String getIndex(){
        return index;
    }

    public String getHouse(){
        return house;
    }

    public String getFlat(){
        return flat;
    }

    public String getCountry(){
        return country;
    }

    public User (String firstName,
                 String lastName,
                 String patronymic,
                 String age,
                 String gender,
                 String dateOfBorn,
                 String city,
                 String street,
                 String country,
                 String flat,
                 String inn,
                 String index,
                 String house,
                 String state
                 ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.city = city;
        this.street = street;
        this.age=age;
        this.patronymic=patronymic;
        this.gender=gender;
        this.dateOfBorn=dateOfBorn;
        this.house=house;
        this.flat=flat;
        this.index=index;
        this.inn=inn;
        this.country=country;
    }
}
