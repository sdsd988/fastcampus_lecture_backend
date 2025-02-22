package org.fastcampus.user.domain;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;
    private final UserRelationCounter followingCounter;
    private final UserRelationCounter followerCounter;


    public User(Long id, UserInfo info) {
        this.id = id;
        this.info = info;
        this.followingCounter = new UserRelationCounter();
        this.followerCounter = new UserRelationCounter();
    }

    public void follow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followerCounter.increase();
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
}
