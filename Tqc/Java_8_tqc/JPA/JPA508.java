public class JPA508 {
    public static void main(String[] argv) {
        int[] data = {2, 4, 3, 5, 7, 6, 9, 1};
        while(true) {
            boolean con = true;
            for(int i=1;i<data.length;i++){
                if(data[i] < data[i-1]){
                    int temp = data[i-1];
                    data[i-1] = data[i];
                    data[i] = temp;
                    con = false;
                }
            }
            if(con)break;
            for (int i : data) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}