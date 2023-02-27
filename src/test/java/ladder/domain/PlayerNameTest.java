package ladder.domain;


import static ladder.domain.PlayerName.INVALID_NAME_LENGTH_MESSAGE;
import static ladder.domain.PlayerName.RESERVED_NAME_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerNameTest {

    @ParameterizedTest(name = "이름이 NULL, 빈값, 6자 이상인 경우 예외를 던진다. 입력값: \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"우아한형제들"})
    void 이름이_NULL_빈값_6자_이상인_경우_예외를_던진다(final String name) {
        assertThatThrownBy(() -> new PlayerName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_NAME_LENGTH_MESSAGE);
    }

    @Test
    void 이름이_정상적으로_생성한다() {
        final PlayerName name = new PlayerName("name");

        assertThat(name.getValue()).isEqualTo("name");
    }

    @ParameterizedTest(name = "입력받은 이름과 같은 이름인지 확인한다. 이름: name, 입력받은이름: {0}, 결과 {1}")
    @CsvSource({"name,true", "notSameName,false"})
    void 입력받은_이름과_같은_이름인지_확인한다(final String name, final boolean result) {
        final PlayerName playerName = new PlayerName("name");

        assertThat(playerName.isSameAs(name)).isEqualTo(result);
    }

    @Test
    void all을_이름으로_사용할_수_없다() {
        assertThatThrownBy(() -> new PlayerName("all"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(RESERVED_NAME_MESSAGE);
    }
}
