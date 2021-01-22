public class Ploscice {
    private int indexX1;
    private int indexY1;
    private int indexX2;
    private int indexY2;
    private int sum;

    public Ploscice(int indexX1, int indexY1, int indexX2, int indexY2, int sum) {
        this.indexX1 = indexX1;
        this.indexY1 = indexY1;
        this.indexX2 = indexX2;
        this.indexY2 = indexY2;
        this.sum = sum;
    }

    public int getIndexX1() {
        return indexX1;
    }

    public void setIndexX1(int indexX1) {
        this.indexX1 = indexX1;
    }

    public int getIndexY1() {
        return indexY1;
    }

    public void setIndexY1(int indexY1) {
        this.indexY1 = indexY1;
    }

    public int getIndexX2() {
        return indexX2;
    }

    public void setIndexX2(int indexX2) {
        this.indexX2 = indexX2;
    }

    public int getIndexY2() {
        return indexY2;
    }

    public void setIndexY2(int indexY2) {
        this.indexY2 = indexY2;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Ploscice{" +
                "indexX1=" + indexX1 +
                ", indexY1=" + indexY1 +
                ", indexX2=" + indexX2 +
                ", indexY2=" + indexY2 +
                ", sum=" + sum +
                '}';
    }
}
