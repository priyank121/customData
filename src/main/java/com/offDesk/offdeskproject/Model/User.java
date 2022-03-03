package com.offDesk.offdeskproject.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user_record")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long userId;

    @Column(name = "user_name")
    private  String  userName;

    @Column(name="user_password")
    private String password;

    @Column(name="join_date")
    private  String joinDate;

    @Column(unique = true,nullable = false,length = 10)
    private  String mobile;

    @Column(nullable = false)
    private  String gender;

    @Column(nullable = false)
    private  String address;

   @Column(unique = true,nullable = false)
     private  String email;

    @Column(nullable = false)
   private  String designation;

    @JsonIgnore
    @OneToOne(cascade={CascadeType.ALL})
    private User manager;


    @JsonBackReference
    @OneToMany(mappedBy = "manager")
    private Set<User> listOfuser = new HashSet<User>();

    //@JsonIgnore
    @OneToMany(fetch= FetchType.EAGER,cascade= CascadeType.ALL,targetEntity = Leave.class)
    @JoinColumn
    private List<Leave> leaveDetails;


    public User(String userName, String password, String joinDate, String mobile,String gender ,String address, String email, String designation) {
        this.userName = userName;
        this.password = password;
        this.joinDate = joinDate;
        this.mobile = mobile;
        this.gender=gender;
        this.address = address;
        this.email = email;
        this.designation = designation;
    }

    public User() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Set<User> getListOfuser() {
        return listOfuser;
    }

    public void setListOfuser(Set<User> listOfuser) {
        this.listOfuser = listOfuser;
    }

    public List<Leave> getLeaveDetails() {
        return leaveDetails;
    }

    public void setLeaveDetails(List<Leave> leaveDetails) {
        this.leaveDetails = leaveDetails;
    }
}
