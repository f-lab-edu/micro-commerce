package com.microcommerce.user.application;

import com.microcommerce.user.application.exception.UserException;
import com.microcommerce.user.application.exception.UserExceptionCode;
import com.microcommerce.user.domain.entity.User;
import com.microcommerce.user.domain.repository.UserRepository;
import com.microcommerce.user.dto.req.SignupReqDto;
import com.microcommerce.user.dto.res.SignupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public SignupResDto signup(final SignupReqDto body) {
        if (userRepository.existsByEmailAndType(body.email(), body.type())) {
            throw new UserException(UserExceptionCode.EXISTS_USER);
        }

        final String hashedPassword = passwordEncoder.encode(body.password());
        final User newUser = userRepository.save(
                User.getInstance(body.email(), hashedPassword, body.name(), body.phoneNumber(), body.type())
        );

        return SignupResDto.builder()
                .email(newUser.getEmail())
                .build();
    }

}
