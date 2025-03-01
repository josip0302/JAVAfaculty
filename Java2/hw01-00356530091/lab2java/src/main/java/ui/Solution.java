package ui;

public class Solution {
    public static void main(String[] args) {
        Boolean resolution=false;
        boolean cooking=false;
        String fileName="";
        String fileName2="";
       if(args.length==2){
           if(args[0].equals("resolution")){
               resolution=true;
             fileName=  args[1];
           }else {
               resolution=true;
               fileName=args[0];
           }
       }
        if(args.length==3){
            Cooking cook=new Cooking(args[1],args[2]);
        }


        if(resolution){
            System.out.println(fileName);
            Resolution r=new Resolution(fileName);
        }
    }
}
