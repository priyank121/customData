package com.offDesk.offdeskproject.Controller;

import com.offDesk.offdeskproject.Dao.ILeaveRepository;
import com.offDesk.offdeskproject.Dao.IUserRepository;
import com.offDesk.offdeskproject.Dto.UserDto;
import com.offDesk.offdeskproject.Model.User;
import com.offDesk.offdeskproject.Service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
public class UserControllerImpl implements IUserController{

    @Autowired
    IUserService iUserService;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    ILeaveRepository iLeaveRepository;

    @Override
    public User createUser(UserDto userDto) {
        String joinDate[]=(userDto.getJoinDate()).split("T");
        User user = new User(userDto.getUserName(),userDto.getPassword(),joinDate[0],userDto.getMobile(),userDto.getGender(),userDto.getAddress(),userDto.getEmail(),userDto.getDesignation());
        if((userDto.getManagerUsername()).equalsIgnoreCase("null"))
       {
           return iUserService.userSave(user);
       }
       else {
           User manager  =iUserRepository.getUserByEmail(userDto.getManagerUsername());
           log.info("INSIDE USER SAVE METHOD :{}",userDto.getManagerUsername());
           User managerUser = iUserRepository.getById(manager.getUserId());
           user.setManager(managerUser);
           return iUserService.userSave(user);
       }
    }


    @Override
    public List<User> getManagerEmployee() {
        return iUserService.getManagerEmployee();
    }

    @Override
    public List<User> getEmployeeByManagerEmail(String email) {

        return iUserService.getEmployeeByManagerEmail(email);
    }

    @Override
    public Boolean giveLeaveApproveByManager(Long id) throws ParseException {
        return iUserService.giveLeaveApproveByManager(id);
    }

    @Override
    public User getUserByemail(String email) {

       User user= iUserRepository.getUserByEmail(email);

        return iUserService.getUserByEmail(user.getUserId());
    }

    @Override
    public Boolean rejectLeaveByManager(Long leaveId) {
        return iUserService.rejectLeaveByManager(leaveId);
    }

    @Override
    public List<User> getEmployeeWaitAndApprovedState(String email) {
        return iUserService.getEmployeeWaitAndApprovedState(email);
    }

    @Override
    public List<String> getEmailAllManager() {
        return iUserService.getAllEmailManager();
    }


}
