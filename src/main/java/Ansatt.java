import java.io.Serializable;
import java.util.Objects;

public class Ansatt implements Cloneable, Serializable {

    public static int antallKjorteKonstruktorer = 0;

    private String navn;
    private int alder;

    public Ansatt() {
        System.out.println("Standard konstruktør var kjørt");
        antallKjorteKonstruktorer++;
    }

    public Ansatt(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
        System.out.println("Konstruktør med parameterliste var kjørt");
        antallKjorteKonstruktorer++;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ansatt employee = (Ansatt) o;
        return alder == employee.alder && Objects.equals(navn, employee.navn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(navn, alder);
    }

    @Override
    public String toString() {
        return "Ansatt{" +
                "navn='" + navn + '\'' +
                ", alder=" + alder +
                ", hashcode=" + hashCode() +
                '}';
    }
}
