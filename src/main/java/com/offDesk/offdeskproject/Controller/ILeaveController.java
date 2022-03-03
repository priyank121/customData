package com.offDesk.offdeskproject.Controller;

import com.offDesk.offdeskproject.Dto.LeaveDto;
import com.offDesk.offdeskproject.Model.Leave;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/offdesk/leave")
public interface ILeaveController {

    @PostMapping("/save")
    Boolean takeLeave(@RequestBody LeaveDto leaveDto);

    @GetMapping("/getleaveuserbyemail/{email}")
    List<Leave> getLeaveRecordByMail(@PathVariable String email);

    @GetMapping("/findLeaveBalance/{email}")
    Leave checkLeaveBalanceByMail(@PathVariable String mail);

}
