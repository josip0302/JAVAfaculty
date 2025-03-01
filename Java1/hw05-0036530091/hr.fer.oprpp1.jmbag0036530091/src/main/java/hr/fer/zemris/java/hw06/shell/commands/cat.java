package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class cat implements ShellCommand{
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
        String[] polje=arguments.split(" ");
        if(arguments.contains("\"")){
            polje=arguments.split("\"");
        }
        try {
            InputStream in=new FileInputStream(polje[0].trim());
            InputStreamReader reader;
            if(polje.length==1){
                reader=new InputStreamReader(in);
            }else {
                reader=new InputStreamReader(in, polje[1].trim());
            }
            BufferedReader buffReader=new BufferedReader(reader);
            String line;

            while ((line = buffReader.readLine()) != null) {
                env.writeln(line);
            }
        } catch (IOException  e) {
            System.out.println(e.getMessage()+"in cat class method executeCommand");
        }

        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "cat";
    }

    @Override
    public List<String> getCommandDescription() {
        List<String> rez=new ArrayList<>();
        rez.add("Command cat takes one or two arguments. The first argument is path to some file and is mandatory. The\n");
        rez.add("second argument is charset name that is used to interpret chars from bytes. If not provided, a default\n");
        rez.add("platform charset is used.This command opens given file and writes its content to console\n");

        return rez;
    }
}
