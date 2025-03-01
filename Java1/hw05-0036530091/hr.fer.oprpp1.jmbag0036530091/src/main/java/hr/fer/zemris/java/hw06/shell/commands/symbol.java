package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;

import java.util.ArrayList;
import java.util.List;

public class symbol implements ShellCommand{
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String[] polje=arguments.split(" ");
        if(polje.length==1){
            System.out.print("Symbol for "+polje[0]+" is \'");
            if(polje[0].equals("PROMPT")){
                System.out.println(env.getPromptSymbol()+"\'");
            }else if(polje[0].equals("MORELINES")){
                System.out.println(env.getMorelinesSymbol()+"\'");
            }else if(polje[0].equals("MULTILINE")){
                System.out.println(env.getMultilineSymbol()+"\'");
            }
        }else if(polje.length==2){
            System.out.print("Symbol for "+polje[0]+" changed from \'");
            if(polje[0].equals("PROMPT")){
                System.out.print(env.getPromptSymbol()+"\' to \'");
                env.setPromptSymbol(polje[1].charAt(0));
                System.out.println(env.getPromptSymbol()+"\'");
            }else if(polje[0].equals("MORELINES")){
                System.out.print(env.getMorelinesSymbol()+"\' to \'");
                env.setMorelinesSymbol(polje[1].charAt(0));
                System.out.println(env.getMorelinesSymbol()+"\'");
            }else if(polje[0].equals("MULTILINE")){
                System.out.print(env.getMultilineSymbol()+"\' to \'");
                env.setMultilineSymbol(polje[1].charAt(0));
                System.out.println(env.getMultilineSymbol()+"\'");
            }

        }else {
            System.out.println("Invalid arguments in class symbol method execute command");
        }
        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "symbol";
    }

    @Override
    public List<String> getCommandDescription() {
        List<String> rez=new ArrayList<>();
        return rez;
    }
}
