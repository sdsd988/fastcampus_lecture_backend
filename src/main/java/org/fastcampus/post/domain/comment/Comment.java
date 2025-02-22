package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;

    public Comment(Long id, Post post, User author, Content content) {
        if(author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }

        if (post == null) {
            throw new IllegalArgumentException("post cannot be null");
        }

        if(content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }


        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
    }
}
