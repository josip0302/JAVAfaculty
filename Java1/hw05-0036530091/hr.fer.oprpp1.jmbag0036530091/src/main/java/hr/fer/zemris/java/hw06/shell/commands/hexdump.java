package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class hexdump implements ShellCommand {
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        try {


            File file = new File(arguments);
            if(file.isDirectory()){
                env.writeln("please give us name of the file not directory");
                return ShellStatus.CONTINUE;
            }
            InputStream stream= Files.newInputStream(file.toPath());
            byte[] poljeb = new byte[16];
            int i = stream.read(poljeb,0,16);
            int brojlinije=0;
            while (i > 0) {
                StringBuilder rez=new StringBuilder();
                rez.append(String.format("%08X",brojlinije)+": ");
                for (int b=0;b<16;b++){

                    if(i<16){

                        if(b<=i){
                            int a=poljeb[b];
                            if(127>=a && a>=32) {
                                rez.append(String.format("%02X",poljeb[b])+((b==7)?"|":" "));
                            }else {
                                rez.append(((b==7)?"|":" . "));
                            }

                        }else {
                            rez.append(((b==7)?"  |":"   "));

                        }
                        continue;
                    }
                    int a=poljeb[b];
                    if(127>=a && a>=32) {
                        rez.append(String.format("%02X",poljeb[b])+((b==(7))?"|":" "));
                    }else {
                        rez.append(((b==7)?"| ":" . "));
                    }

                }
                rez.append("| ");
                for (int b=0;b<i;b++){
                    String s=String.format("%c",poljeb[b]);
                    s=s.replace("\n", "").replace("\r", "");
                    rez.append(s);
                }

                env.writeln(rez.toString());

                i = stream.read(poljeb,0,16);;
                brojlinije+=i;


            }
            stream.close();
        } catch (Exception e) {


            System.out.println(e.getLocalizedMessage() + " in class hexdump executeCommand method");
        }
        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "hexdump";
    }

    @Override
    public List<String> getCommandDescription() {
        List<String> rez=new ArrayList<>();
        rez.add("hexdump command expects a single argument: file name, and produces hex-outpu");
        return rez;
    }
}
