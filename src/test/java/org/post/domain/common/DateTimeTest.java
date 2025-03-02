package org.post.domain.common;

import org.fastcampus.post.domain.common.DateTimeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeTest {

    @DisplayName("")
    @Test
    void test() {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime dateTime = dateTimeInfo.getDateTime();

        //when
        dateTimeInfo.updatedEditDateTime();

        //then
        assertTrue(dateTimeInfo.isIsEdited());
        assertNotEquals(dateTime, dateTimeInfo.getDateTime());
    }
}
