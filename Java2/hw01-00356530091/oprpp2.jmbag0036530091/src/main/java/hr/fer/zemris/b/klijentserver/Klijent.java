package hr.fer.zemris.b.klijentserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Klijent {
    String ime,hostname;
    int port;
int brojPoruka;

JFrame frame;
JTextArea area;
JTextField field;

    public Klijent(String ime, String hostname, int port) {
        this.ime = ime;
        this.hostname = hostname;
        this.port = port;
        this.brojPoruka=0;
    }

    long UID;
    public static void main(String[] args)
            throws IOException {

        if(args.length!=3) {
            System.out.println("Očekivao sam host,port i ime");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

       Klijent klijent=new Klijent(args[2],hostname,port);
       klijent.initHelloServer();
       klijent.GUI();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       while (klijent.frame.isActive()){

           try {
               sleep(3000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }



       }
       klijent.sayBye();
        System.out.println("USPILI SMO PRVI KORAK");

    }

    private void waitINM() {
        try {
            DatagramSocket dSocket = new DatagramSocket();
            byte[] recvBuffer = new byte[1500];
            DatagramPacket recvPacket = new DatagramPacket(
                    recvBuffer, recvBuffer.length
            );


            dSocket.receive(recvPacket);
            ByteArrayInputStream bis = new ByteArrayInputStream(recvPacket.getData(),recvPacket.getOffset(),recvPacket.getLength());

            DataInputStream ois = new DataInputStream(bis);
            int a=ois.readInt();
            Message poruka=new Message(a,ois.readLong(),ois.readUTF(),ois.readUTF());
            System.out.println(poruka.getTekst());
            if (a == 5) {
                String aba=this.area.getText();
                this.area.setText(aba+recvPacket.getSocketAddress().toString()+"Poruka od:"+poruka.getIme()+"\n"+poruka.getTekst()+"\n");

            }
        } catch (SocketException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendOUTM(String getText) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            dos.writeInt(4);
            dos.writeLong(this.brojPoruka);
            dos.writeLong(this.UID);
            dos.writeUTF(getText);
            dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] podatci = bos.toByteArray();
        System.out.println("poslano:"+getText);
        Message ack=sendAndWait(podatci);
        brojPoruka++;
    }

    private void GUI() {
        frame=new JFrame("Chat client"+ime);
        frame.setSize(500,500);
        frame.getContentPane().setLayout(new BorderLayout());
        area=new JTextArea();
        area.setEditable(false);
        field=new JTextField();
        field.addActionListener((s) -> {
            String getText=field.getText().trim();
            sendOUTM(getText);
            waitINM();
        });
        JScrollPane pane=new JScrollPane(area);
        frame.getContentPane().add(field,BorderLayout.NORTH);
        frame.getContentPane().add(pane,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


    private void sayBye() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(3);
        dos.writeLong(this.brojPoruka);
        dos.writeLong(this.UID);
        dos.close();
        byte[] podatci = bos.toByteArray();
        Message ack=sendAndWait(podatci);
    }

    private void initHelloServer() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(1);
        dos.writeLong(this.brojPoruka);
        dos.writeUTF(this.ime);
        dos.writeLong(new Random().nextLong());
        dos.close();
        byte[] podatci = bos.toByteArray();
        Message ack=sendAndWait(podatci);
        this.UID=ack.getUid();
        brojPoruka++;

    }

    private Message sendAndWait(byte[] podatci){
        try {
            Message poruka = null;
            DatagramSocket dSocket = new DatagramSocket();

            DatagramPacket packet = new DatagramPacket(
                    podatci, podatci.length
            );
            packet.setSocketAddress(new InetSocketAddress(
                    InetAddress.getByName(hostname),
                    port
            ));

            while(true) { {
                try {
                    dSocket.setSoTimeout(5000);
                } catch (SocketException e) {
                    throw new RuntimeException(e);
                }
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Šaljem upit...");
                try {
                    dSocket.send(packet);
                } catch (IOException e) {
                    System.out.println("Ne mogu poslati upit.");
                    break;
                }

                byte[] recvBuffer = new byte[1500];
                DatagramPacket recvPacket = new DatagramPacket(
                        recvBuffer, recvBuffer.length
                );
                System.out.println(packet.getSocketAddress());
                try {
                    dSocket.receive(recvPacket);
                    byte[] rezultat = recvPacket.getData();
                    ByteArrayInputStream bis = new ByteArrayInputStream(recvPacket.getData(),recvPacket.getOffset(),recvPacket.getLength());

                    DataInputStream ois = new DataInputStream(bis);
                    int a=ois.readInt();
                    poruka = new Message(a,ois.readLong(),ois.readLong());
                    System.out.println("dođe li do ovoga");
                    System.out.println(a);
                    if (a == 2) {
                        System.out.println("uspija san");
                        return poruka;

                    }
                } catch (SocketTimeoutException ste) {
                    System.out.println(ste.getMessage());
                    continue;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

            }
            dSocket.close();
            return poruka;
        }
        return null;

        // Obavezno zatvori pristupnu toÄŤku:


    } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }}
