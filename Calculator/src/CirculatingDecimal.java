public class CirculatingDecimal {
    //整数部分
    private BigInt intPart;
    //小数部分
    private int []decimalPart;
    //循环开始处
    private int begin;
    public CirculatingDecimal(BigInt intPart,int[]decimalPart,int begin){
        this.decimalPart=decimalPart;
        this.begin=begin;
        this.intPart=intPart;
    }

    public BigInt getIntPart() {
        return intPart;
    }

}
