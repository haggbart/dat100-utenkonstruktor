# dat100-utenkonstruktør

Eksempler på hvordan objekter kan opprettes uten konstruktør i java. 

```java
    public static void main(String[] args) {

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
        var ansattKlasse = ReflectionFactory.getReflectionFactory()
                .newConstructorForSerialization(Ansatt.class);

        var gunnar = (Ansatt)ansattKlasse.newInstance();

        gunnar.setNavn("Gunnar");
        gunnar.setAlder(23);

        Arrays.asList(ansatt, per, atle, anne, gunnar)
                .forEach(System.out::println);

        System.out.println("\nAntall ganger konstruktører ble brukt: " + Ansatt.antallKjorteKonstruktorer);
    }
```


```
Standard konstruktør var kjørt
Ansatt{navn='null', alder=0, hashcode=961}
Ansatt{navn='Per Viskelær', alder=30, hashcode=1395502622}
Ansatt{navn='Atle Patle', alder=32, hashcode=853970873}
Ansatt{navn='Anne Ananas', alder=40, hashcode=-1865919199}
Ansatt{navn='Gunnar', alder=23, hashcode=2042824633}

Antall ganger konstruktører ble brukt: 1
```
