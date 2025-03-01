package hr.fer.zemris.java.hw06.shell;

import hr.fer.zemris.java.hw06.shell.commands.ShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyShell implements Environment {
    private char multilineSymbol,promptSymbol,morelinesSymbol;
    private SortedMap<String, ShellCommand> commands;

    public MyShell() {
        this.multilineSymbol = '|';
        this.promptSymbol = '>';
        this.morelinesSymbol = '\\';
        this.commands = new TreeMap<>();
        commands.put("cat",new cat());
        commands.put("charsets",new charsets());
        commands.put("copy",new copy());
        commands.put("hexdump",new hexdump());
        commands.put("ls",new ls());
        commands.put("mkdir",new mkdir());
        commands.put("tree",new tree());
        commands.put("symbol",new symbol());
        commands.put("exit",new ExitShellCommand());
    }

    public static void main(String[] args) {
        MyShell shell=new MyShell();
        ShellStatus status=ShellStatus.CONTINUE;
        SortedMap<String, ShellCommand> commands= shell.commands();
        System.out.println("Welcome to MyShell v 1.0");

        String line;
        do{
            StringBuilder sb=new StringBuilder();
            System.out.print(shell.getPromptSymbol()+" ");
            try {
                line= shell.readLine();
                sb.append(line);
                while (line.charAt(line.length()-1)==shell.getMorelinesSymbol()){
                    System.out.print(shell.getMultilineSymbol()+" ");
                    line= shell.readLine();
                    sb.append(line);
                };
                String input=sb.toString().replace(shell.getMorelinesSymbol().toString(), "");
                String commandName = input.trim().split("\\s+")[0];
                String arguments = input.replace(commandName + " ", "");
                if(commandName.equals("help")){
                     arguments = input.replace(commandName, "");
                    if(arguments.length()==0){
                        for (String s: commands.keySet()){
                            System.out.println(s);
                        }
                    }else {
                        ShellCommand com=commands.get(arguments.trim());
                        System.out.println(arguments);
                        System.out.println(com.getCommandDescription());
                    }
                }else
                {

                    ShellCommand command = commands.get(commandName.trim());
                    status = command.executeCommand(shell, arguments);
                }
            } catch (ShellIOException e) {
                System.out.println(e.getMessage());
                status=ShellStatus.TERMINATE;
            }

        }while (status!=ShellStatus.TERMINATE);
    }

    @Override
    public String readLine() throws ShellIOException {
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public void write(String text) throws ShellIOException {
        System.out.print(text);
    }

    @Override
    public void writeln(String text) throws ShellIOException {
        System.out.println(text);
    }

    @Override
    public SortedMap<String, ShellCommand> commands() {
        return this.commands;
    }

    @Override
    public Character getMultilineSymbol() {
        return multilineSymbol;
    }

    @Override
    public void setMultilineSymbol(Character symbol) {
         this.multilineSymbol=symbol;
    }

    @Override
    public Character getPromptSymbol() {
        return this.promptSymbol;
    }

    @Override
    public void setPromptSymbol(Character symbol) {
       this.promptSymbol=symbol;
    }

    @Override
    public Character getMorelinesSymbol() {
        return this.morelinesSymbol;
    }

    @Override
    public void setMorelinesSymbol(Character symbol) {
         this.morelinesSymbol=symbol;
    }
}
