package ladder;

import ladder.domain.Ladder;
import ladder.domain.generator.RandomLineGenerator;
import ladder.domain.Users;
import ladder.view.InputView;
import ladder.view.OutputView;

public class MainApplication {

    public static void main(String[] args) {

        Users users = inputUsers();
        Ladder ladder = inputLadder(users);
        OutputView.printResult(users, ladder);
    }

    private static Users inputUsers() {

        try {
            return new Users(InputView.inputUserNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputUsers();
        }
    }

    private static Ladder inputLadder(Users users) {

        try {
            return new Ladder(InputView.inputFloorHeight(), users, new RandomLineGenerator());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputLadder(users);
        }
    }
}