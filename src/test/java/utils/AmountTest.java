package utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class AmountTest {
    @Test
    public void amount_value_cannot_be_negative(){
       assertThatIllegalArgumentException().isThrownBy(() -> Amount.createAmount(-1));
    }
}
