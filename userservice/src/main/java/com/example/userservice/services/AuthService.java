package com.example.userservice.services;

import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Session;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import com.example.userservice.repositories.SessionRepository;
import com.example.userservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;
    @Autowired
    public AuthService(UserRepository userRepository, SessionRepository sessionRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.sessionRepository=sessionRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        secretKey = Jwts.SIG.HS256.key().build();
    }
    public ResponseEntity<UserDto> login(String email, String password){
        Optional<User> user= userRepository.findByEmail(email);
        if(user.isEmpty()) throw new RuntimeException("User not exist with this email");
        User user1= user.get();
        if(!bCryptPasswordEncoder.matches(password, user1.getPassword())){
            throw new IllegalArgumentException("password is incorrect");
        }
//        if(!password.matches(user1.getPassword())){
//            throw new IllegalArgumentException("password is incorrect");
//        }
//        String token=generateRandomString(10);
        Date now=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE,30);
        Date expiryat= calendar.getTime();

        Map<String,Object> map=new HashMap<>();
        map.put("email",email);
        map.put("createdAt",now);
        map.put("expiryat",expiryat);

        String jwt=Jwts.builder().claims(map).signWith(secretKey).compact();


        SessionStatus sessionStatus=SessionStatus.ACTIVE;

        Session session=new Session();
        session.setToken(jwt);
        session.setExpiryAt(expiryat);
        session.setUser(user1);
        session.setSessionStatus(sessionStatus);
        sessionRepository.save(session);
        UserDto userDto=UserDto.from(user1);
        MultiValueMap<String,String> headers=new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.COOKIE,"auth-token="+jwt);
        return new ResponseEntity<>(userDto,headers, HttpStatusCode.valueOf(200));

    }

    public void logout(Long user_id,String token){
        Optional<Session> sessionOptional=sessionRepository.findByUseridAndToken(user_id,token);
        if(sessionOptional.isEmpty())
            throw new IllegalArgumentException("Invalid userid or token,or no session exist for this combination");
        Session session=sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);

    }
    public UserDto signup(String email, String password){
        User user=new User();
        user.setEmail(email);
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);
        User saveduser=userRepository.save(user);
        return UserDto.from(saveduser);
    }
    public SessionStatus validate(Long user_id,String token){
        Optional<Session> sessionOptional=sessionRepository.findByUseridAndToken(user_id,token);
        if(sessionOptional.isEmpty())
            throw new IllegalArgumentException("Invalid userid or token,or no session exist for this combination");
        Session session= sessionOptional.get();
        Jws<Claims> jws;
        try {
            jws=Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            }
        catch (SignatureException signatureException){
            session.setSessionStatus(SessionStatus.ENDED);
            sessionRepository.save(session);
            return SessionStatus.ENDED;
        }

            String email=(String) jws.getPayload().get("email");
//            Date expiryAt=(Date) jws.getPayload().get("expiryat");


        Date expiryAt=session.getExpiryAt();
        Date now=new Date();
        if(expiryAt.before(now) || expiryAt.equals(now)){
            session.setSessionStatus(SessionStatus.ENDED);
            sessionRepository.save(session);
            return SessionStatus.ENDED;
        } else if (expiryAt.after(now)) {
            if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)){
                return SessionStatus.ENDED;
            }
        }
        return SessionStatus.ACTIVE;
    }
    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
