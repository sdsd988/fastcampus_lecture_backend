package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;


public class Post {

    /**
     * 사용자 객체를 받는 방식과, 식별키만 받는 방법의 차이에 대해
     */
    private Long id;
    private final User author;
//    private final Long authorId;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content,PostPublicationState state) {
        if(author == null){
            throw new IllegalArgumentException("author is null");
        }
        this.id = id;
        this.author = author;
//        this.authorId = author.getId();
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like(User user) {
        if(this.author.equals(user)){
            throw new IllegalArgumentException("user already like this post");
        }
        likeCount.increase();
    }

    public void unlike() {
        this.likeCount.decrease();
    }

    public void updatePost(User user, String updateContent,PostPublicationState state) {
        if(!this.author.equals(user)){
            throw new IllegalArgumentException("user not like this post");
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }
}
