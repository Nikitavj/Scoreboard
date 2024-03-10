import com.play.scoreboard.match.score.RegularGameScore;
import com.play.scoreboard.match.score.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegularGameScoreTests {

    @Test
    public void fourPointsWinGameTest() {
        RegularGameScore score = new RegularGameScore();

        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(State.ONGOING, score.pointWon(0));
        }

        Assertions.assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }

    @Test
    public void deucePointsWinGameTest() {
        RegularGameScore score = new RegularGameScore();

        //score is 40:40
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(State.ONGOING, score.pointWon(0));
            Assertions.assertEquals(State.ONGOING, score.pointWon(1));
        }

        //score is ADVANTAGE:40
        Assertions.assertEquals(State.ONGOING, score.pointWon(0));
        //score is 40:40
        Assertions.assertEquals(State.ONGOING, score.pointWon(1));
        //score is ADVANTAGE:40
        Assertions.assertEquals(State.ONGOING, score.pointWon(0));
        Assertions.assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }
}
