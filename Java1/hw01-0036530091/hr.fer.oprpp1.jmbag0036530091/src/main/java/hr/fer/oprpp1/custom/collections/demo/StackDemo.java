package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.ObjectStack;

public class StackDemo {
    public static void main(String[] args) {
        ObjectStack stack=new ObjectStack();
        String[] input=args[0].split(" " );
        for(String s: input){
            if(s.equals("+")){
                int a=(Integer) stack.pop();
                int b=(Integer)stack.pop();
                stack.push((a+b));
            } else if (s.equals("-")) {
                int a=(Integer) stack.pop();
                int b=(Integer)stack.pop();
                stack.push((b-a));
            }else if (s.equals("\"")) {}
            else if (s.equals("*")) {
                int a=(Integer) stack.pop();
                int b=(Integer)stack.pop();
                stack.push((a*b));
            }
            else if (s.equals("/")) {
                int a=(Integer) stack.pop();
                int b=(Integer)stack.pop();
                if(a==0){
                    throw new Error("Divison with zero is not possible");
                }else {
                    stack.push((b / a));
                }
            }else if((s.equals("%"))){
                int a=(Integer) stack.pop();
                int b=(Integer)stack.pop();
                stack.push((b%a));
            }else{
                stack.push((int) (Integer.parseInt(s)));
            }
        }
        if(stack.size()!=1)throw new Error();
        else System.out.println(stack.pop());

    }
}
