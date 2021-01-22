import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static int steviloN;
    public static int steviloK;
    public static int[][] array2d;

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

    public static void main (String[] args) throws IOException {
        Path filePath = Paths.get(args[0]);

        readNums(filePath);

        System.out.println(steviloN + " " + steviloK);

        for(int i = 0; i < steviloN; i++) {
            System.out.println(array2d[i][0] + " " + array2d[i][1] + " " + array2d[i][2]);
        }

        List<Integer> arrayList = new ArrayList<Integer>();
        List<Integer> arrayListPoped = new ArrayList<Integer>();
        List<Integer> arrayListBiggest = new ArrayList<Integer>();
        int sum = 0;

        for(int i = 0; i < steviloN; i++) {
            for (int j = 0; j < 3; j++) {
                if(i != steviloN - 1) {
                    if(j != 2){
                        int sumJ = array2d[i][j] + array2d[i][j + 1];
                        int sumI = array2d[i][j] + array2d[i + 1][j];
                        if(sumJ > sumI) {
                            arrayList.add(sumJ);
                        }
                        else {
                            arrayList.add(sumI);
                        }
                    }
                    else {
                        int sumI = array2d[i][j] + array2d[i + 1][j];
                        arrayList.add(sumI);
                    }
                }
                else {
                    if(j != 2){
                        int sumJ = array2d[i][j] + array2d[i][j + 1];
                        arrayList.add(sumJ);
                    }
                }
            }
        }
        System.out.println(arrayList.toString());

        for(int i = 0; i < arrayList.size(); i++) {
            arrayList.remove(Collections.min(arrayList));
        }

        for(int i = 0; i < steviloK; i++) {
            arrayListBiggest.add(Collections.max(arrayList));
            arrayList.remove(Collections.max(arrayList));
            sum += arrayListBiggest.get(i);
        }


        System.out.println(arrayListBiggest.toString());

        System.out.println(sum);
    }
}
