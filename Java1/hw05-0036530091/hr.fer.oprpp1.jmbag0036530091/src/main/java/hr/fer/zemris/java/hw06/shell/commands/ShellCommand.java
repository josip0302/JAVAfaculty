package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.util.List;

public interface ShellCommand {
    ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException;
    String getCommandName();
    List<String> getCommandDescription();
}
