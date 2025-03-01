package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class charsets implements ShellCommand{
    @Override
    public ShellStatus executeCommand(Environment env, String arguments)  {
        Map<String, Charset> chars = Charset.availableCharsets();
        chars.values().iterator().forEachRemaining(x-> {

            try {
                env.writeln(x.toString());
            } catch (ShellIOException e) {

            }

        });
        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "charsets";
    }

    @Override
    public List<String> getCommandDescription() {
        List<String> rez=new ArrayList<>();
        rez.add("Command charsets takes no arguments");
        rez.add("and lists names of supported charsets for your Java platform");
        rez.add("A single charset name is written per line.");
        return rez;
    }
}
