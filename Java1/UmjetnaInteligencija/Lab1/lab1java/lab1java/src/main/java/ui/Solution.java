package ui;

public class Solution {


	public static void main(String ... args) {
		String alg="";
		String ss="";
		String h="";
		String checkConst="";
		String checkOpti="";
		for(int i =0;i<args.length;i++){
			if(args[i].equals("--alg")){
              alg=args[i+1];
				System.out.println(alg);
			}else if(args[i].equals("--ss")){
               ss=args[i+1];
				System.out.println(ss);
			}else if(args[i].equals("--h")){
				h=args[i+1];
				System.out.println(h);
			} else if (args[i].equals("--check-consistent")) {
				checkConst=args[i+1];
			} else if (args[i].equals("--check-optimistic")) {
				checkOpti=args[i+1];
			}
		}
		if(alg.equals("bfs")){
			BFS bfs=new BFS(ss);

		}

	}

}
