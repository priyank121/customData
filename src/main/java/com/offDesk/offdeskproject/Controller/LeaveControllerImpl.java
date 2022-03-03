package com.offDesk.offdeskproject.Controller;

import com.offDesk.offdeskproject.Dao.ILeaveRepository;
import com.offDesk.offdeskproject.Dao.IUserRepository;
import com.offDesk.offdeskproject.Dto.LeaveDto;
import com.offDesk.offdeskproject.Model.Leave;
import com.offDesk.offdeskproject.Model.User;
import com.offDesk.offdeskproject.Service.ILeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class LeaveControllerImpl implements ILeaveController{

    @Autowired
    ILeaveService iLeaveService;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    ILeaveRepository iLeaveRepository;

    @Override
    public Boolean takeLeave(LeaveDto leaveDto) {

        String fromDate[] = (leaveDto.getFromDate()).split("T");
        String endDate[] = (leaveDto.getEndDate()).split("T");
        User user=iUserRepository.getUserByEmail(leaveDto.getUserLeaveEmail());
        Leave leave1 = new Leave(fromDate[0], endDate[0], leaveDto.getLeaveStatus(), leaveDto.getLeavePurpose());
        log.info("Email Taken Leave Methods:email-", leaveDto.getUserLeaveEmail());


            Leave leave = iLeaveRepository.CheckLeaveStatusWaiting(user.getUserId());
            if (leave == null) {
                iLeaveService.takeLeaveByEmployee(leave1, user.getUserId());
                return true;
            } else {
                return false;
            }


    }

    @Override
    public List<Leave> getLeaveRecordByMail(String email) {
        log.info("Leave Controller Inside ......GetLeaveRecord By Method");
       User user= iUserRepository.getUserByEmail(email);
       log.info(" User: {}",user);
       return iLeaveService.getLeaveRecordByMail(user.getUserId());
    }

    @Override
    public Leave checkLeaveBalanceByMail(String email) {
        return iLeaveService.checkLeaveBalanceByMail(email);
    }
}
