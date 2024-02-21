package ladder.model;

import ladder.utils.BooleanGenerator;
import ladder.utils.FixedBooleanGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LineTest {
    @Test
    @DisplayName("지정된 수 만큼의 길이를 갖는 한 행이 생성된다.")
    void createLineByLengthTest() {
        BooleanGenerator bg = new FixedBooleanGenerator(true);

        Line line = Line.of(5, bg);

        int actual = line.size();
        int expected = 5;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("동일한 가로줄에는 연속된 경로가 없다.")
    void continuousPathNotExistInSameLine() {
        Line line = Line.of(5, new FixedBooleanGenerator(true));

        List<Boolean> actual = line.getRow();
        List<Boolean> expected = List.of(true, false, true, false, true);

        assertThat(actual).isEqualTo(expected);
    }
}
