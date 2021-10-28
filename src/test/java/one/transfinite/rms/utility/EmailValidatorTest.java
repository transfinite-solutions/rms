package one.transfinite.rms.utility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailValidator.class})
@ExtendWith(SpringExtension.class)
class EmailValidatorTest {
    @Autowired
    private EmailValidator emailValidator;

    @Test
    void testTest() {
        assertTrue(this.emailValidator.test("jane.doe@example.org"));
        assertFalse(this.emailValidator.test("Email"));
    }
}

