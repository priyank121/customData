package com.offDesk.offdeskproject.Service.ServiceImpl;

import com.offDesk.offdeskproject.Dao.IUserRepository;
import com.offDesk.offdeskproject.Model.CustomUserDetails;
import com.offDesk.offdeskproject.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository iuserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        log.info(" UserName From USERDETAILS METHOD : {}",userName);
        User user = this.iuserRepository.getUserByEmail(userName);

        if (user==null) {
            throw new UsernameNotFoundException("User not found !!");

        } else {
         return new CustomUserDetails(user);

        }
    }
}
