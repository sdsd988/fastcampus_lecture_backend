package org.fastcampus.user.domain;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;

import java.util.Objects;


@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;

    private UserInfo info;

    private PositiveIntegerCounter followingCounter;

    private PositiveIntegerCounter followerCounter;


    public User(Long id, UserInfo info) {
        if(info == null) {
            throw new IllegalArgumentException("info cannot be null");
        }
        this.id = id;
        this.info = info;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCounter.increase();
        targetUser.increaseFollowerCounter();
    }

    public void unfollow(User targetUser) {
        if(this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCounter.decrease();
        targetUser.decreaseFollowerCounter();
    }

    private void increaseFollowerCounter() {
        followerCounter.increase();
    }

    private void decreaseFollowerCounter() {
        followerCounter.decrease();
    }

    public int getFollowingCounter() {
        return followingCounter.getCount();
    }

    public int getFollowerCounter() {
        return followerCounter.getCount();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getProfileImage() {
        return info.getProfileImageUrl();
    }

    public String getName() {
        return info.getName();
    }
}
