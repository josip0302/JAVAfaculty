package hr.fer.zemris.b.klijentserver;


import java.io.*;
import java.net.*;
import java.util.*;

import static java.lang.Thread.sleep;

public class Server {
    public static void main(String[] args) throws SocketException {
        if(args.length!=1) {
            System.out.println("OÄŤekivao sam port");
            return;
        }

        List<Long> listaRandova=new ArrayList<>();
        Map<Long,Klijenti> mapaKlijenata=new HashMap<>();
        Map<Long,Klijenti> mapaRandova=new HashMap<>();
        long uid=new Random().nextLong();
        int port = Integer.parseInt(args[0]);
        DatagramSocket dSocket = new DatagramSocket(null);
        dSocket.bind(new InetSocketAddress((InetAddress)null, port));

        while(true) {

            // Pripremi prijemni spremnik:
            byte[] recvBuffer = new byte[1500];
            DatagramPacket recvPacket = new DatagramPacket(
                    recvBuffer, recvBuffer.length
            );

            // ÄŚekaj na primitak upita:
            try {
                dSocket.receive(recvPacket);

            } catch (IOException e) {
                continue;
            }

            Message message=unpackUDP(recvPacket);
            if(message.getMessageType()== 1){
                long mNumber= message.getMessageNumber();
                System.out.println(mNumber);
                long klijentUID=(++uid);
                if(!mapaRandova.containsKey(message.getKljuc())) {
                    Klijenti klijent = new Klijenti(message.getIme(), recvPacket.getAddress().getHostName(), klijentUID, message.getKljuc(), recvPacket.getPort());
                    mapaKlijenata.put(klijentUID, klijent);
                    mapaRandova.put(message.getKljuc(),klijent);
                    SendACK(mNumber, klijentUID, recvPacket, dSocket);
                    System.out.println(mapaKlijenata);
                }else {
                    Klijenti klinet=mapaRandova.get(message.getKljuc());
                    SendACK(mNumber, klijentUID, recvPacket, dSocket);
                }
            }else if(message.getMessageType()== 3){
                long mNumber= message.getMessageNumber();
                System.out.println(mNumber);
                long klijentUID=message.getUid();
                SendACK(mNumber, klijentUID, recvPacket, dSocket);
            } else if (message.getMessageType()== 4) {
                long mNumber= message.getMessageNumber();
                System.out.println(message.getTekst());
                System.out.println(mNumber);
                long klijentUID=message.getUid();
                Klijenti klijenti=mapaKlijenata.get(klijentUID);
                SendACK(mNumber, klijentUID, recvPacket, dSocket);
                sendIN(mNumber,message.getTekst(),klijenti.ime,dSocket,mapaKlijenata);
            }

        }
    }

    private static void sendIN(long mNumber, String tekst, String ime, DatagramSocket dSocket, Map<Long, Klijenti> mapaKlijenata) {
        System.out.println("In");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            dos.writeInt(5);
            dos.writeLong(mNumber);
            dos.writeUTF(ime);
            dos.writeUTF(tekst);
            dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] sendBuffer = bos.toByteArray();

    for(long id:mapaKlijenata.keySet()) {

    // Pripremi odlazni paket:
    DatagramPacket sendPacket = new DatagramPacket(
            sendBuffer, sendBuffer.length
    );
    Klijenti klijent=mapaKlijenata.get(id);
        try {
            sendPacket.setSocketAddress(new InetSocketAddress(
                    InetAddress.getByName(klijent.hostName),
                    klijent.port
            ));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        // PoĹˇalji ga:
    try {
        dSocket.send(sendPacket);
        System.out.println("paket poslan");
    } catch (IOException e) {
        System.out.println("GreĹˇka pri slanju odgovora.");
    }
}
    }

    private static void SendACK(long mNumber, long klijentUID, DatagramPacket recvPacket, DatagramSocket dSocket)  {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            dos.writeInt(2);
            dos.writeLong(mNumber);
            dos.writeLong(klijentUID);
            dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] sendBuffer = bos.toByteArray();



        // Pripremi odlazni paket:
        DatagramPacket sendPacket = new DatagramPacket(
                sendBuffer, sendBuffer.length
        );
        sendPacket.setSocketAddress(recvPacket.getSocketAddress());
        System.out.println(recvPacket.getSocketAddress());
        // PoĹˇalji ga:
        try {
            dSocket.send(sendPacket);
            System.out.println("paket poslan");
        } catch (IOException e) {
            System.out.println("GreĹˇka pri slanju odgovora.");
        }

    }

    private static Message unpackUDP(DatagramPacket recvPacket) {
        ByteArrayInputStream bis = new ByteArrayInputStream(recvPacket.getData(),recvPacket.getOffset(),recvPacket.getLength());
        DataInputStream ois = new DataInputStream(bis);
        try {
            Message poruka=null;
            int a=ois.readInt();
            if(a==1) {
                poruka = new Message(a, ois.readLong(), ois.readUTF(), ois.readLong());
           } else if (a==3) {
                poruka = new Message(a, ois.readLong(), ois.readLong());
           } else if (a==4) {
                poruka = new Message(a, ois.readLong(), ois.readLong(),ois.readUTF());
            }
            return  poruka;//,ois.readLong(),ois.readUTF(),ois.readLong());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
