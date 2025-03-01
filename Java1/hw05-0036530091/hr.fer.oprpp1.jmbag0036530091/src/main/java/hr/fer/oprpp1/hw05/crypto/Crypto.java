package hr.fer.oprpp1.hw05.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.Document;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

import static hr.fer.oprpp1.hw05.crypto.Util.bytetohex;
import static hr.fer.oprpp1.hw05.crypto.Util.hextobyte;

public class Crypto {


    public static void main(String[] args) {
        boolean encrypt;
        boolean cipherb=false;
        Scanner scanner = new Scanner(System.in);
        if(args[0].equals("checksha")) {
        byte[] polje=new byte[2048];
        String realPassword="";
        StringBuilder sb=new StringBuilder();
        MessageDigest mg;
        try{
            mg=MessageDigest.getInstance("SHA-256");
            FileInputStream stream=new FileInputStream(args[1]);
            int i= stream.read(polje);
            int sum=0;
            while(i>0) {
                if(i==2048) {
                    mg.update(polje);
                }else {
                    mg.update(polje,0,i);
                }
                sum+=i;
                i=stream.read(polje);
            }
            stream.close();
            realPassword=bytetohex(mg.digest());
        }catch (Exception e){
            System.out.println(e.getMessage()+e.getLocalizedMessage());

        }
           System.out.print("Please provide expected sha-256 digest for "+args[1]+":\n>");
            String password = scanner.next();
            if(password.equals(realPassword)){
                System.out.println("Digesting completed. Digest of "+args[1]+" matches expected digest.");
            }else {
                System.out.println("Digesting completed. Digest of "+args[1]+" does not match the expected digest. Digest\n" +
                        "was: "+realPassword);
            }
            return;
        }
        if(args[0].equals("encrypt")){
            encrypt=true;
        cipherb=true;}
        else if(args[0].equals("decrypt")){
            encrypt=false;
        cipherb=true;}else {
            encrypt=false;
        }
        if(cipherb) {
            System.out.print("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):\n>");
            String password = scanner.next();
            System.out.print("Please provide initialization vector as hex-encoded text (32 hex-digits):\n>");
            String initVector = scanner.next();
            String keyText = password;
            String ivText = initVector;
            SecretKeySpec keySpec = new SecretKeySpec(Util.hextobyte(keyText), "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(hextobyte(ivText));
            Cipher cipher = null;

            try {
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);
                FileInputStream inStream = new FileInputStream(args[1]);
                FileOutputStream outStream = new FileOutputStream(args[2]);
                byte[] polje = new byte[2048];
                int i = inStream.read(polje);
                while (i > 0) {
                    byte[] poljeout = new byte[2048];
                    if (i == 2048) {

                        poljeout = cipher.update(polje);
                        outStream.write(poljeout);
                    } else {

                        poljeout = cipher.update(polje, 0, i);

                        outStream.write(poljeout);
                    }
                    i = inStream.read(polje);
                }
                outStream.flush();
                inStream.close();
                outStream.close();

            } catch (NoSuchAlgorithmException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchPaddingException e) {
                System.out.println(e.getMessage());
            } catch (InvalidAlgorithmParameterException e) {
                System.out.println(e.getMessage());
            } catch (InvalidKeyException e) {
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println((encrypt ?"Encryption":"Decryption")+" completed. Generated file "+args[2]+" based on file "+args[1]+".");
        }
    }
}
