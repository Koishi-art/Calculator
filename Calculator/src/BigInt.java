/**
 * BigInt类
 * 不提供改变BigInt实例所代表的值的函数
 * */

public class BigInt {
    //10位是int,long的最大位数
    //规定0是个位，1是十位
    private int[]number=new int[10];
    private boolean isNegative,isZero;
    public BigInt(int[]number,boolean isNegative){
        this.number=number;this.isNegative=isNegative;
        check0();
        if(number.length<10)enlargeTo(10);
    }
    //可以通过int构造BigInt
    public BigInt(int n){
        if(n==0){isZero=true;}
        if(n<0){isNegative=true;n=n*(-1);}
        int i=0;
        while (n/10>=0&&i<=9){
            number[i++]=n%10;
            n=n/10;
        }
        check0();
    }
    //可以通过long构造BigInt
    public BigInt(long n){
        if(n==0){isZero=true;}
        if(n<0){isNegative=true;n=n*(-1);}
        int i=0;
        while (n/10>=0&&i<=9){
            number[i++]= (int) (n%10);
            n=n/10;
        }
        check0();
    }
    //检查自身是否为0的函数
    public void check0(){
        for (int i=number.length-1;i>=0;i--){if(number[i]!=0){isZero=false;return;}}
        isZero=true;isNegative=false;
    }
    //打印自身的函数
    public void print(){
        if(isNegative)System.out.print('-');
        int i=number.length-1;
        while (number[i]==0&&i>0)i--;
        while (i>=0)System.out.print(number[i--]);
    }
    public String toString(){
        int i=number.length-1;
        while (number[i]==0&&i>0)i--;
        char[] n=new char[i+2];
        if(isNegative){n[0]='-';}else {n[0]='+';}
        for (int nn=1;i>=0;i--,nn++){
            n[nn]=(char)(number[i]+48);
        }
        String aaaa=new String(n);
        return aaaa;
    }
    //拓展函数，增大number的储存空间
    public void enlargeTo(int n){
        if(n<number.length){
            System.out.println("error! the n you enter smaller than array's len!");
            return;
        }
        int[] nn =new int[n];
        System.arraycopy(number, 0, nn, 0, number.length);
        this.number=nn;
    }

    //仅仅保留自身有效长度+1的空间但不小于10
    public void lessen(){
        if(number.length<=10)return;
        int i=number.length-1;
        while (number[i]!=0)i--;
        i++;
        int []n=new int[i];
        for(int k=0;k<i;k++)n[k]=number[k];
        number=n;
    }
    //len函数返回长度
    public int getStorageSpace(){return this.number.length;}
    public int[] getNumber(){return this.number;}

    public boolean isNegative() {
        return isNegative;
    }

    public boolean isZero() {
        return isZero;
    }
}
