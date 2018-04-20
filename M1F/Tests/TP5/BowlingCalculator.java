package fr.rouen.mastergil.TDD;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by rudy on 12/03/17.
 */
public class BowlingCalculator {
    //See http://warwickbowling.50webs.com/calculator.html

    public int score(String feuilleDeScore) {
        final List<Frame> frames = extractFrameFromString(feuilleDeScore);
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);

            // Calcul du score de base (sans influence spare/strike)
            score += frame.getS1int();
            if (i == frames.size() - 1 && frame.isStrike()) {
                score += frame.getScore(frame.getS2());
            } else {
                score += frame.getS2int();
            }
            score += frame.getS3int();

            // Influence spare/strike
            if (i < frames.size() - 1) {
                Frame next = frames.get(i + 1);

                // Bonus du score par spare (+ valeur du prochain symbole)
                if (frame.isSpare()) {
                    score += next.getScore(next.getS1());
                }

                // Bonus du score par spare (+ valeur des 2 prochains symboles)
                if (frame.isStrike()) {
                    /*
                     * Si la prochaine frame contient un strike, le 2e symbole
                     * est dans la frame d'après
                     */
                    if (next.isStrike() && i < frames.size() - 2) {
                        // On prend la frame contenant le 2e symbole
                        Frame afterNext = frames.get(i + 2);

                        score += next.getScore(next.getS1());
                        score += afterNext.getScore(afterNext.getS1());

                    /*
                     * Sinon, il faut différencier le cas particulier de
                     * la dernière frame
                     */
                    } else if (i < frames.size() - 1) {
                        score += next.getS1int();
                        score += next.getScore(next.getS2());

                    } else {
                        score += next.getS1int();
                        score += next.getS2int();
                    }
                }
            }
        }
        return score;
    }


    List<Frame> extractFrameFromString(String feuilleDeScore) {
        final List<Frame> frames = new ArrayList<Frame>();
        final String[] split = feuilleDeScore.split(";");
        for (int i = 0; i < split.length; i++) {
            frames.add(new Frame(split[i]));
        }
        return frames;
    }


}