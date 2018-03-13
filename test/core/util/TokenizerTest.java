package core.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenizerTest {

    Tokenizer tokenizer = new Tokenizer("FUNCTION sqrt(x: INTEGER) RETURNS INTEGER");

    @BeforeEach
    void setup() {
        tokenizer.reset();
    }

    @Test
    void integrated() {
        assert tokenizer.nextName().equals("FUNCTION");
        assert tokenizer.skip(" ");
        assert tokenizer.nextName().equals("sqrt");
        assert tokenizer.skip("(");
        assert tokenizer.nextChar() == 'x';
        assert tokenizer.nextName().equals("");
        assert tokenizer.skip(":");
        assert tokenizer.nextName().equals("INTEGER");
        assert !tokenizer.skip(",");
        assert tokenizer.skip(") RETURNS");
        assert tokenizer.nextName().equals("INTEGER");
    }
}