package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.CurrentUser;
import com.gladguys.alucoapi.entities.User;
import com.gladguys.alucoapi.entities.dto.UserDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.security.jwt.JwtAuthenticationRequest;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Qualifier("jwtService")
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Autentica usuário e retorna seu token")
	@PostMapping(value = "/api/auth")
	public ResponseEntity<?> authenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		final Authentication authentication = authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final UserDTO user = userService.getUserTeacherByEmail(authenticationRequest.getEmail());
		return ResponseEntity.ok(new CurrentUser(token, user));
	}

	private Authentication authenticate(String email, String password) {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (AuthenticationException e) {
			throw new ApiResponseException("Email e/ou senha incorretos");
		}
	}

}
