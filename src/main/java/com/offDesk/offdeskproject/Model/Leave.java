package com.offDesk.offdeskproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="leave_record")
@Data
@AllArgsConstructor
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "leave_id")
    private  Long LeaveId;

    @Column(name = "from_date",length = 25)
    private  String fromDate;

    @Column(name="end_date",length = 25)
     private String endDate;

    @Column(name = "leave_status",nullable = false,length = 10)
    private String leaveStatus;

    @Column(name="leave_balance")
    private Integer leaveBalance=12;

    @Column(name="total_leave")
    private Integer totalLeave;

    @Column(name = "extra_leave")
    private  Integer extraLeave;

    @Column(name="taken_leave",length = 100)
    private Integer takenLeave;


    @Column(name="leave_purpose",length = 20)
    private  String leavePurpose;


    public Leave(String fromDate, String endDate, String leaveStatus,String leavePurpose) {
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.leaveStatus = leaveStatus;
        this.leavePurpose= leavePurpose;
        this.extraLeave=0;
        this.takenLeave=0;
        this.totalLeave=0;
    }
    public Leave(){}
}
