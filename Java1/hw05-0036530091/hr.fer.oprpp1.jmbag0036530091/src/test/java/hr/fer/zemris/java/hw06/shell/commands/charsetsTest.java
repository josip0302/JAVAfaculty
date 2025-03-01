package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.MyShell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class charsetsTest {
    @Test
    public void charsetsTest(){
        Environment en=new MyShell();
        charsets charsets=new charsets();
        charsets.executeCommand(en,"");
    }

}