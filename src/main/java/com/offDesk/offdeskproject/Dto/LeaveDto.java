package com.offDesk.offdeskproject.Dto;

import lombok.Data;

@Data
public class LeaveDto {

    private  Long LeaveId;

    private  String fromDate;

    private String endDate;

    private String userLeaveEmail;

    private  String leaveStatus;

    private  String leavePurpose;

}
