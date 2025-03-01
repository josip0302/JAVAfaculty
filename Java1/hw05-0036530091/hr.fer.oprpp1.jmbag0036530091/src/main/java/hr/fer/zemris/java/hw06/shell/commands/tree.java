package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class tree implements ShellCommand{
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
        File file=new File(arguments);
        if (!file.isDirectory()) {
            env.writeln("invalid directory name");
            return ShellStatus.CONTINUE;
             }
        int udubina=0;
        StringBuilder sb = new StringBuilder();
        prikaziDir(file,udubina, sb);
        env.write(sb.toString());
        return ShellStatus.CONTINUE;
    }
    public static void prikaziDir(File file,int udubina, StringBuilder sb){
        File[] files = file.listFiles();
        sb.append("  ");
        for (int i=0;i<udubina;i++){
            sb.append("  ");
        }
        sb.append(file.getName());
        sb.append("\n");
        for (File f : files) {
            if (f.isDirectory()) {
                prikaziDir(f, udubina + 1, sb);
            }else {
                sb.append("  ");
                for (int i=0;i<udubina;i++){
                    sb.append("  ");
                }

                sb.append(file.getName());
                sb.append("\n");
            }
        }
    }
    @Override
    public String getCommandName() {
        return "tree";
    }

    @Override
    public List<String> getCommandDescription() {
        List<String> rez=new ArrayList<>();
        rez.add("The tree command expects a single argument: directory name and prints a tree");
        return rez;
    }
}
