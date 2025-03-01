package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class ls implements ShellCommand{
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
        Stack<File> stack = new Stack<File>();
        if(arguments.contains("\"")){
            arguments=arguments.replace("\"","");
        }
        File file=new File(arguments);
        stack.push(file);
        while(!stack.isEmpty()) {
            File child = stack.pop();
            if (child.isDirectory()) {
                env.writeln(format(child.getPath()));
                for(File f : child.listFiles()) stack.push(f);
            } else if (child.isFile()) {
                env.writeln(format(child.getPath()));
            }
        }

        return ShellStatus.CONTINUE;
    }
    private String format(String arguments){
        StringBuilder sb=new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Path path = Paths.get(arguments);
        File file=new File(arguments);
        if(file.isDirectory()){
            sb.append("d");
        }else {
            sb.append("-");
        }
        if(file.canRead()){
            sb.append("r");
        }else {
            sb.append("-");
        }
        if(file.canWrite()){
            sb.append("w");
        }else {
            sb.append("-");
        }
        if(file.canExecute()){
            sb.append("e");
        }else {
            sb.append("-");
        }
        try {
            String size= String.valueOf(Files.size(path));
            sb.append(" ".repeat(10-size.length())+size);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        BasicFileAttributeView faView = Files.getFileAttributeView(
                path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS
        );
        BasicFileAttributes attributes = null;
        try {
            attributes = faView.readAttributes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileTime fileTime = attributes.creationTime();
        String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
        sb.append(" "+formattedDateTime);
        sb.append(" "+file.getName());
        return sb.toString();
    }
    @Override
    public String getCommandName() {
        return "ls";
    }

    @Override
    public List<String> getCommandDescription() {
        List<String> rez=new ArrayList<>();
        rez.add("Command ls takes a single argument – directory – and writes a directory listing");
        return rez;
    }
}
