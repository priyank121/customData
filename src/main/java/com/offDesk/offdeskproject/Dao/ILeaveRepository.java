package com.offDesk.offdeskproject.Dao;

import com.offDesk.offdeskproject.Model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILeaveRepository extends JpaRepository<Leave,Long> {

    @Query(value = " select * from leave_record c where c.leave_details_user_id=?1 and c.leave_status='waiting' order by leave_id desc limit 1",nativeQuery = true)
    Leave findLeaveDetailsUsingUserId(Long id);

    @Query(value = " select * from leave_record c where c.leave_details_user_id=?1",nativeQuery = true)
    List<Leave> findAllLeaveDetailsById(Long id);

    @Query(value = "select * from leave_record c where c.leave_details_user_id=?1 and c.leave_status='Approved' order by leave_id desc limit 1",nativeQuery = true)
    Leave findLeaveForBalance(Long id);



    @Query(value = " select * from leave_record c where c.leave_details_user_id=?1 and c.leave_status='waiting'",nativeQuery = true)
    Leave CheckLeaveStatusWaiting(Long id);

    @Query(value = "select * from leave_record c where c.leave_details_user_id=(select  leave_details_user_id from leave_record where leave_id=?1) and c.leave_status='Approved' order by leave_id desc limit 1",nativeQuery = true)
    Leave findLeaveDetailsForApprovedLeave(Long leaveId);

}
