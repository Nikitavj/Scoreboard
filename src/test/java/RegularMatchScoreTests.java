import com.play.scoreboard.match.score.RegularMatchScore;
import com.play.scoreboard.match.score.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegularMatchScoreTests {

    @Test
    public void twoSetsWinMatchTest() {
        RegularMatchScore score = new RegularMatchScore();
        //score is 1:0
        Assertions.assertEquals(State.ONGOING, score.setWon(0));
        //score is 2:0
        Assertions.assertEquals(State.PLAYER_ONE_WON, score.setWon(0));
    }
}
