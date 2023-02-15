package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.controller.form.UserCreateFrom;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.respository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        user.getUserName();
        user.getEmail();
        user.getPassword();
        user.getUserPhone();

//        user.setUserName(userName);
//        user.setEmail(email);
//        user.setUserPhonenum(phoneNum);
//        user.setPassword(password);
        // 사용자 비밀번호 암호화. (시큐리티 BCryptPasswordEncoder/ 빈등록 객체)
//        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }
}


