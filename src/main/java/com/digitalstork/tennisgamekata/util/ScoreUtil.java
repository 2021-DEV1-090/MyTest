package com.digitalstork.tennisgamekata.util;

public class ScoreUtil {

    private ScoreUtil() {
    }

    public static String translateScore(int score, int otherScore) {
        if (score <= 3) {
            switch (score) {
                case 0:
                    return "0";
                case 1:
                    return "15";
                case 2:
                    return "30";
                case 3:
                    return "40";
                default:
                    return null;
            }
        } else {
            if (score == otherScore || score + 1 == otherScore || score + 2 == otherScore) return "40";
            else {
                if (score == otherScore + 1) return "Advantage";
                if (score == otherScore + 2) return "Won";
            }
        }
        return null;
    }
}
