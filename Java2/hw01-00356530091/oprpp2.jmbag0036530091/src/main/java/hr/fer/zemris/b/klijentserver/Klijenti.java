package hr.fer.zemris.b.klijentserver;

public class Klijenti {
    String ime,hostName;
    long uid;
    long initKljuc;
    int port;

    public Klijenti(String ime, String hostName, long uid, long initKljuc, int port) {
        this.ime = ime;
        this.hostName = hostName;
        this.uid = uid;
        this.initKljuc = initKljuc;
        this.port = port;
    }
}
