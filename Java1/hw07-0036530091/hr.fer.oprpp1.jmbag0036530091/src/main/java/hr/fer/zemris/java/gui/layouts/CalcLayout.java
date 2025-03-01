package hr.fer.zemris.java.gui.layouts;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CalcLayout implements LayoutManager2 {
    private int distance;
    private Map<Component,RCPosition> map;
    private boolean sizeUnokwn;

    public CalcLayout(int distance) {
        this.distance = distance;
        this.map=new HashMap<>();
        this.sizeUnokwn=true;

    }

    public CalcLayout() {
        this(0);
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        RCPosition constraints2;
        if(constraints instanceof String){
           constraints2=RCPosition.parse((String) constraints);
        } else if (constraints instanceof RCPosition) {
            constraints2=(RCPosition) constraints;
        } else if (constraints==null) {
            throw new NullPointerException();
        } else {
            throw new IllegalArgumentException();
        }
        int s=constraints2.getColumn();
        int r=constraints2.getRow();
        boolean contains=false;
        for( Component key:map.keySet()){
            if(map.get(key).equals((RCPosition)constraints)){
                contains=true;
            }
        }
        if(contains|| (r<1||r>5) || (s<1||s>7)||(r==1&&(s>1&&s<6))){
            throw new CalcLayoutException();
        }else {

            map.put(comp,(RCPosition) constraints);
        }

    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        int maxLength=0;
        int maxHeight=0;
        for( Component key:map.keySet()){
            int x=key.getMaximumSize().width;
            int y=key.getMaximumSize().height;
            if(x>maxLength){
                maxLength=x;
            }
            if(y>maxHeight){
                maxHeight=y;
            }
        }
       // return new Dimension(maxLength,maxHeight);
       return target.getMaximumSize();
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {
        if(!map.containsKey(target)){

        }else {
            target.invalidate();
            map.put(target,map.get(target));
        }

    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        if(!map.containsKey(comp)){
            throw new CalcLayoutException();
        }else {
            map.remove(comp);

        }
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int maxLength=0;
        int maxHeight=0;
        for( Component key:map.keySet()){
            int x=key.getPreferredSize().width;
            int y=key.getPreferredSize().height;
            if(map.get(key).equals(new RCPosition(1,1))){
                x=(x-4*distance)/5;
            }
            if(x>maxLength){
                maxLength=x;
            }
            if(y>maxHeight){
                maxHeight=y;
            }
        }


        int width=maxLength*7+6*distance;
        int height=maxHeight*5+4*distance;

        return new Dimension(width,height);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        int maxLength=0;
        int maxHeight=0;
        for( Component key:map.keySet()){
            int x=key.getMinimumSize().width;
            int y=key.getMinimumSize().height;
            if(x>maxLength){
                maxLength=x;
            }
            if(y>maxHeight){
                maxHeight=y;
            }
        }
        int width=maxLength*7+6*distance+10;
        int height=maxHeight*7+6*distance+10;

        return new Dimension(width,height);
    }

    @Override
    public void layoutContainer(Container parent) {
        int width= parent.getWidth();
        int height=parent.getHeight();

        double mjeraH=(height-6*distance)/5;
        double mjeraW=(width-6*distance)/7;

        for(Component key:map.keySet()){
            int x=map.get(key).getColumn();
            int y=map.get(key).getRow();
            if(x==1 && y==1){
                int w=0;
                for(int i =0 ;i<5;i++){
                    if(i%2==0){
                        w+=(int)(mjeraW+0.5);
                    }else {
                        w+=(int)(mjeraW);
                    }
                }
                key.setBounds(0,0,w+distance*4,(int)(mjeraH+0.5));
            }else {
                int h=0;
                int w=0;
                for(int i =0 ;i<x-1;i++){
                    if(i%2==0){
                        w+=(int)(mjeraW+0.5);
                    }else {
                        w+=(int)(mjeraW);
                    }
                }
                for(int i =0 ;i<y-1;i++){
                    if(i%2==0){
                        h+=(int)(mjeraH+0.5);
                    }else {
                        h+=(int)(mjeraH);
                    }
                }
                key.setBounds((x - 1) * distance+w, (y - 1) *  distance+h,(x%2==0)?(int)(mjeraW+0.5):(int)mjeraW,(y%2==0)?(int)(mjeraH+0.5):(int)mjeraH);
            }
        }
    }
}
