package hr.fer.oprpp1.custom.scripting.lexer;

import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import hr.fer.oprpp1.hw02.prob1.Lexer;
import hr.fer.oprpp1.hw02.prob1.Token;
import hr.fer.oprpp1.hw02.prob1.TokenType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {
    private String readExample(int n) {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("extra/primjer" + n + ".txt")) {
            if (is == null) throw new RuntimeException("Datoteka extra/primjer" + n + ".txt je nedostupna.");
            byte[] data = is.readAllBytes();
            String text = new String(data, StandardCharsets.UTF_8);
            return text;
        } catch (IOException ex) {
            throw new RuntimeException("Greška pri čitanju datoteke.", ex);
        }
    }
    @Test
    public void primjer1() {
        String text = readExample(1);

      RealLexer lexer = new RealLexer(text);

       RealToken correctData[] = {
                new RealToken(RealTokenType.TEXT, "Ovo je \nsve jedan text node"),
       };

        checkTokenStream(lexer, correctData);
    }

    @Test
    public void primjer2() {
        String text = readExample(2);

        RealLexer lexer = new RealLexer(text);

        RealToken correctData[] = {
                new RealToken(RealTokenType.TEXT, "Ovo je \n" +
                        "sve jedan {$ text node\n"),
        };

        checkTokenStream(lexer, correctData);

    }


    @Test
    public void primjer3() {

        String text = readExample(3);
        RealLexer lexer = new RealLexer(text);

        RealToken correctData[] = {
                new RealToken(RealTokenType.TEXT, "Ovo je \n" +
                        "sve jedan \\{$text node\n"),
        };

        checkTokenStream(lexer, correctData);
    }


    @Test
    public void primjer4() {
        String text = readExample(4);
        RealLexer lexer = new RealLexer(text);

        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());
    }


    @Test
    public void primjer5() {
        String text = readExample(5);
        RealLexer lexer = new RealLexer(text);

        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());
    }


    @Test
    public void primjer6() {

        String text = readExample(6);
        RealLexer lexer = new RealLexer(text);
        assertEquals(lexer.getState(),RealLexerState.TEXT);
        RealToken correctData[] = {
                new RealToken(RealTokenType.TEXT, "Ovo je OK "),
                new RealToken(RealTokenType.OPERATOR,"="),
                new RealToken(RealTokenType.STRING,"String ide\n" +
                        "u više redaka\n" +
                        "čak tri"),
                new RealToken(RealTokenType.EOF,null),
        };

        checkTokenStream(lexer, correctData);
        assertEquals(lexer.getState(),RealLexerState.TEXT);
    }


    @Test
    public void primjer7() {
        String text = readExample(7);
        RealLexer lexer = new RealLexer(text);

        RealToken correctData[] = {
                new RealToken(RealTokenType.TEXT, "Ovo je isto OK "),
                new RealToken(RealTokenType.OPERATOR,"="),
                new RealToken(RealTokenType.STRING,"String ide\n" +
                        "u \\\"više\\\" \\nredaka\n" +
                        "ovdje a stvarno četiri"),
                new RealToken(RealTokenType.EOF,null),
        };

        checkTokenStream(lexer, correctData);
    }


    @Test
    public void primjer8() {
        String text = readExample(8);
        RealLexer lexer = new RealLexer(text);
        lexer.nextToken();
        lexer.nextToken();
        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());
    }


    @Test
    public void primjer9() {
        String text = readExample(9);
        RealLexer lexer = new RealLexer(text);
        lexer.nextToken();
        lexer.nextToken();
        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());
    }

    @Test
    public void primjer10() {
        String text = "{$= i i * @sin \"0.000\" @decfmt $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"="),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.OPERATOR,"*"),
                new RealToken(RealTokenType.FUNCTION, "sin"),
                new RealToken(RealTokenType.STRING,"0.000"),
                new RealToken(RealTokenType.FUNCTION,"decfmt"),
                new RealToken(RealTokenType.EOF,null),
        };

        checkTokenStream(lexer, correctData);
    }
    @Test
    public void primjer11() {
        String text = "{$ FOR i -1 10 1 $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.NUMBER,"-1"),
                new RealToken(RealTokenType.NUMBER, "10"),
                new RealToken(RealTokenType.NUMBER,"1"),
                new RealToken(RealTokenType.EOF,null),
        };

        checkTokenStream(lexer, correctData);
    }

    @Test
    public void primjer12() {
        String text = "{$ FOR sco_re \"-1\"10 \"1\" $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"sco_re"),
                new RealToken(RealTokenType.NUMBER,"-1"),
                new RealToken(RealTokenType.NUMBER, "10"),
                new RealToken(RealTokenType.NUMBER,"1"),
                new RealToken(RealTokenType.EOF,null),
        };
        checkTokenStream(lexer, correctData);
    }
    @Test
    public void primjer13() {
        String text = "{$ FOR year 1 last_year $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"year"),
                new RealToken(RealTokenType.NUMBER,"1"),
                new RealToken(RealTokenType.VARIABLE, "last_year"),
                new RealToken(RealTokenType.EOF,null),
        };
        checkTokenStream(lexer, correctData);
    }
    @Test
    public void primjer14() {
        String text = "{$ FOR 3 1 10 1 $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),

        };
        checkTokenStream(lexer, correctData);
        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());

    }
    @Test
    public void primjer15() {
        String text = "{$ FOR year @sin 10 $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"year"),
        };
        checkTokenStream(lexer, correctData);
        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());

    }
    @Test
    public void primjer16() {
        String text = "{$ FOR year @sin 10 $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"year"),
        };
        checkTokenStream(lexer, correctData);
        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());

    }
    @Test
    public void primjer17() {
        String text = "{$ FOR year 1 10 \"1\" \"10\" $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"year"),
                new RealToken(RealTokenType.NUMBER,"1"),
                new RealToken(RealTokenType.NUMBER, "10"),
                new RealToken(RealTokenType.NUMBER,"1"),
                new RealToken(RealTokenType.NUMBER,"10"),
        };
        checkTokenStream(lexer, correctData);
        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());
    }
    @Test
    public void primjer18() {
        String text = "{$ fOr year $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"year"),

        };
        checkTokenStream(lexer, correctData);
        assertThrows(SmartScriptParserException.class,()->lexer.nextToken());
    }
    @Test
    public void primjer19() {
        String text = " {$ END $}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.TEXT," "),
                new RealToken(RealTokenType.OPERATOR,"END"),
                new RealToken(RealTokenType.EOF,null),

        };
        checkTokenStream(lexer, correctData);

    }
    @Test
    public void FINALNI() {
        String text = " This is sample text.\n" +
                "{$ FOR i 1 10 1 $}\n" +
                " This is {$= i $}-th time this message is generated.\n" +
                "{$END$}\n" +
                "{$FOR i 0 10 2 $}\n" +
                " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\n" +
                "{$END$}";
        System.out.println(text);
        RealLexer lexer = new RealLexer(text);
        RealToken correctData[] = {
                new RealToken(RealTokenType.TEXT," This is sample text.\n"),
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.NUMBER,"1"),
                new RealToken(RealTokenType.NUMBER, "10"),
                new RealToken(RealTokenType.NUMBER,"1"),
                new RealToken(RealTokenType.TEXT,"\n This is "),
                new RealToken(RealTokenType.OPERATOR,"="),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.TEXT,"-th time this message is generated.\n"),
                new RealToken(RealTokenType.OPERATOR,"END"),
                new RealToken(RealTokenType.TEXT,"\n"),
                new RealToken(RealTokenType.OPERATOR,"FOR"),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.NUMBER,"0"),
                new RealToken(RealTokenType.NUMBER, "10"),
                new RealToken(RealTokenType.NUMBER,"2"),
                new RealToken(RealTokenType.TEXT,"\n sin("),
                new RealToken(RealTokenType.OPERATOR,"="),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.TEXT,"^2) = "),
                new RealToken(RealTokenType.OPERATOR,"="),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.VARIABLE,"i"),
                new RealToken(RealTokenType.OPERATOR,"*"),
                new RealToken(RealTokenType.FUNCTION, "sin"),
                new RealToken(RealTokenType.STRING,"0.000"),
                new RealToken(RealTokenType.FUNCTION,"decfmt"),
                new RealToken(RealTokenType.TEXT,"\n"),
                new RealToken(RealTokenType.OPERATOR,"END"),
                new RealToken(RealTokenType.EOF,null),

        };
        checkTokenStream(lexer, correctData);

    }
    private void checkTokenStream(RealLexer lexer, RealToken[] correctData) {
        int counter = 0;
        for (RealToken expected : correctData) {
            RealToken actual = lexer.nextToken();
            String msg = "Checking token " + counter + ":";
            System.out.println("expected"+expected.getValue()+":"+"actual"+actual.getValue());
            assertEquals(expected.getType(), actual.getType(), msg);
            assertEquals(expected.getValue(), actual.getValue(), msg);
            counter++;
        }
    }

}