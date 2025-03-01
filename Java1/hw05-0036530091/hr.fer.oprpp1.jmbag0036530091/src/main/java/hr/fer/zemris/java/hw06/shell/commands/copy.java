package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class copy implements ShellCommand{
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
        String[] polje=arguments.split(" ");
        if(arguments.contains("\"")){
             polje=arguments.split("\"");
        }

        try {
            InputStream in = new FileInputStream(polje[0].trim());
            File file=new File(polje[1]);
            if(file.exists() && !file.isDirectory()){
                env.writeln("File "+polje[1].trim()+" already exists, do you want to owerwrite it?(yes/no)");
                if(env.readLine().trim().equals("yes")){

                }else {
                    return ShellStatus.CONTINUE;
                }
            }
            OutputStream out;
            if(file.isDirectory()){
                String s = String.valueOf('/');
                String[] novo=polje[0].split(s);
                out = new FileOutputStream(polje[1]+"/"+novo[novo.length-1]);

            }else {
                out = new FileOutputStream(polje[1]);
            }
            byte[] poljeb = new byte[2048];
            int i = in.read(poljeb);
            while (i > 0) {
                byte[] poljeout = new byte[2048];
                if (i == 2048) {
                    out.write(poljeb);
                } else {
                    out.write(poljeb,0,i);
                }
                i = in.read(poljeb);
            }
            out.flush();
            in.close();
            out.close();
        } catch ( IOException e) {
            System.out.println(e.getMessage()+" in copy class executeCommand method");
        }


        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "copy";
    }

    @Override
    public List<String> getCommandDescription() {
        List<String> rez=new ArrayList<>();
        rez.add("The copy command expects two arguments: source file name and destination file name ");
        rez.add("And then copies one file to another");
        return rez;
    }
}
