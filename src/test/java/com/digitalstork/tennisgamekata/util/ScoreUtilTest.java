package com.digitalstork.tennisgamekata.util;

import com.digitalstork.tennisgamekata.exception.IllegalScoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreUtilTest {

    @Test
    void should_throw_IllegalScoreException_when_bad_scores_provided() {
        // Test Assertions
        IllegalScoreException illegalScoreException = assertThrows(IllegalScoreException.class, () -> {
            String score = ScoreUtil.translateScore(-8, 20);
        });

        assertEquals("Illegal score!", illegalScoreException.getMessage());
    }
}