package org.fastcampus.post.domain.comment;

import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

@Getter
@Builder
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment( Post post, User author,String content, PositiveIntegerCounter likeCount) {
        return new Comment(null, post, author, new CommentContent(content),likeCount);
    }

    public Comment(Long id, Post post, User author, Content content,PositiveIntegerCounter likeCount) {
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
        this.likeCount = new PositiveIntegerCounter();
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

    public void updateComment(User User, String updateContent) {
        if(!this.author.equals(User)) {
            throw new IllegalArgumentException("user not like this post");
        }
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContentText() {
        return content.getContentText();
    }
}
