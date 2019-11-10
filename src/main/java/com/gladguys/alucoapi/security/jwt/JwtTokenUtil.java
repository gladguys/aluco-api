package com.gladguys.alucoapi.security.jwt;

import com.gladguys.alucoapi.services.TeacherService;
import com.gladguys.alucoapi.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1l;

	static final String CLAIM_KEY_USERNAME ="sub";
	static final String CLAIM_KEY_TEACHER ="teacherId";
	static final String CLAIM_KEY_CREATED="created";
	static final String CLAIM_KEY_EXPIRED ="exp";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private Claims claims;

	@Autowired
	private UserService service;

	public JwtTokenUtil() {
	}

	public String getUsernameFromToken(String token) {
		String username;

		try {
			username = getClaimsFromToken(token).getSubject();
		}catch (Exception e) {
			username = null;
		}
		return username;
	}

	private Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			expiration = getClaimsFromToken(token).getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();

			return claims;

		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private boolean isTokenExpired(String token) {
		return !getExpirationDateFromToken(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {

		Map<String,Object> claims = new HashMap<>();

		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());

		Long teacherId = this.service.getTeacherIdByUsername(userDetails.getUsername());
		if(teacherId != null) {
			claims.put(CLAIM_KEY_TEACHER,teacherId );
		}

		return doGenerateToken(claims);
	}

	private String doGenerateToken(Map<String,Object> claims) {

		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public boolean canTokenBeRefreshed(String token) {
		return (isTokenExpired(token));
	}


	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = doGenerateToken(claims);

		} catch (Exception e) {
			refreshedToken = null;
		}

		return refreshedToken;
	}

	public Boolean validaToken(String token, UserDetails userDetails){

		JwtUser jwtUser = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return ( username.equals(jwtUser.getUsername()) && isTokenExpired(token));
	}

	public Integer getTeacherIdFromToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		return (int) this.getClaimsFromToken(token).get("teacherId");
	}
}
