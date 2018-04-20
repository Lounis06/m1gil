package fr.rouen.mastergil.TDD;

/**
 * Created by rudy on 12/03/17.
 */
public class Frame {

    private Character s1;
    private Character s2;
    private Character s3;

    public Frame(String score) {
        if (score != null) {
            if (score.length() > 0) {
                s1 = score.charAt(0);
            }
            if (score.length() > 1) {
                s2 = score.charAt(1);
            }
            if (score.length() > 2) {
                s3 = score.charAt(2);
            }
        }
    }

    public boolean isStrike() {
        return new Character('x').equals(s1);
    }

    public boolean isSpare() {
        return new Character('/').equals(s2);
    }

    public Character getS1() {
        return s1;
    }

    public Character getS2() {
        return s2;
    }

    public int getS1int() {
        if (isStrike()) {
            return 10;
        }
        if (isSpare()) {
            return 0;
        }
        return getScore(s1);
    }

    public int getS2int() {
        if (isStrike()) {
            return 0;
        }
        if (isSpare()) {
            return 10;
        }
        return getScore(s2);
    }

    public int getS3int() {
        return getScore(s3);
    }

    public int getScore(Character score) {
        if (null == score || score.charValue() == '-') {
            return 0;
        }
        if (score.charValue() == 'x' || score.charValue() == '/') {
            return 10;
        }
        return score.charValue() - new Character('0').charValue();
    }
}
