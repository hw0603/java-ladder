package ladder.controller;

import ladder.domain.generator.BooleanGenerator;
import ladder.domain.ladder.Height;
import ladder.domain.ladder.Ladder;
import ladder.domain.player.Player;
import ladder.domain.player.Players;
import ladder.domain.reward.GameResult;
import ladder.domain.reward.Reward;
import ladder.domain.reward.Rewards;
import ladder.view.Input;
import ladder.view.Result;

import java.util.Map;

public class LadderController {

    private static final String CONTINUE = "Y";

    private final Input input;
    private final Result result;
    private final BooleanGenerator booleanGenerator;
    private final ExceptionProcess exceptionProcess;

    public LadderController(Input input, Result result, BooleanGenerator booleanGenerator) {
        this.input = input;
        this.result = result;
        this.booleanGenerator = booleanGenerator;
        this.exceptionProcess = new ExceptionProcess(result);
    }

    public void run() {
        Players players = exceptionProcess.repeat(input::inputPlayerNames, Players::create);
        Rewards rewards = exceptionProcess
                .repeat(input::inputRewards, inputRewards -> Rewards.create(inputRewards, players.count()));
        Height heightOfLadder = exceptionProcess.repeat(input::inputHeightOfLadder, Height::new);
        Ladder ladder = Ladder.create(players.count(), heightOfLadder.getHeight(), booleanGenerator);

        result.printLadder(players, ladder, rewards);

        calculateGameResult(players, ladder, rewards);
    }

    private void calculateGameResult(Players players, Ladder ladder, Rewards rewards) {
        GameResult gameResult = GameResult.create(players, ladder, rewards);
        String printContinue = CONTINUE;
        while (printContinue.equals(CONTINUE)) {
            Map<Player, Reward> resultByPlayers = exceptionProcess
                    .repeat(input::inputTargetPlayerNames, gameResult::findResultByPlayers);

            result.printGameResult(resultByPlayers);

            printContinue = exceptionProcess.repeat(input::inputContinue, inputContinue -> inputContinue);
        }
    }

}