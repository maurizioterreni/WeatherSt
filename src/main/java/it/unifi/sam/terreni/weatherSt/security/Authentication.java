package it.unifi.sam.terreni.weatherSt.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.unifi.sam.terreni.weatherSt.model.user.User;
import it.unifi.sam.terreni.weatherSt.model.user.UserPropertie;
import it.unifi.sam.terreni.weatherSt.model.user.UserRole;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;   

public class Authentication {

	private static final long EXPIRATIONTIME = 7200000;//2 ore
	private static final String SECRET = "f263e012-3fba-11e8-b467-0ed5f89f718b";

	public static String generateToken(User user) {
		return Jwts.builder()
				.setSubject(getSubjectFromUser(user))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}

	public static final boolean rightRoleFromToken(String token, UserRole userRole) {
		List<UserRole> userRoles = new ArrayList<>();
		userRoles.add(userRole);
		return rightRoleFromToken(token, userRoles);
	}

	public static final boolean rightRoleFromToken(String token, List<UserRole> userRoles) {
		if(StringUtils.isEmpty(token))
			return false;

		String subject = "";
		try {
			subject = getSubjectFromToken(token);
		}catch (Exception e) {
			return false;
		}
		if(StringUtils.isEmpty(subject))
			return false;

		User user = getUserFromSubject(subject);
		if(userRoles.contains(user.getPropertie().getUserRole()))
			return true;
		return false;
	}

	private static String getSubjectFromToken(String token) throws Exception{
		return Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	private static String getSubjectFromUser(User user) {
		return user.getId() + "#" + user.getUsername() + "#" + user.getPropertie().getUserRole().getName();
	}

	public static boolean isValid(String token) {
		String subject = "";
		try {
			subject = getSubjectFromToken(token);
		}catch (Exception e) {
			return false;
		}
		if(StringUtils.isEmpty(subject))
			return false;

		return true;
	}

	public static boolean isNotValid(String token) {
		return !isValid(token);
	}

	private static User getUserFromSubject(String subject) {
		String[] str = subject.split("#");

		User user = new User("");
		UserPropertie propertie = new UserPropertie("");

		propertie.setUserRole(UserRole.userRoleFromStr(str[2]));

		user.setId(new Long(str[0]));
		user.setUsername(str[1]);
		user.setPropertie(propertie);

		return user;
	}
}
