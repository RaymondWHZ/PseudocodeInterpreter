package core.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenizerTest {

    Tokenizer tokenizer = new Tokenizer("FUNCTION sqrt(x: INTEGER) RETURNS INTEGER\nOUTPUT x\nRETURN x");

    @BeforeEach
    void setup() {
        tokenizer.reset();
    }

    @Test
    void integrated() {
        assert tokenizer.nextName().equals("FUNCTION");
        assert tokenizer.getCurrentCharNumber() == 9;
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
        assert tokenizer.nextName().equals("OUTPUT");
        assert tokenizer.getCurrentLine() == 2;
        assert tokenizer.getCurrentCharNumber() == 7;
        assert tokenizer.skip(" ");
        assert tokenizer.nextName().equals("x");
        assert tokenizer.getCurrentLine() == 2;
        assert tokenizer.nextName().equals("RETURN");
        assert tokenizer.skip(" ");
        assert tokenizer.nextName().equals("x");
        assert tokenizer.getCurrentCharNumber() == 9;

        tokenizer.goBack(20);
        assert tokenizer.getCurrentLine() == 1;

        tokenizer.reset();

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
        assert tokenizer.nextName().equals("OUTPUT");
        assert tokenizer.getCurrentLine() == 2;
        assert tokenizer.skip(" ");
        assert tokenizer.nextName().equals("x");
        assert tokenizer.getCurrentLine() == 2;
        assert tokenizer.nextName().equals("RETURN");
        assert tokenizer.skip(" ");
        assert tokenizer.nextName().equals("x");
    }
}