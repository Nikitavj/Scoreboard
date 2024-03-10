import com.play.scoreboard.match.score.State;
import com.play.scoreboard.match.score.TiebreakGameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TiebreakGameScoreTests {

    @Test
    public void sevenPointWinSetTest () {
        TiebreakGameScore score = new TiebreakGameScore();
        //score is 6:0
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(State.ONGOING, score.pointWon(0));
        }
        //score is 7:0
        Assertions.assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }

    @Test
    public void twoPointsDifferenceWinSetTest () {
        TiebreakGameScore score = new TiebreakGameScore();
        //score is 7:7
        for (int i = 0; i < 7; i++) {
            Assertions.assertEquals(State.ONGOING, score.pointWon(0));
            Assertions.assertEquals(State.ONGOING, score.pointWon(1));
        }
        //score is 8:7
        Assertions.assertEquals(State.ONGOING, score.pointWon(0));
        //score is 9:7
        Assertions.assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }
}
