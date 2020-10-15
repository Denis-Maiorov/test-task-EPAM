package estimation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tools {

    //отбрасывает лишние числа после запятой, второй параметр кол-во необходимое после запятой.
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //проверяет выходит ли за рамки и преобразует
    public static double areaProgressBar(double value){
        if (value <= 0){
            value = 0;
        }
        if (value >= 1){
            value = 1;
        }
        return value;
    }
}
