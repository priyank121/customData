package com.offDesk.offdeskproject.Controller;

import com.offDesk.offdeskproject.Dto.UserDto;
import com.offDesk.offdeskproject.Model.User;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
@CrossOrigin("*")
@RequestMapping("/offdesk/user")
public interface IUserController {

    @PostMapping("/save")
    User createUser(@RequestBody UserDto userDto);

    //Admin Dashboard
    @GetMapping("/all_employee")
    List<User> getManagerEmployee();

    //Manager Dashboard
    @GetMapping("/employee_by_managerid/{email}")
    List<User> getEmployeeByManagerEmail(@PathVariable String email);

    //Manager Dashboard Approved Button
    @GetMapping("/giveApproveByManager/{id}")
    Boolean giveLeaveApproveByManager(@PathVariable("id") Long id) throws ParseException;


    //Return Object of an user through Email
    @GetMapping("/getuserbyemail/{email}")
    User getUserByemail(@PathVariable("email") String email);


    //Manager Dashboard Approved Button
    @GetMapping("RejectByManager/{id}")
    Boolean rejectLeaveByManager(@PathVariable("id") Long leaveId);


    //Manager Dashboard History Button
    @GetMapping("GetAllEmployeeWithApprovedOrrejectByManager/{email}")
    List<User> getEmployeeWaitAndApprovedState(@PathVariable String email);
    //Manag
    @GetMapping("/getAllmanager")
    List<String> getEmailAllManager();


}
