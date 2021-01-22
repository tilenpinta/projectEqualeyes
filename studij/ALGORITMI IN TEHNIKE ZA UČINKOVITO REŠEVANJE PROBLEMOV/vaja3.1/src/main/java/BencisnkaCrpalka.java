import java.util.*;

public class BencisnkaCrpalka implements Comparable {
    int lokacija;
    int radius;
    int zacetek;
    int konec;

    public BencisnkaCrpalka(int lokacija, int radius) {
        this.lokacija = lokacija;
        this.radius = radius;
        zacetek = lokacija - radius;
        konec = lokacija + radius;
    }

    @Override
    public int compareTo(Object o) {
        int compareZ = ((BencisnkaCrpalka)o).getZacetek();
        return this.zacetek-compareZ;
        //return  compareZ-this.zacetek;
    }

    public int getLokacija() {
        return lokacija;
    }

    public void setLokacija(int lokacija) {
        this.lokacija = lokacija;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getZacetek() {
        return zacetek;
    }

    public void setZacetek(int zacetek) {
        this.zacetek = zacetek;
    }

    public int getKonec() {
        return konec;
    }

    public void setKonec(int konec) {
        this.konec = konec;
    }

    @Override
    public String toString() {
        return "BencisnkaCrpalka{" +
                "lokacija=" + lokacija +
                ", radius=" + radius +
                ", zacetek=" + zacetek +
                ", konec=" + konec +
                '}';
    }
}
