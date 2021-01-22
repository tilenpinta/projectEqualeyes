import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class delno {
    public static int dolzinaL;
    public static int steviloG;
    public static List<BencisnkaCrpalka> bencisnkaCrpalkaListVse = new ArrayList<BencisnkaCrpalka>();
    public static Stack<BencisnkaCrpalka> bencisnkaCrpalkaListUporabne = new Stack<>();

    //branje datoteke
    public static void readNums(Path file) throws IOException {
        Scanner scanner = new Scanner(file);
        if(scanner.hasNextLine()) {
            dolzinaL = scanner.nextInt();
        }
        if(scanner.hasNextLine()) {
            steviloG = scanner.nextInt();
        }

        int prvo = 0;
        int drugo = 0;

        if (scanner.hasNextFloat()) {
            for(int i = 0; i < steviloG; i++) {
                for(int j = 0; j < 2; j++) {
                    if(j == 0) {
                        prvo = scanner.nextInt();
                    }
                    else {
                        drugo = scanner.nextInt();
                    }
                }
                BencisnkaCrpalka bencisnkaCrpalka = new BencisnkaCrpalka(prvo, drugo);
                bencisnkaCrpalkaListVse.add(bencisnkaCrpalka);
            }
        } else {
            scanner.next();
        }
    }

    public static int howMany() {
        //doda prvoga
        bencisnkaCrpalkaListUporabne.push(bencisnkaCrpalkaListVse.get(0));

        for (int i = 1; i < steviloG; i++) {
            BencisnkaCrpalka trenutna = bencisnkaCrpalkaListVse.get(i);
            BencisnkaCrpalka prejsnja = bencisnkaCrpalkaListUporabne.get(bencisnkaCrpalkaListUporabne.size()-1);
            //preveri ci je prejsnje konec stika ali vecji od trenutnoga zacetka
            // && ce je prejsnji konec vecji od trenutnoga konca da nebi vzel manjsi radius
            // && dolzina se stavi
            if(prejsnja.getKonec() >= trenutna.getZacetek() && prejsnja.getKonec() < trenutna.getKonec() && prejsnja.getKonec() <= dolzinaL)
            {
                //preveri predprejsnjega ce je trenutni interval boljsi in ga zbrise
                if(bencisnkaCrpalkaListUporabne.size() >= 2) {
                    if(bencisnkaCrpalkaListUporabne.get(bencisnkaCrpalkaListUporabne.size()-2).getKonec() >= trenutna.getZacetek()
                            && bencisnkaCrpalkaListUporabne.get(bencisnkaCrpalkaListUporabne.size()-2).getKonec() < trenutna.getKonec()) {
                        bencisnkaCrpalkaListUporabne.pop();
                    }
                }
                bencisnkaCrpalkaListUporabne.push(bencisnkaCrpalkaListVse.get(i));
            }
        }
        //preveri zacetek pa konec ce je v tun polje
        if(bencisnkaCrpalkaListVse.get(0).getZacetek() <= 0 && bencisnkaCrpalkaListUporabne.get(bencisnkaCrpalkaListUporabne.size()-1).getKonec() >= dolzinaL) {
            //kul
            return bencisnkaCrpalkaListVse.size()-bencisnkaCrpalkaListUporabne.size();
        }
        //nega poti
        return -1;
    }

    public static void main (String[] args) throws IOException {
        Path filePath = Paths.get(args[0]);

        readNums(filePath);

        //sort
        //Collections.sort(bencisnkaCrpalkaListVse);
        //sort prvo po zacetke te pa po konec decreasing
        Collections.sort(bencisnkaCrpalkaListVse, Comparator.comparing(BencisnkaCrpalka::getZacetek).thenComparing(Comparator.comparing(BencisnkaCrpalka::getKonec).reversed()));
        int p;
        p = howMany();
        System.out.println(p);
        /*
        for (int i = 0; i < bencisnkaCrpalkaListUporabne.size(); i++) {
            //if(bencisnkaCrpalkaListVse.get(i).getKonec() > 32000 && bencisnkaCrpalkaListVse.get(i).getKonec() < 44000) {
            //System.out.println(bencisnkaCrpalkaListVse.get(i).toString());
            //System.out.println(bencisnkaCrpalkaListUporabne.get(i).toString());
        }
         */
    }
}
