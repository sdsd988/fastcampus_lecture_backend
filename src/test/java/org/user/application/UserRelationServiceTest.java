package org.user.application;

import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.user.repository.FakeUserRelationRepository;
import org.user.repository.FakeUserRepository;

import javax.management.relation.RelationService;

import static org.junit.jupiter.api.Assertions.*;

public class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository relationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, relationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @DisplayName("팔로잉/팔로워 증가 테스트")
    @Test
    void test() {
        //given
        userRelationService.follow(requestDto);


        //then
        assertEquals(1, user1.getFollowingCounter());
        assertEquals(1, user2.getFollowerCounter());
    }


    @DisplayName("")
    @Test
    void test2() {
        //given
        userRelationService.follow(requestDto);

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));

    }

    @DisplayName("")
    @Test
    void test3() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));

    }

    @DisplayName("팔로잉/팔로워 감소 테스트")
    @Test
    void test4() {
        //given
        userRelationService.follow(requestDto);


        //then
        userRelationService.unfollow(requestDto);
        //then
        assertEquals(0, user1.getFollowingCounter());
        assertEquals(0, user2.getFollowerCounter());
    }


    @DisplayName("")
    @Test
    void test5() {


        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));

    }

    @DisplayName("")
    @Test
    void test6() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));

    }


}
