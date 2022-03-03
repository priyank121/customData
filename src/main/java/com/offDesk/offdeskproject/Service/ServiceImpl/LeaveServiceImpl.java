package com.offDesk.offdeskproject.Service.ServiceImpl;

import com.offDesk.offdeskproject.Dao.ILeaveRepository;
import com.offDesk.offdeskproject.Dao.IUserRepository;
import com.offDesk.offdeskproject.Model.Leave;
import com.offDesk.offdeskproject.Model.User;
import com.offDesk.offdeskproject.Service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements ILeaveService {

    @Autowired
    ILeaveRepository iLeaveRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public Leave takeLeaveByEmployee(Leave leave,Long userIdForLeave) {



        Leave leave1 = iLeaveRepository.findLeaveForBalance(userIdForLeave);
        if(leave1==null) {
            leave.setLeaveBalance(12);
        }
        else {
            leave.setLeaveBalance(leave1.getLeaveBalance());
        }
        iLeaveRepository.save(leave);

        User user = iUserRepository.getById(userIdForLeave);


        List<Leave> listOfLeave = user.getLeaveDetails();
        listOfLeave.add(leave);

        user.setLeaveDetails(listOfLeave);

        iUserRepository.save(user);


        return leave;
    }

    @Override
    public List<Leave> getLeaveRecordByMail(Long id) {
        return iLeaveRepository.findAllLeaveDetailsById(id);
    }

    @Override
    public Leave checkLeaveBalanceByMail(String gmail) {

            User user = iUserRepository.getUserByEmail(gmail);
           // Leave leave = iLeaveRepository.findLeaveDetailsUsingUserId(user.getUserId());

                return iLeaveRepository.findLeaveForBalance(user.getUserId());

    }
}
