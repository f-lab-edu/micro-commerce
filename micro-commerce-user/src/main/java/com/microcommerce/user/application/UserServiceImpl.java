package com.microcommerce.user.application;

import com.microcommerce.user.application.exception.UserException;
import com.microcommerce.user.application.exception.UserExceptionCode;
import com.microcommerce.user.domain.entity.User;
import com.microcommerce.user.domain.repository.UserRepository;
import com.microcommerce.user.dto.req.SignInReqDto;
import com.microcommerce.user.dto.req.SignUpReqDto;
import com.microcommerce.user.dto.res.SignInResDto;
import com.microcommerce.user.dto.res.SignUpResDto;
import com.microcommerce.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public SignUpResDto signUp(final SignUpReqDto body) {
        if (userRepository.existsByEmailAndType(body.email(), body.type())) {
            throw new UserException(UserExceptionCode.EXISTS_USER);
        }

        final String hashedPassword = passwordEncoder.encode(body.password());
        final User newUser = userRepository.save(
                User.getInstance(body.email(), hashedPassword, body.name(), body.phoneNumber(), body.type())
        );

        return SignUpResDto.getInstance(newUser.getEmail());
    }

    @Override
    public SignInResDto signIn(final SignInReqDto body) {
        final Optional<User> oUser = userRepository.findByEmailAndType(body.email(), body.type());
        if (oUser.isEmpty() || !passwordEncoder.matches(body.password(), oUser.get().getPassword())) {
            throw new UserException(UserExceptionCode.INVALID_USER_INFO);
        }

        final String accessToken = jwtUtil.createToken(oUser.get().getId().toString(), Map.of("email", oUser.get().getEmail()));
        return SignInResDto.getInstance(accessToken);
    }

}
