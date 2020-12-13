import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        // Oppretter objekt med konstruktør
        var ansatt = new Ansatt();

        // Oppretter nye objekter uten konstruktør (clone)
        var atle = (Ansatt)ansatt.clone();
        atle.setNavn("Atle Patle");
        atle.setAlder(32);

        var per = (Ansatt)ansatt.clone();
        per.setNavn("Per Viskelær");
        per.setAlder(30);

        new ObjectOutputStream(new FileOutputStream("ansatt.obj"))
                .writeObject(ansatt);

        // Oppretter nytt objekt uten konstruktør (deserialisering)
        var anne = (Ansatt) new ObjectInputStream(new FileInputStream("ansatt.obj"))
                .readObject();

        anne.setNavn("Anne Ananas");
        anne.setAlder(40);

        Arrays.asList(ansatt, per, atle, anne)
                .forEach(System.out::println);

        System.out.println("\nAntall ganger konstruktører ble brukt: " + Ansatt.antallKjorteKonstruktorer);
    }
}
