package hr.fer.zemris.b.klijentserver;

import oprpp2.hw02.common.MessageType;

import java.io.Serializable;

public class Message {
    private int messageType;
    private long messageNumber;
    private long uid;
    private long kljuc;
    private String ime;

    private String tekst;

    public Message(int messageType, long messageNumber) {
        this.messageType = messageType;
        this.messageNumber = messageNumber;
    }

    public Message(int tip, long messageNumber, long uid) {
      this(tip,messageNumber);
        this.uid=uid;

    }
    public Message(int tip, long messageNumber, String ime, long kljuc) {
        this(tip,messageNumber);
        this.ime=ime;
        this.kljuc=kljuc;
    }

    public Message(int a, long readLong, long readLong1, String readUTF) {
        this.messageType = a;
        this.messageNumber = readLong;
        this.uid=readLong1;
        this.tekst = readUTF;

    }

    public long getUid() {
        return uid;
    }

    public long getKljuc() {
        return kljuc;
    }

    public String getIme() {
        return ime;
    }

    public Message(int messageType, long messageNumber, String ime, String tekst) {
        this.messageType = messageType;
        this.messageNumber = messageNumber;
        this.ime=ime;
        this.tekst = tekst;
    }

    public String getTekst() {
        return tekst;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public long getMessageNumber() {
        return this.messageNumber;
    }

}
