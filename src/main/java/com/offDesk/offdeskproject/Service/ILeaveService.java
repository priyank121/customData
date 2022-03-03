package com.offDesk.offdeskproject.Service;

import com.offDesk.offdeskproject.Model.Leave;

import java.util.List;

public interface ILeaveService {

    Leave takeLeaveByEmployee(Leave leave,Long userIdForLeave);

     List<Leave> getLeaveRecordByMail(Long id);

     Leave checkLeaveBalanceByMail(String gmail);
}
