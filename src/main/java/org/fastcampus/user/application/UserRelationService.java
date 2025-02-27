package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService, UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User target = userService.getUser(dto.targetUserId());

        if(userRelationRepository.isAlreadyFollow(user, target)) {
            throw new IllegalArgumentException();
        }

        user.follow(target);
        userRelationRepository.save(user, target);
    }

    public void unfollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User target = userService.getUser(dto.targetUserId());

        if(!userRelationRepository.isAlreadyFollow(user, target)) {
            throw new IllegalArgumentException();
        }

        user.follow(target);
        userRelationRepository.save(user, target);
    }
}
