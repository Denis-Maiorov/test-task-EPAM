package estimation;

import java.util.Date;

public class DateLogic {

    //разница между времени для определения как давно не был в игре
    public static long resultDate(long date){
        int metabolism = 1;
        Date nowDate = new Date();
        date = ((nowDate.getTime() - date)/60000)/metabolism;
        return date;
    }

    public static long resultDeadDate(long date){
        int value = 1;
        Date nowDate = new Date();
        date = ((nowDate.getTime() - date)/60000)/value;
        return date;
    }

    public static long resultBirthDate(long date){
        //каждые 2 минуты +1 пиксель
        int valueGrowth = 2;
        Date nowDate = new Date();
        date = ((nowDate.getTime() - date)/60000)/valueGrowth;
        //ограничение роста
        if (date > 30){
            date = 30;
        }
        return date;
    }
}
