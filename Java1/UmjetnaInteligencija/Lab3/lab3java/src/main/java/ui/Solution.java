package ui;

public class Solution {

	public static void main(String ... args) {
        TableLine table=  InAndOut.readCSV(args[0]);
		int depth=-1;
		if(args.length==3){
			depth=Integer.parseInt(args[2]);
		}
		ID3 id3=new ID3(table);

		id3.fit(table,0,null,null,depth);
		id3.printBranches();
		id3.predict(args[1]);
		id3.printAccuracy();
		id3.matrix();

	}

}
