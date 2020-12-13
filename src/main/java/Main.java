import sun.reflect.ReflectionFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

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
        var anne = (Ansatt)new ObjectInputStream(new FileInputStream("ansatt.obj"))
                .readObject();

        anne.setNavn("Anne Ananas");
        anne.setAlder(40);

        // "munged" konstruktør, bruker ikke klassens egne konstruktører
        // Dette eksempelet er ikke avhengig av eksisterende objekter
        ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
        Constructor<?> intConstr = rf.newConstructorForSerialization(Ansatt.class);

        var gunnar = (Ansatt)intConstr.newInstance();

        gunnar.setNavn("Gunnar");
        gunnar.setAlder(23);

        Arrays.asList(ansatt, per, atle, anne, gunnar)
                .forEach(System.out::println);

        System.out.println("\nAntall ganger konstruktører ble brukt: " + Ansatt.antallKjorteKonstruktorer);
    }
}
