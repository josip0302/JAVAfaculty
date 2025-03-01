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
			}else if(args[i].equals("--ss")){
               ss=args[i+1];

			}else if(args[i].equals("--h")){
				h=args[i+1];

			} else if (args[i].equals("--check-consistent")) {
				checkConst="yes";
			} else if (args[i].equals("--check-optimistic")) {
				checkOpti="yes";
			}
		}
		if(alg.equals("bfs")){
			BFS bfs=new BFS(ss);

		} else if (alg.equals("ucs")) {
			UCS ucs=new UCS(ss);
			ucs.print();
		} else if (alg.equals("astar")) {
			Astar astar=new Astar(ss,h);
			astar.print();
		}


		if(checkConst!=""){
			Heuristics.checkConsist(ss,h);
		}
		if (checkOpti!=""){
			Heuristics.checkOpt(ss,h);
		}

	}

}
