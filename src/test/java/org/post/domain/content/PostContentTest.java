package org.post.domain.content;

import org.fastcampus.post.domain.content.PostContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostContentTest {

    @DisplayName("")
    @Test
    void test() {
        //given
        String test = "this is a Test";


        //when
        PostContent postContent = new PostContent(test);
        //then
        assertEquals(postContent.getContentText(), test);

    }

    @DisplayName("")
    @Test
    void test2() {
        //given
        String content = "a".repeat(501);
        //when


        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @DisplayName("")
    @Test
    void test3() {
        //given

        //when

        //then

    }
}
