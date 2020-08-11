
public class Main {
    public static void main(String[]args) {
        BigIntCal cal=new BigIntCal();
        for (int i=0;i<1000;i++){
            int A=(int)(Math.random()*10000);
            int B=(int)(Math.random()*100);
            if(B==0){i--;continue;}
            BigInt a=new BigInt(A);
            BigInt b=new BigInt(B);
            System.out.println(A+" / "+B+" = " +cal.absMod(a,b).toString()+" "+(A%B));
        }
    }
}
