package com.offDesk.offdeskproject.Dao;

import com.offDesk.offdeskproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user_record WHERE  manager_user_id=?1 and  user_id IN(SELECT  leave_details_user_id from leave_record WHERE  leave_status='waiting')",nativeQuery = true)
     List<User> EmployeeWithManagerId(Long id);

    @Query(value = "select * from user_record c where c.email=?1",nativeQuery = true)
     User getUserByEmail(String email);

    @Query(value = "SELECT * FROM user_record WHERE  manager_user_id=?1 and  user_id IN(SELECT  leave_details_user_id from leave_record WHERE  leave_status='Approved' or leave_status='Rejected')",nativeQuery = true)
    List<User> getAllEmployeeWithStatusRejectAndApprove(Long id);

    @Query(value = "select email from user_record where designation='Manager'",nativeQuery = true)
    List<String> getAllManager();

}
