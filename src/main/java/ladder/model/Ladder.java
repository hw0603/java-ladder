package ladder.model;

import java.util.Collections;
import java.util.stream.IntStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ladder {
    private static final Random random = new Random();
    private final List<Line> ladder;

    private Ladder(List<Line> ladder) {
        this.ladder = ladder;
    }

    public static Ladder of(int height, int width) {
        validate(height, width);

        List<Line> ladder = IntStream.range(0, height)
                .mapToObj(idx -> new Line(makeRandomRow(width)))
                .toList();

        return new Ladder(ladder);
    }

    private static void validate(int height, int width) {
        if (notNaturalNumber(height) || notNaturalNumber(width)) {
            throw new IllegalArgumentException("사다리 높이와 너비는 자연수여야 합니다.");
        }
    }

    private static boolean notNaturalNumber(int value) {
        return value <= 0;
    }

    private static List<LadderPath> makeRandomRow(int width) {
        List<LadderPath> randomPath = new ArrayList<>(generatePairableRandomPath(width));
        fillPathIfNotEnough(randomPath, width);

        return randomPath;
    }

    private static List<LadderPath> generatePairableRandomPath(int maxWidth) {
        List<LadderPath> randomPathWithPair = new ArrayList<>();

        while (randomPathWithPair.size() < maxWidth - 1) {
            randomPathWithPair.addAll(generateRandomPath());
        }

        return randomPathWithPair;
    }

    private static List<LadderPath> generateRandomPath() {
        boolean isConnectedPath = random.nextBoolean();

        if (isConnectedPath) {
            return List.of(LadderPath.RIGHT, LadderPath.LEFT);
        }
        return List.of(LadderPath.STAY);
    }

    private static void fillPathIfNotEnough(List<LadderPath> createdPath, int width) {
        if (createdPath.size() < width) {
            createdPath.add(LadderPath.STAY);
        }
    }

    public List<Line> getLadder() {
        return Collections.unmodifiableList(ladder);
    }

    public int getWidth() {
        return ladder.get(0).size();
    }
}
