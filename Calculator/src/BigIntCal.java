import java.util.Stack;

/**
 * BigIntCal类
 * 负责计算BigInt的
 * 加
 * 减
 * 乘
 * 除
 * 求余
 * */

public class BigIntCal {

    
    //返回bigInt的位数
    public int ws(BigInt a){
        int []t=a.getNumber();
        for(int i=a.getStorageSpace()-1;i>=0;i--){
            if(t[i]!=0)return i+1;
        }
        return 1;
    }
    //两个数的绝对值相加
    public BigInt absAdd(BigInt a,BigInt b){
        if(a.isZero())return b;
        if(b.isZero())return a;
        //统一大小
        if(a.getStorageSpace()<b.getStorageSpace())a.enlargeTo(b.getStorageSpace());
        if(a.getStorageSpace()>b.getStorageSpace())b.enlargeTo(a.getStorageSpace());
        int len=a.getStorageSpace()+1;
        int []cNum=new int[len];
        int []aNum=a.getNumber();
        int []bNum=b.getNumber();
        //模仿人算
        boolean BiggerThanTenBefore=false;//之前有没有进位
        int i=0;//从个位开始
        while (i<len-1){
            int t=aNum[i]+bNum[i];
            if(!BiggerThanTenBefore){
                if(t<10){BiggerThanTenBefore=false;cNum[i++]=t;}
                else {BiggerThanTenBefore=true;cNum[i++]=t-10;}
            }else {
                t++;
                if(t<10){BiggerThanTenBefore=false;cNum[i++]=t;}
                else {BiggerThanTenBefore=true;cNum[i++]=t-10;}
            }
        }
        BigInt c=new BigInt(cNum,false);
        return c;
    }
    //两个数的绝对值大小比较
    public boolean absBiggerOrEqual(BigInt a,BigInt b){
        //统一大小
        if(a.getStorageSpace()<b.getStorageSpace())a.enlargeTo(b.getStorageSpace());
        if(a.getStorageSpace()>b.getStorageSpace())b.enlargeTo(a.getStorageSpace());
        int []aa=a.getNumber();int []bb=b.getNumber();
        for (int i=a.getStorageSpace()-1;i>=0;i--){if(aa[i]>bb[i])return true;if(aa[i]<bb[i])return false;}
        //equal
        return true;
    }
    public boolean absEqual(BigInt a,BigInt b){
        //统一大小
        if(a.getStorageSpace()<b.getStorageSpace())a.enlargeTo(b.getStorageSpace());
        if(a.getStorageSpace()>b.getStorageSpace())b.enlargeTo(a.getStorageSpace());
        int []aa=a.getNumber();int []bb=b.getNumber();
        for (int i=a.getStorageSpace()-1;i>=0;i--){if(aa[i]>bb[i])return false;if(aa[i]<bb[i])return false;}
        //equal
        return true;
    }
    public boolean absBigger(BigInt a,BigInt b){
        if(absBiggerOrEqual(a,b)&&!absEqual(a,b))return true;
        return false;
    }
    public boolean bigger(BigInt a,BigInt b){
        if(a.isNegative()==b.isNegative()){
            if(a.isNegative()){
                if(absBigger(a,b))return false;
                return true;
            }else {
                if(absBigger(a,b))return true;
                return false;
            }
        }
        if(a.isNegative())return false;
        return true;
    }
    public boolean equal(BigInt a,BigInt b){
        if(a.isNegative()==b.isNegative()&&absEqual(a,b))return true;
        return false;
    }

    //两个数的绝对值之差的绝对值
    public BigInt absSub(BigInt a,BigInt b) {
        //统一大小
        if (a.getStorageSpace() < b.getStorageSpace()) a.enlargeTo(b.getStorageSpace());
        if (a.getStorageSpace() > b.getStorageSpace()) b.enlargeTo(a.getStorageSpace());
        //确保大小
        BigInt biggerOne, anther;
        if (absBiggerOrEqual(a, b)) {
            biggerOne = a;
            anther = b;
        } else {
            biggerOne = b;
            anther = a;
        }
        //模拟手算
        //+1是考虑到999+999=1998加一位 9999+9999=19998也是+1位
        int len = anther.getStorageSpace() + 1;
        int[] result = new int[len];
        int[] biggerArray = biggerOne.getNumber();
        int[] antherArray = anther.getNumber();
        //之前有没有借10，一开始肯定是没有的
        boolean isSmaller = false;
        //当前的位数是个位
        int i = 0;
        while (i < len - 1) {
            int t = biggerArray[i] - antherArray[i];
            if (isSmaller) {
                t--;
                if (t < 0) {
                    isSmaller = true;
                    result[i++] = t + 10;
                } else {
                    isSmaller = false;
                    result[i++] = t;
                }
            } else {
                if (t < 0) {
                    isSmaller = true;
                    result[i++] = t + 10;
                } else {
                    isSmaller = false;
                    result[i++] = t;
                }
            }
        }
        return new BigInt(result, false);
    }
    //相反数函数
    public BigInt opp(BigInt n){
        return new BigInt(n.getNumber(),!(n.isNegative()));
    }
    public BigInt add(BigInt a,BigInt b){
        //-- = - (++)
        if(a.isNegative()&&b.isNegative()){return new BigInt(absAdd(a,b).getNumber(),true);}
        //++ = +(+ +)
        if(!(a.isNegative())&&!(b.isNegative())){return new BigInt(absAdd(a,b).getNumber(),false);}
        //-+
        if(a.isNegative()){
            if(absBiggerOrEqual(a,b))return new BigInt(absSub(a,b).getNumber(),true);
            else return new BigInt(absSub(b,a).getNumber(),false);
        }
        //+-
        if(absBiggerOrEqual(a,b))return new BigInt(absSub(a,b).getNumber(),false);
        return new BigInt(absSub(b,a).getNumber(),true);
    }
    public BigInt sub(BigInt a,BigInt b){
        //-+
        if(a.isNegative()&&!b.isNegative()){
            //System.out.println("(-a)-(+b) is -(a+b)");
            return new BigInt(absAdd(a,b).getNumber(),true);
        }
        //+-
        if(!a.isNegative()&&b.isNegative()){
           // System.out.println("(+a)-(-b) is +(a+b)");
            return new BigInt(absAdd(a,b).getNumber(),false);
        }
        //++
        if(!a.isNegative()&&!b.isNegative()){
            //a.print();b.print();
            //System.out.println(absBiggerOrEqual(a,b));
            if(absBiggerOrEqual(a,b)){
               // System.out.println("(+a)-(+b) a is bigger one");
                return new BigInt(absSub(a,b).getNumber(),false);
            }
            //System.out.println("(+a)-(+b) b is bigger one");
            return new BigInt(absSub(b,a).getNumber(),true);
        }
        //--
        //System.out.println("(-a)-(-b) is |-b|-|-a|");
        if(absBiggerOrEqual(a,b))return new BigInt(absSub(a,b).getNumber(),true);
        return new BigInt(absSub(b,a).getNumber(),false);
    }
    //乘10的某次方算法，加个0
   public BigInt multi10(BigInt a,int n){
        if(a.isZero())return new BigInt(0);
        //冗余设计
        int[]number=new int[a.getStorageSpace()+n+2];
        int[]aNum=a.getNumber();
        for(int i=0;i<n;i++)number[i]=0;
        for(int i=n,k=0;k<aNum.length;i++,k++){
            number[i]=aNum[k];
        }
        return new BigInt(number,a.isNegative());
    }
    //乘一个10以内非负整数,实际上也可以乘任意一个非负数，不用循环做纯粹为了性能
    public BigInt multiplyWithNumberSmaller10(BigInt a,int n){
        if(n==0){return new BigInt(0);}
        BigInt result=a;int i=n;
        while (i>1){
            result=add(result,a);
            i-=1;
        }
        return result;
    }
    //乘法本体，利用乘法分配律，这也是手算的原理
    public BigInt multiply(BigInt a, BigInt b){
        //统一大小
        if (a.getStorageSpace() < b.getStorageSpace()) a.enlargeTo(b.getStorageSpace());
        if (a.getStorageSpace() > b.getStorageSpace()) b.enlargeTo(a.getStorageSpace());
        //反向表示的优势出来了
        int []aNum=a.getNumber();
        int []bNum=b.getNumber();
        //两个数组的长度
        int len= aNum.length;
        //b的位数
        int bws=ws(b);
        //结果的储存
        BigInt []resultNum=new BigInt[len];
        /**
         * 123*223=
         * 123*100*2+123*10*2+123*3
         * 储存的样子
         * ->
         * 123（保留bigint的样子） 32200...0
         * 123乘3再乘10的0次方
         * 放在BigInt的数组中
         * 0就是个位，对应次方
         * 超了b的位数就是结果为0
         * 最后求和
         * */

        for(int i=0;i<bws;i++){
            BigInt c=multiplyWithNumberSmaller10(a,bNum[i]);
            resultNum[i]=multi10(c,i);
        }
        BigInt result=new BigInt(0);
        for (int i=0;i<bws;i++)result=add(resultNum[i],result);
        if(b.isNegative()!=a.isNegative())return new BigInt(result.getNumber(),true);
        return new BigInt(result.getNumber(),false);
    }


    //模拟手算
    public BigInt divide(BigInt dividend,BigInt divisor){
        //被除数和除数
        int []absDividend=dividend.getNumber();
        int []absDivisor=divisor.getNumber();
        //得到被除数的位数，确定被除数的有效位置
        int wsOfDividend=ws(dividend);
        //当前位置1表示当前截取的被除数的一个片段的高位对应的数组索引，2表示低位
        //一开始在相同的位置表示他们截取的是一个一位数
        int currentPosition1=wsOfDividend;
        int currentPosition2=currentPosition1;
        //这个栈的作用下文解释
        Stack<Integer>storeResult=new Stack<>();
        //差值，作用下文解释
        BigInt DifferentValue=new BigInt(0);
        //下界的底线是个位
       A: while (currentPosition2>=0) {
            //这段数组成的大整数c,冗余设计存储空间,并且要把上一步的差值补到这个大整数的前面，手算第一步可以看做差值为0
            int[] cNum = new int[currentPosition1 - currentPosition2 + ws(DifferentValue)+10];
           System.arraycopy(absDividend,currentPosition2,cNum,0,currentPosition1-currentPosition2+1);
           BigInt jq = new BigInt(cNum, false);//构造出截取的大整数
           //但是要加上差值的10倍
           jq=add(multi10(DifferentValue,1),jq);
            //寻找除数*一个个位数使得是小于这个截取的数的最大值，即从9开始找
            int result = 9;
            //只要比c大就减小result，减到0不要怕，高位为0用反序表示法没有问题，这里要用栈装起来，先进后出，最后组成反序的结果数组
         B:   while (absBigger(multiplyWithNumberSmaller10(divisor, result), jq)) {
                result--;
                if (result == 0) {
                    //结果减为0要一些特殊处理，这表明我们截取的数不过大
                    //扩大截取的数要让下界变小
                    currentPosition2--;

                    //无论找没找到非0结果都要放进栈里面
                    storeResult.push(result);

                    //这个过程(截取-寻找)要再来一次，直到找到了非0结果
                    continue A;
                }
            }
            //要得到截取的数和除数*result的差
            DifferentValue=sub(jq,multiplyWithNumberSmaller10(divisor,result));

            //无论找没找到非0结果都要放进栈里面
            storeResult.push(result);
            //并且要重置上下界截取新的数
            currentPosition1 = currentPosition2 - 1;
            currentPosition2 = currentPosition1;
            //重新开始一切过程
        }
        //此时栈里面的数是一个个放出来就是反序的数表示了
        int []R=new int[storeResult.size()];
        for(int i=0;i<R.length;i++){
            R[i]=storeResult.pop();
        }
        if(dividend.isNegative()!=divisor.isNegative())return new BigInt(R,true);
        return new BigInt(R,false);
    }

    //求余数mod reminder
    public BigInt absMod(BigInt dividend,BigInt divisor){
        BigInt absDividend=new BigInt(dividend.getNumber(),false);
        BigInt absDivisor=new BigInt(divisor.getNumber(),false);
        BigInt c=divide(absDividend,absDivisor);
        BigInt reminder=sub(absDividend,multiply(absDivisor,c));
        return reminder;
    }

}
