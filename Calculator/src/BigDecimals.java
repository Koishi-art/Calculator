public class BigDecimals {
    //分母
    private BigInt denominator;
    //分子
    private BigInt numerator;
    //近似值
    private double approximateValue;
    //循环小数式
    private CirculatingDecimal circulatingDecimal;
    public  BigDecimals(BigInt denominator,BigInt numerator){
        this.denominator=denominator;
        this.numerator=numerator;
    }

    public BigInt getDenominator() {
        return denominator;
    }

    public BigInt getNumerator() {
        return numerator;
    }


}
