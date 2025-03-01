package ui;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String ... args) {
		String train=null;
		String test= null;
		String nn=null;
		int popsize=0;
		int elitism=0;
		double p=0;
		double k=0;
		int iter=0;
		for(int i =0;i<args.length;i+=2){
			if(args[i].equals("--train")){

				train=args[i+1];
			} else if (args[i].equals("--test")) {
				test=args[i+1];
			} else if (args[i].equals("--nn")) {
				nn=args[i+1];
			}else if (args[i].equals("--popsize")) {
				popsize=Integer.parseInt(args[i+1]);
			}else if (args[i].equals("--elitism")) {
				elitism=Integer.parseInt(args[i+1]);
			}else if (args[i].equals("--p")) {
				p= Double.parseDouble(args[i+1]);
			} else if (args[i].equals("--K")) {
				k= Double.parseDouble(args[i+1]);
			}else if (args[i].equals("--iter")) {
				iter=Integer.parseInt(args[i+1]);
			}
		}
		List<NeuralNet> mainList=new ArrayList<>();
		for (int i = 0; i < popsize; i++) {
			mainList.add(new NeuralNet(nn.trim(),train));
		}
		List<NeuralNet> next=Input.evaluate(mainList,elitism);

		NeuralNet net=null;
		for (int i = 1; i <= iter; i++) {
			while (next.size()<popsize){
				List<NeuralNet> roditelji=Input.evaluate(mainList,2);

				NeuralNet net1=NeuralNet.newNet(roditelji.get(0),roditelji.get(1));
				net1.mutacija(p,k);
				next.addAll( roditelji);
				next.add(net1);

			}
			mainList=new ArrayList<>(next);
			next=Input.evaluate(mainList,elitism);
			if(i%2000==0){
				System.out.print("[Train error @"+i+"]: ");
				System.out.printf("%,.6f\n",next.get(0).error);
			}
			 net=next.get(0);
		}

		net.setData(test);
		net.start();
		System.out.print("[Test error]: ");
		System.out.printf("%,.6f",next.get(0).error);




	}

}
