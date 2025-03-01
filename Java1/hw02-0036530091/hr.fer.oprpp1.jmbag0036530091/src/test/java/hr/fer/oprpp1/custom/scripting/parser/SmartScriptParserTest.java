package hr.fer.oprpp1.custom.scripting.parser;

import hr.fer.oprpp1.custom.scripting.lexer.RealLexer;
import hr.fer.oprpp1.custom.scripting.lexer.RealToken;
import hr.fer.oprpp1.custom.scripting.lexer.RealTokenType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class SmartScriptParserTest {
    private String readExample(int n) {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("extra/primjer" + n + ".txt")) {
            if (is == null) throw new RuntimeException("Datoteka extra/primjer" + n + ".txt je nedostupna.");
            byte[] data = is.readAllBytes();
            String text = new String(data, StandardCharsets.UTF_8);
            return text;
        } catch (IOException ex) {
            throw new RuntimeException("Greška pri čitanju datoteke.", ex);
        }}
     @Test
        public void primjer1() {
            String text = readExample(1);

            SmartScriptParser parser= new SmartScriptParser(text);
         System.out.println(parser.toString());
           assertEquals("Ovo je \n" +
                   "sve jedan text node",parser.toString());


        }
    @Test
    public void primjerFOR() {
        String text = "{$ FOR i -1 10 1 $}";
        SmartScriptParser parser= new SmartScriptParser(text);
        assertEquals("{$ FOR i -1 10 1 $}",parser.toString());


    }
    @Test
    public void primjerFOR2() {
        String text = "{$ FOR i 1 10 1 $}\n" +
                " This is {$= i $}-th time this message is generated.{$END$}";
        SmartScriptParser parser= new SmartScriptParser(text);
        assertEquals("{$ FOR i 1 10 1 $}\n" +
                " This is {$= i $}-th time this message is generated.{$END$}",parser.toString());

    }

}