package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proposition {
    List<String> elementi;
    int index;
    int origin1,origin2;

    public int getOrigin1() {
        return origin1;
    }

    public void setOrigin1(int origin1) {
        this.origin1 = origin1;
    }

    public int getOrigin2() {
        return origin2;
    }

    public void setOrigin2(int origin2) {
        this.origin2 = origin2;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getElementi() {
        return elementi;
    }

    public Proposition(String toLowerCase) {
        index=-1;
        origin1=-1;
        origin2=-1;
        elementi=new ArrayList<>();
        for(String s:List.of(toLowerCase.split("v"))){
            elementi.add(s.trim());
        }
    }

    public Proposition(List<String> elementi) {
        this.elementi=elementi;
    }

    public boolean isTauntology(){
        for (String e:elementi){
            String a=Proposition.complement(e);
            if(elementi.contains(a)){
                return true;
            }
        }
        return false;
    }

    public Proposition complement(){
        return new Proposition(Proposition.complement(elementi.get(0)));
    }

    public static String complement(String a){
        if(a.contains("~")){
            return a.replace("~","");
        }else {
            return "~"+a;
        }
    }

    @Override
    public String toString() {
        String a="";
        if(index>=0){
            a+=index+". ";
        }
        if(this.NIL()){
            a+="NIL";
        }
        for (String s:elementi){
            if(elementi.indexOf(s)!=elementi.size()-1){
                a+=s+" v ";
            }else {
                a += s;
            }
        }
        if(origin1>=0 && origin2>=0){
            a+=" ("+origin1+","+origin2+")";
        }
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposition that = (Proposition) o;
        if(that.elementi.size()!=elementi.size()){
            return false;
        }
        for(String s:elementi){
            if(!that.elementi.contains(s)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementi);
    }
    public Proposition resolve(Proposition other) {
        List<String> elementi2 = new ArrayList<>(elementi);
        elementi2.addAll(other.getElementi());
        boolean dobro=false;
        for (String s : other.getElementi()) {
            String k = Proposition.complement(s);

            if (elementi.contains(k)) {
                elementi2.remove(k);
                elementi2.remove(s);
                dobro=true;
            }
        }
        Proposition rez=null;
        if(dobro) {
            rez = new Proposition(elementi2);
            rez.setOrigin1(this.getIndex());
            rez.setOrigin2(other.getIndex());
        };
        return rez;
    }
    public boolean NIL(){
        return elementi.size()==0;
    }

    public boolean jePodskup( Proposition other){
        for(String s:elementi){

            if(!other.getElementi().contains(s)){
                return false;
            }
        }
        return true;
    }
}
