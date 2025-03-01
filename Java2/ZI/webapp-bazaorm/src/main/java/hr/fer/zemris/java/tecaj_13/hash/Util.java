package hr.fer.zemris.java.tecaj_13.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    /**
     * @param input
     * turns input from string in hexadecimal to byte type
     * using static digit function Character
     * @return byte array
     * @throws IllegalArgumentException if input is invalid (odd-sized, has invalid characters,etc.)
     */
    public static  byte[] hextobyte(String input) throws IllegalArgumentException{

        if(input.length()% 2 !=0)throw new IllegalArgumentException();
        byte[] rez = new byte[input.length() / 2];
        char [] poljeC=input.toCharArray();
        int count=0;
        for(int i =0;i<input.length();i+=2){
            int prva=Character.digit(poljeC[i],16);
            int druga=Character.digit(poljeC[i+1],16);
            if(prva==-1 || druga==-1){
                throw new IllegalArgumentException();

            }
            rez[count++]= (byte)(((prva)<<4)+druga);
        }
        return rez;
    }

    /**
     *
     * @param polje which is array of bytes
     * then each byte is turned to two characters which contain hexadecimal digits of that byte
     * @return number in hexadecimal system of type string
     */
    public static  String bytetohex(byte[] polje) {
        StringBuilder rez=new StringBuilder();

        for(int i = 0;i<polje.length;i++) {
            char x;
            x = Character.forDigit((polje[i] >> 4) & 0xF, 16);
            rez.append(x);
            x = Character.forDigit((polje[i] & 0xF), 16);
            rez.append(x);
        }
        return new String(rez.toString());
    }
    public static String encode(String pass){
        try {
            MessageDigest mg=MessageDigest.getInstance("SHA-1");
            mg.update(pass.getBytes());

            return Util.bytetohex(mg.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
