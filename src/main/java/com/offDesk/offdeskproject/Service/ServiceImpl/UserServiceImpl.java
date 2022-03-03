package com.offDesk.offdeskproject.Service.ServiceImpl;

import com.offDesk.offdeskproject.Dao.ILeaveRepository;
import com.offDesk.offdeskproject.Dao.IUserRepository;
import com.offDesk.offdeskproject.Model.Leave;
import com.offDesk.offdeskproject.Model.User;
import com.offDesk.offdeskproject.Service.ILeaveService;
import com.offDesk.offdeskproject.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Math.abs;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    ILeaveService iLeaveService;

    @Autowired
    ILeaveRepository iLeaveRepository;

    @Override
    public User userSave(User user) {
        return iUserRepository.save(user);

    }


    @Override
    public List<User> getManagerEmployee() {
        return iUserRepository.findAll();
    }

    @Override
    public List<User> getEmployeeByManagerEmail(String email) {

       User user= iUserRepository.getUserByEmail(email);
        return iUserRepository.EmployeeWithManagerId(user.getUserId());
    }

    @Override
    public Boolean giveLeaveApproveByManager(Long leaveId) throws ParseException {
        Integer newleave=0,oldLeaveBalance=0,oldTakenLeave=0,oldTotalLeave=0,oldExtraLeave=0;
        Leave leave = iLeaveRepository.getById(leaveId);

        Leave   Oldleave = iLeaveRepository.findLeaveDetailsForApprovedLeave(leaveId);
        if(Oldleave!=null) {
            oldLeaveBalance = Oldleave.getLeaveBalance();
            oldExtraLeave = Oldleave.getExtraLeave();
            oldTakenLeave = Oldleave.getTakenLeave();
            oldTotalLeave = Oldleave.getTotalLeave();
        }else {
            oldLeaveBalance = 12;
            oldExtraLeave = 0;
            oldTakenLeave =0;
            oldTotalLeave =0;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.parse(leave.getEndDate(),dtf);
        LocalDate fromDate = LocalDate.parse(leave.getFromDate(),dtf);
        Period diff = Period.between(endDate, fromDate);
        newleave = abs(diff.getDays());
        if(oldLeaveBalance<13 && oldLeaveBalance>0 && newleave<oldLeaveBalance)
        {
            leave.setLeaveBalance(oldLeaveBalance-newleave);
            leave.setTakenLeave(newleave+oldTakenLeave);
            leave.setTotalLeave(oldTotalLeave+newleave);
            leave.setExtraLeave(oldExtraLeave);
            leave.setLeaveStatus("Approved");
            iLeaveRepository.save(leave);
            return  true;

        }
        else if(leave.getLeaveBalance()==0 || newleave>oldLeaveBalance)
        {
            if(oldLeaveBalance>0) {
                leave.setTakenLeave(oldTakenLeave + newleave+oldLeaveBalance);
                leave.setTotalLeave(oldTotalLeave + newleave+oldLeaveBalance);
            }
            leave.setExtraLeave(newleave + oldExtraLeave);
            leave.setLeaveBalance(0);
            leave.setLeaveStatus("Approved");
            iLeaveRepository.save(leave);
            return  true;
        }
       else {
           return  false;
        }





    }

    @Override
    public User getUserByEmail(Long id) {

         User user = iUserRepository.getById(id);
        return  user;

    }

 @Override
 public Boolean rejectLeaveByManager(Long leaveId)
   {
       Boolean temp=false;
       Leave leave = iLeaveRepository.getById(leaveId);
       if(leave.getLeaveStatus().equalsIgnoreCase("waiting"))
       {
           leave.setLeaveStatus("Rejected");
           iLeaveRepository.save(leave);
           temp= true;
       }
   return  temp;

   }

    @Override
    public List<User> getEmployeeWaitAndApprovedState(String email) {
        User user = iUserRepository.getUserByEmail(email);
        return iUserRepository.getAllEmployeeWithStatusRejectAndApprove(user.getUserId());
    }

    @Override
    public List<String> getAllEmailManager() {
        return iUserRepository.getAllManager();
    }
}
