package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;


public class Post {

    /**
     * 사용자 객체를 받는 방식과, 식별키만 받는 방법의 차이에 대해
     */
    private Long id;
    private final User author;
//    private final Long authorId;
    private final PostContent content;

    public Post(Long id, User author, PostContent content) {
        if(author == null){
            throw new IllegalArgumentException("author is null");
        }
        this.id = id;
        this.author = author;
//        this.authorId = author.getId();
        this.content = content;
    }
}
