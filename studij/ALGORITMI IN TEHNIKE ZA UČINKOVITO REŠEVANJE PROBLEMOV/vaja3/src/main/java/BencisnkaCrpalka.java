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

    public BencisnkaCrpalka dobiNajb(int in, List<BencisnkaCrpalka> list, int newIn) {
        List<BencisnkaCrpalka> similarElements = new ArrayList<>();
        int previousRadiusStart = list.get(in).getZacetek();
        int previousRadiusEnd = list.get(in).getKonec();

        //++index; // premaknemo se na naslednjega

        // ce smo prisli do konca ali pa je interval prekinjen
        ++in;
        if (in < list.size()) {
            if (list.get(in).getZacetek() <= previousRadiusEnd) {
                // sproti preverjamo ce pridemo do konca  && do konca radiusa prejsnje crpalke
                while (in < list.size() && list.get(in).getZacetek() <= previousRadiusEnd) {
                    similarElements.add(list.get(in));
                    ++in;
                }
            }
		else { // ce smo ze prisli do konca ali pa je interval prekinjen, potem nullptr
                return null;
            }

        }
        else {
            Main.ifEnd = true;
            return null;
        }

        HashMap<BencisnkaCrpalka, Integer> hashMap = new HashMap<>();

        for (int k = 0; k < similarElements.size(); k++)
            hashMap[similarElements.get(k)] = k;

        std::sort(similarElements.begin(), similarElements.end(), [](GasStation* a, GasStation* b)-> bool {
            return a->getRadiusEnd() > b->getRadiusEnd();
        });


        BencisnkaCrpalka gasStation = similarElements.get(0);
        //newIn = hashMap..compute(gasStation);


        return gasStation;
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
