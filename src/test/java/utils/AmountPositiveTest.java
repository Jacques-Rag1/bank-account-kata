package utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class AmountPositiveTest {
    @Test
    public void amount_value_cannot_be_negative(){
       assertThatIllegalArgumentException().isThrownBy(() -> AmountPositive.of(-1));
    }
}
