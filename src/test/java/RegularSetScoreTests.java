import com.play.scoreboard.match.score.RegularSetScore;
import com.play.scoreboard.match.score.State;
import com.play.scoreboard.match.score.TiebreakGameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegularSetScoreTests {

    @Test
    public void sixGamesWinSetTest() {
        RegularSetScore score = new RegularSetScore();
        //games is 5:0
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(State.ONGOING, winGame(score, 0));
        }
        //games is 6:0
        Assertions.assertEquals(State.PLAYER_ONE_WON, winGame(score, 0));
    }

    @Test
    public void sevenGamesWinSetTest() {
        RegularSetScore score = new RegularSetScore();

        //score is 5:5
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(State.ONGOING, winGame(score, 0));
            Assertions.assertEquals(State.ONGOING, winGame(score, 1));
        }
        //score is 6:5
        Assertions.assertEquals(State.ONGOING, winGame(score, 0));
        //score is 7:5
        Assertions.assertEquals(State.PLAYER_ONE_WON, winGame(score, 0));
    }

    @Test
    public void sevenTiebreakTest() {
        RegularSetScore score = new RegularSetScore();
        //games is 6:6
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(State.ONGOING, winGame(score, 0));
            Assertions.assertEquals(State.ONGOING, winGame(score, 1));
        }
        //points in tiebreak is 6:0
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(State.ONGOING, score.pointWon(0));
        }
        //points in tiebreak is 7:0
        Assertions.assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }

    @Test
    public void twoPointsDifferenceTiebreakTest() {
        RegularSetScore score = new RegularSetScore();
        //games is 6:6
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(State.ONGOING, winGame(score, 0));
            Assertions.assertEquals(State.ONGOING, winGame(score, 1));
        }
        //points in tiebreak is 7:7
        for (int i = 0; i < 7; i++) {
            Assertions.assertEquals(State.ONGOING, score.pointWon(0));
            Assertions.assertEquals(State.ONGOING, score.pointWon(1));
        }
        //points in tiebreak is 8:7
        Assertions.assertEquals(State.ONGOING, score.pointWon(0));
        //points in tiebreak is 9:7
        Assertions.assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }

    public State winGame(RegularSetScore score, int playerNumber) {
        State setState = null;
        for (int i = 0; i < 4; i++) {
            setState = score.pointWon(playerNumber);
        }
        return setState;
    }
}
