package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.MyShell;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class treeTest {
    @Test
   public void treeExecuteCommandTest(){
        Environment env=new MyShell();
        String name="src";
        tree t=new tree();
        try {
            t.executeCommand(env,"src");
        } catch (ShellIOException e) {
            throw new RuntimeException(e);
        }
    }

}