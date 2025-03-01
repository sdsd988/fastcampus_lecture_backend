package org.user.application;

import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.user.repository.FakeUserRepository;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @DisplayName("유저 생성 테스트")
    @Test
    void test() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals("test",userInfo.getName());

    }
}
