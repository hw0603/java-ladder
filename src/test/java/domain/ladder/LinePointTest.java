package domain.ladder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LinePointTest {

    private static final int MIN_NUMBER_TO_GENERATE_PASSABLE = 4;

    @DisplayName("객체 생성시 4 이상의 값을 인자로 전달하면 PASSABLE을 생성한다")
    @Test
    void create_PASSABLE() {
        assertThat(LineBridge.from(MIN_NUMBER_TO_GENERATE_PASSABLE))
                .isEqualTo(LineBridge.PASSABLE);
    }

    @DisplayName("객체 생성시 3 미만의 값을 인자로 전달하면 BLOCKED를 생성한다")
    @Test
    void create_BLOCKED() {
        assertThat(LineBridge.from(MIN_NUMBER_TO_GENERATE_PASSABLE-1))
                .isEqualTo(LineBridge.BLOCKED);
    }
}