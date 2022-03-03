package com.offDesk.offdeskproject.Controller;

import com.offDesk.offdeskproject.Helper.JwtUtil;
import com.offDesk.offdeskproject.Model.JwtRequest;
import com.offDesk.offdeskproject.Model.JwtResponse;
import com.offDesk.offdeskproject.Service.ServiceImpl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/jwt")
public class JwtController {

     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private CustomUserDetailsService customUserForSingUpService;

     @Autowired
    private  JwtUtil jwtUtil;

   @PostMapping("/token")
    public  JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {


        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch(UsernameNotFoundException e){
           e.printStackTrace();
           throw  new Exception("Bad credentials");
        }catch (BadCredentialsException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.customUserForSingUpService.loadUserByUsername(jwtRequest.getUsername());
        String token =this.jwtUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
