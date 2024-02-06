import com.play.scoreboard.models.MatchScoreModel;
import com.play.scoreboard.models.Player;
import com.play.scoreboard.servise.MatchScoreCalculationServise;
import com.play.scoreboard.servise.OngoingMatchesServise;
import com.play.scoreboard.servise.ScoreGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MatchScoreCalculationServiseTest {
    Player player1 = new Player();
    Player player2 = new Player();
    OngoingMatchesServise ongServise = new OngoingMatchesServise();
    MatchScoreCalculationServise calcServise = new MatchScoreCalculationServise();
    MatchScoreModel match;
    ScoreGame score;

    @Test
    @BeforeEach
    @DisplayName("Initial Data")
    void initData() {
        player1.setId(1);
        player2.setId(2);
        match = ongServise.startNewMatch(player1, player2);
        score = match.getScore();

        System.out.println("init");
    }


    @ParameterizedTest(name = "Test calculate points in game{index}")
    @CsvSource(value = {
            "4, 0",
            "4, 2"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testGame(int p1, int p2) {
        int gamesPlayer1 = score.getGames(player1);

        for (int i = 0; i < p1; i++){
            score.addPoint(player1);
        }
        for (int i = 0; i < p2; i++){
            score.addPoint(player2);
        }
        calcServise.calculate(score, player1);
        int currentGamesPlayer1 = score.getGames(player1);

        Assertions.assertTrue(
                (currentGamesPlayer1 - gamesPlayer1) == 1,
                () -> "Неверный подстчет points");
    }

    @Test
    @DisplayName("Test start РОВНО 3:3")
    void testGameStartEqually() {
        int pointsPlayers = 3;
        for (int i = 0; i < pointsPlayers; i++){
            score.addPoint(player1);
            score.addPoint(player2);
        }
        calcServise.calculate(score, player1);

        Assertions.assertTrue(
                score.getEqually(),
                () -> "Неверное объявление РОВНО");
    }

    @Test
    @DisplayName("Test calculate for РОВНО")
    void testGameEqually() {
        score.startEqually();
        final int POINT_PLAYER = 2;
        int gamesPlayer = score.getGames(player1);
        for (int i = 0; i < POINT_PLAYER; i++){
            score.addPoint(player1);
        }
        calcServise.calculate(score, player1);

        Assertions.assertTrue(
                score.getGames(player1) == ++gamesPlayer,
                () -> "Неверный подсчет points in РОВНО");
    }

    @ParameterizedTest(name = "Test calculate games in set {index}")
    @CsvSource(value = {
            "6, 0",
            "6, 4",
            "7, 5"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testWinnSet(int p1, int p2) {
        int setsPlayer1 = score.getSets(player1);
        for (int i = 0; i < p1; i++) {
            score.addGame(player1);
        }
        for (int i = 0; i < p2; i++) {
            score.addGame(player2);
        }
        calcServise.calculate(score, player1);

        Assertions.assertTrue(
                score.getSets(player1) == ++setsPlayer1,
                () -> "Неверный calculate games in set");
    }

    @Test
    @DisplayName("Test start Tai-Breake games 6:6")
    void testTaiBreak() {
        final int GAMES_PLAYERS = 6;
        for (int i = 0; i < GAMES_PLAYERS; i++){
            score.addGame(player1);
            score.addGame(player2);
        }
        calcServise.calculate(score, player1);

        Assertions.assertTrue(
                score.getTieBreak(),
                () -> "Не объявляется Tie-Break");
    }

    @ParameterizedTest(name = "Test calculate points in Tie-Breake {index}")
    @CsvSource(value = {
            "7, 0",
            "7, 5",
            "45, 43"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testScoreForTieBreake(int p1, int p2) {
        int gamesPlayer1 = score.getGames(player1);
        for (int i = 0; i < p1; i++) {
            score.addPoint(player1);
        }
        for (int i = 0; i < p2; i++) {
            score.addPoint(player2);
        }
        score.startTieBreak();
        calcServise.calculate(score, player1);

        Assertions.assertTrue(
                score.getGames(player1) == ++gamesPlayer1,
                () -> "Неверный calculate points in Tie-Breake");
    }

    @ParameterizedTest(name = "Test finish game {index}")
    @CsvSource(value = {
            "2, 1",
            "2, 0"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testFinishGame(int p1, int p2) {
        for (int i = 0; i < p1; i++) {
            score.addSet(player1);
        }
        for (int i = 0; i < p2; i++) {
            score.addSet(player2);
        }
        Assertions.assertTrue(
                calcServise.calculate(score, player1),
                () -> "Неверный calculate sets");
    }
}