package com.example.formvalidation;

public class User {
    //This class will be as a template for the data that we are going to parse

    private String FirstName;
    private String LastName;
    private String Email;
    private String DOB;
    private String Gender;
    private String BuildingNo;
    private String Street;
    private String City;
    private String State;
    private String PinCode;
    private String ContactNo;

    private String Skill;

    public User(String firstName, String lastName, String email, String DOB, String gender, String buildingNo, String street, String city, String state, String pinCode, String contactNo, String skill) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        this.DOB = DOB;
        Gender = gender;
        BuildingNo = buildingNo;
        Street = street;
        City = city;
        State = state;
        PinCode = pinCode;
        ContactNo = contactNo;
        Skill = skill;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBuildingNo() {
        return BuildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        BuildingNo = buildingNo;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }
}

