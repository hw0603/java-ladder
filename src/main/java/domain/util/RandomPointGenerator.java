package domain.util;

import domain.Ladder.Point;

import java.util.Random;

public class RandomPointGenerator implements PointGenerator {
    @Override
    public Point generate() {
        boolean point = new Random().nextBoolean();
        return Point.from(point);
    }
}