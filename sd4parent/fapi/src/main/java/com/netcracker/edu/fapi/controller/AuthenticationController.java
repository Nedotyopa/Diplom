package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.config.JwtTokenUtil;
import com.netcracker.edu.fapi.models.AuthToken;
import com.netcracker.edu.fapi.models.LoginUser;
import com.netcracker.edu.fapi.Constants;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/token")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDataService userDataService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        boolean verify = userDataService.verifyUser(loginUser);

        if(verify) {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getLogin(),
                            loginUser.getPassword()
                    )
            );
            final String token = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok(new AuthToken(token));
        } else {
            return null;
        }

    }

    @RequestMapping(value = "/expDate", method = RequestMethod.GET)
    public ResponseEntity<?> GetExpDate(@PathVariable String token) {
        token = token.replace(Constants.TOKEN_PREFIX, "");
        return ResponseEntity.ok(jwtTokenUtil.getExpirationDateFromToken(token));
    }

}
