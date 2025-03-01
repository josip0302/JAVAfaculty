package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class mkdir implements ShellCommand{
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
        Path path = Paths.get(arguments);
        try {
            Files.createDirectories(path);
            env.writeln(arguments+" is created!");
        } catch (IOException e) {
            System.out.println(e.getMessage()+" in mkdir class executeCommand method");
        }
        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "mkdir";
    }

    @Override
    public List<String> getCommandDescription() {

        List<String> rez=new ArrayList<>();
        rez.add("The mkdir command takes a single argument: directory name, and creates");
        rez.add("the appropriate directory structure.");
        return rez;
    }
}
