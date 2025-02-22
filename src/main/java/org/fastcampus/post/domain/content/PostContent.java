package org.fastcampus.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_CONTENT_LENGTH = 5;
    private static final int MAX_CONTENT_LENGTH = 500;



    public PostContent(String content) {
        super(content);
    }

    @Override
    void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException("contentText is null or empty");
        }

        if(contentText.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException("contentText is too long");
        }

        if(contentText.length() < MIN_CONTENT_LENGTH) {
            throw new IllegalArgumentException("contentText is too short");
        }
    }


}
