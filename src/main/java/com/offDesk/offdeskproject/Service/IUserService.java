package com.offDesk.offdeskproject.Service;

import com.offDesk.offdeskproject.Model.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface IUserService {

     User userSave(User user);

     List<User> getManagerEmployee();

     List<User> getEmployeeByManagerEmail(String email);

     Boolean giveLeaveApproveByManager(Long id) throws ParseException;

     User getUserByEmail(Long id);

      Boolean rejectLeaveByManager(Long leaveId);

      List<User> getEmployeeWaitAndApprovedState(String email);


      List<String> getAllEmailManager();
}
