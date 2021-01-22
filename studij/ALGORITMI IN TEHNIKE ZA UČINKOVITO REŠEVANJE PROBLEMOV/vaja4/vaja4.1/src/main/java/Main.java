import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static int steviloN;
    public static int steviloK;
    public static int[][] array2d;
    public static List<Ploscice> arrayPlosciceHorizontal = new ArrayList<>();
    public static List<Ploscice> arrayPlosciceVertical = new ArrayList<>();
    public static List<Ploscice> arrayPlosciceFinal = new ArrayList<>();
    public static List<Ploscice> arrayPlosciceFinalCopy = new ArrayList<>();
    public static List<Ploscice> arrayPlosciceFinalFinal = new ArrayList<>();
    public static List<Ploscice> arrayPlosciceFinalFinalFinal = new ArrayList<>();


    //branje
    public static void readNums(Path file) throws IOException {
        Scanner scanner = new Scanner(file);
        if(scanner.hasNextLine()) {
            steviloN = scanner.nextInt();
        }
        if(scanner.hasNextLine()) {
            steviloK = scanner.nextInt();
        }

        array2d = new int[steviloN][3];

        int prvo = 0;
        int drugo = 0;

        if (scanner.hasNextFloat()) {
            for(int i = 0; i < steviloN; i++) {
                for(int j = 0; j < 3; j++) {
                    array2d[i][j] = scanner.nextInt();
                }
            }
        } else {
            scanner.next();
        }
    }

    public static void findAllHorizontal(){
        for (int i=0; i < steviloN; i++) {
            for (int j=0; j<2; ++j) {
                arrayPlosciceHorizontal.add(new Ploscice(i, j, i, j + 1, array2d[i][j] + array2d[i][j + 1]));
            }
        }
    }

    public static void findAllVertical(){
        for (int j=0; j<3; j++) {
            for (int i=0; i<steviloN-1; i++) {
                arrayPlosciceVertical.add(new Ploscice(i, j, i+1, j, array2d[i][j] + array2d[i + 1][j]));
            }
        }
    }

    static Ploscice[] getSubset(List<Ploscice> input, int[] subset) {
        Ploscice[] result = new Ploscice[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input.get(subset[i]);
        return result;
    }

    public static void main (String[] args) throws IOException {
        Path filePath = Paths.get(args[0]);

        readNums(filePath);

        System.out.println(steviloN + " " + steviloK);

        for(int i = 0; i < steviloN; i++) {
            System.out.println(array2d[i][0] + " " + array2d[i][1] + " " + array2d[i][2]);
        }

        findAllHorizontal();
        findAllVertical();

        //horizontalBest
        for(int i = 0; i < arrayPlosciceHorizontal.size(); i = i + 2) {
            if (arrayPlosciceHorizontal.get(i).getSum() > arrayPlosciceHorizontal.get(i + 1).getSum()) {
                arrayPlosciceFinal.add(arrayPlosciceHorizontal.get(i));
            }
            else {
                arrayPlosciceFinal.add(arrayPlosciceHorizontal.get(i+1));
            }
        }

        //vertical
        int ff = 0;
        if(steviloN % 2 == 0) {
            ff = 1;
        }

        for(int i = 0; i < arrayPlosciceVertical.size() - ff; i = i + 2) {
            if (arrayPlosciceVertical.get(i).getSum() > arrayPlosciceVertical.get(i + 1).getSum()) {
                arrayPlosciceFinal.add(arrayPlosciceVertical.get(i));
            }
            else {
                arrayPlosciceFinal.add(arrayPlosciceVertical.get(i+1));
            }
        }

        arrayPlosciceFinalCopy.addAll(arrayPlosciceFinal);


        //allahhhhhhh
        int o = steviloK;                             // sequence length

        List<Ploscice[]> subsets = new ArrayList<>();

        int[] s = new int[o];                  // here we'll keep indices
        // pointing to elements in input array

        if (o <= arrayPlosciceFinalCopy.size()) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (s[i] = i) < o - 1; i++);
            subsets.add(getSubset(arrayPlosciceFinalCopy, s));
            for(;;) {
                int i;
                // find position of item that can be incremented
                for (i = o - 1; i >= 0 && s[i] == arrayPlosciceFinalCopy.size() - o + i; i--);
                if (i < 0) {
                    break;
                }
                s[i]++;                    // increment this item
                for (++i; i < o; i++) {    // fill up remaining items
                    s[i] = s[i - 1] + 1;
                }
                subsets.add(getSubset(arrayPlosciceFinalCopy, s));
            }
        }

        int sum[] = new int[subsets.size()];
        Arrays.fill(sum, 0);

        for(int i = 0; i < subsets.size(); i++) {
            arrayPlosciceFinalFinalFinal.add(subsets.get(i)[0]);
            sum[i] += arrayPlosciceFinalFinalFinal.get(0).getSum();
            for(int j = 1; j < steviloK; j++) {
                if(((subsets.get(i)[j-1].getIndexX1() != subsets.get(i)[j].getIndexX1())
                        && (subsets.get(i)[j-1].getIndexY1() != subsets.get(i)[j].getIndexY1()))) {
                    if ((subsets.get(i)[j-1].getIndexX2() != subsets.get(i)[j].getIndexX2())
                            && (subsets.get(i)[j-1].getIndexY2() != subsets.get(i)[j].getIndexY2())) {

                        arrayPlosciceFinalFinalFinal.add(subsets.get(i)[j]);

                        sum[i] += arrayPlosciceFinalFinalFinal.get(arrayPlosciceFinalFinalFinal.size()-1).getSum();
                    }
                }
            }
            arrayPlosciceFinalFinalFinal.clear();

        }

        int summ = sum[0];
        for(int i = 1; i < sum.length; i++) {
            if(sum[i] > summ) {
                summ = sum[i];
            }
        }

        System.out.println(summ);

// generate actual subset by index sequence




        //alaahhhhhhhhhhhhhhhh

/*
        System.out.println("busaasasasaasasas");

        for(int i = 0; i < arrayPlosciceFinalCopy.size(); i++) {
            System.out.println(arrayPlosciceFinalCopy.get(i).toString());
        }


        System.out.println("hhhhhhhhhhh");
        List<Integer> sumFinal = new ArrayList<>();

        int fff = 1;
        if(arrayPlosciceFinalFinalFinal.size() % 2 == 0) {
            fff = 0;
        }
        for(int i = 0; i < arrayPlosciceFinalCopy.size() - steviloK + 1; i++) {
            System.out.println("i: " + i);
            for(int k = i + 1; k < arrayPlosciceFinalCopy.size(); k++){
                System.out.println("k: " + k);
                for (int j = 0; j < steviloK; j++) {
                    System.out.println("j: " + j);
                    //arrayPlosciceFinalFinalFinal.add(arrayPlosciceFinalCopy.get(j + k - 1));
                    arrayPlosciceFinalFinalFinal.add(arrayPlosciceFinalCopy.get(((j*k)-(i*j) + i)));
                }
            }
            System.out.println();
        }

        System.out.println("wuuuuuuu");
        for(int i = 0; i < arrayPlosciceFinalFinalFinal.size(); i++) {
            System.out.println(arrayPlosciceFinalFinalFinal.get(i).toString());
        }

        System.out.println("uuuuuuuuuuuwuuuuuuu");
        for(int i = 0; i < arrayPlosciceFinalFinalFinal.size(); i++) {

        }

        System.out.println("bbbbbbbbbbbbbbb");
        for(int i = 0; i < arrayPlosciceFinalFinalFinal.size() - steviloK + 1; i = i + steviloK) {

            for(int k = 1; k < steviloK; k++) {
                if(((arrayPlosciceFinalFinalFinal.get(i).getIndexX1() != arrayPlosciceFinalFinalFinal.get(k + i).getIndexX1())
                        && (arrayPlosciceFinalFinalFinal.get(i).getIndexY1() != arrayPlosciceFinalFinalFinal.get(k + i).getIndexY1()))) {
                    if((arrayPlosciceFinalFinalFinal.get(i).getIndexX2() != arrayPlosciceFinalFinalFinal.get(k + i).getIndexX2())
                            && (arrayPlosciceFinalFinalFinal.get(i).getIndexY2() != arrayPlosciceFinalFinalFinal.get(k + i).getIndexY2())) {

                        sumFinal.add(arrayPlosciceFinalFinalFinal.get(i).getSum());
                        sumFinal.set(sumFinal.size() - 1, sumFinal.get(sumFinal.size() - 1) + arrayPlosciceFinalFinalFinal.get(k + i).getSum());
                        //sumFinal.set(k,sumFinal.get(k) + arrayPlosciceFinalFinalFinal.get(k+i).getSum());
                    }
                }
            }
        }

        System.out.println("");
        for(int i = 0; i < sumFinal.size(); i++) {
            System.out.println(sumFinal.get(i));
        }

 */
/*
        System.out.println("cccccccc");
        int summ = 0;
        for(int k = 0; k < steviloK; k++){

            for(int i = 0; i < arrayPlosciceFinalFinal.size(); i++) {
                if(k != i) {
                    if ((arrayPlosciceFinalFinal.get(k).getIndexX1() == arrayPlosciceFinalFinal.get(i).getIndexX1() && arrayPlosciceFinalFinal.get(k).getIndexY1() == arrayPlosciceFinalFinal.get(i).getIndexY1())
                            || (arrayPlosciceFinalFinal.get(k).getIndexX2() == arrayPlosciceFinalFinal.get(i).getIndexX2() && arrayPlosciceFinalFinal.get(k).getIndexY2() == arrayPlosciceFinalFinal.get(i).getIndexY2())) {

                        System.out.println("ssssssssss");
                    } else {
                        System.out.println("dasdasd");
                    }
                }
            }


            summ += arrayPlosciceFinalFinal.get(k).getSum();
            //System.out.println(arrayPlosciceFinalFinal.get(i).toString());
        }
        System.out.println(summ);
    */
    }
}
