package org.fastcampus.common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositiveIntegerCountTest {

    @DisplayName("")
    @Test
    void test() {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.increase();

        //then
        assertEquals(1, counter.getCount());
    }

    @DisplayName("")
    @Test
    void test2() {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();
        //when
        counter.decrease();
        //then
        assertEquals(0,counter.getCount());

    }
}
