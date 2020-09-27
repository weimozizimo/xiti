import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Date: 2019/10/17 11:33
 * @Author: Qtl
 * @Description: 日期工具类
 */
public class DateUtils {

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getDuration(Date startTime, Date endTime) {
        // 持续毫秒数
        Long duration = endTime.getTime() - startTime.getTime();
        // 持续天数
        int dayMillisecond = 1000 * 24 * 60 * 60;
        Long day = duration / dayMillisecond;
        // 持续小时数
        int hourMillisecond = 1000 * 60 * 60;
        Long hour = (duration % dayMillisecond) / hourMillisecond;
        // 持续分钟数
        Long min = ((duration % dayMillisecond) % hourMillisecond) / (1000 * 60);
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取现在时间
     *
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getNowStrDate(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 将时间格式字符串转换为时间
     *
     * @param strDate 2019-09-26 10:38:00
     * @param pattern yyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date strToDate(String strDate, String pattern) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(strDate);
        return date;
    }

    /**
     * 将时间格式字符串转换为时间
     *
     * @param strDate 2019-09-26 10:38:00
     * @param pattern yyy-MM-dd HH:mm:ss
     * @return
     */
    public static Timestamp strToTimestamp(String strDate, String pattern) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(strDate);
        return new Timestamp(date.getTime());
    }

    /**
     * 将时间转换为时间格式字符串
     *
     * @param date
     * @param pattern yyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateStr = formatter.format(date);
        return dateStr;
    }

    /***
     * 校验日期格式
     * @param time
     * @return
     */
    public static boolean isValidDate(String time) {
        boolean flag = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(time);
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }

    /***
     * 计算两个时间段内所有的日期
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getYearList(String startTime, String endTime) {
        List<String> years = new ArrayList<>();
        Integer start = Integer.parseInt(startTime.substring(0, 4));
        Integer end = Integer.parseInt(endTime.substring(0, 4));
        for (int i = start; i <= end; i++) {
            years.add(String.valueOf(i));

        }
        return years;
    }

    /**
     * 根据开始和结束时间计算每一天 集合中每个元素代表一天
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = sdf.parse(startTime);
        Date dEnd = sdf.parse(endTime);
        //存放每一天日期String对象的daysStrList
        List<String> daysStrList = new ArrayList<>();
        //放入开始的那一天日期String
        daysStrList.add(DateUtils.dateToStr(dBegin, "yyyy-MM-dd"));

        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);

        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);

        // 判断循环此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，给定的日历字段增加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            String dayStr = sdf.format(calBegin.getTime());
            daysStrList.add(dayStr);
        }
        return daysStrList;
    }

    /**
     * 根据开始和结束时间计算每一天 集合中每个元素代表一天
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getMonths(String startTime, String endTime) throws ParseException {

        Date dBegin = null;
        Date dEnd = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        dEnd = sdf.parse(endTime);
        dBegin = sdf.parse(startTime);
        //存放每一天日期String对象的daysStrList
        List<String> monthsStrList = new ArrayList<>();
        //放入开始的那一天日期String
        monthsStrList.add(DateUtils.dateToStr(dBegin, "yyyy-MM"));

        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);

        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);

        // 判断循环此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，给定的日历字段增加或减去指定的时间量
            calBegin.add(Calendar.MONTH, 1);
            String dayStr = sdf.format(calBegin.getTime());
            monthsStrList.add(dayStr);
        }
        return monthsStrList;
    }

    /**
     * 获取某年某月有多少天
     *
     * @param year  年
     * @param month 月
     * @return 某年某月有多少天
     */
    public static Integer getDaysOfMonth(Integer year, Integer month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);
        return c.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取某年有多少天
     *
     * @param year 年
     * @return 某年有多少天
     */
    public static Integer getDaysOfYear(Integer year) {
        Calendar d = Calendar.getInstance();
        d.set(Calendar.YEAR, year);
        return d.getActualMaximum(Calendar.DAY_OF_YEAR);
    }


    /**
     * 功能描述: 日期转为字符串，默认yyyy-MM-dd
     */
    public static String dateToString(Date d) {
        return dateToString(d, "yyyy-MM-dd");
    }

    /**
     * 日期转为字符串，自定义格式
     */
    public static String dateToString(Date d, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(d);
    }

    /**
     * 功能描述: 字符串转为日期，自定义格式
     */
    public static Date stringToDate(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date d = sdf.parse(str);
            return d;
        } catch (Exception e) {
        }
        return new Date();
    }


    /**
     * <p>
     * 获取当前系统时间的小时数
     * </p>
     */
    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到当前时间分钟
     */
    public static int getCurrentMinute() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取两个日期的时间差(5天)
     *
     * @param past
     * @return
     */
    public static int getTwoDaydiff(String fromDay, String toDay) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        long from = 0;
        long to = 0;
        try {
            from = simpleFormat.parse(fromDay).getTime();
            to = simpleFormat.parse(toDay).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int days = (int) ((to - from) / (1000 * 60 * 60 * 24));
        return days;
    }

    public static String addDateMinute(String day, int minute) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // if (date == null)
        //   return "";
        //System.out.println("front:" + format.format(date)); //显示输入的日期  
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);// 24小时制   
        date = cal.getTime();
        //System.out.println("after:" + format.format(date));  //显示更新后的日期 
        cal = null;
        //System.out.println(format.format(date));
        return format.format(date);

    }

    //获取某个月之后的月份
    public static String addDateMonth(String startTime, int i) {
        int year = Integer.valueOf(startTime.substring(0, 4));
        int month = Integer.valueOf(startTime.substring(5, 7));
        int afterYear = 0;
        int afterMonth = 0;

        afterMonth = month + i;
        int yearCha = (afterMonth - 1) / 12;
        afterYear = year + yearCha;
        afterMonth = afterMonth % 12;
        String newStartTime = null;
        if (afterMonth == 0) {
            newStartTime = String.valueOf(afterYear) + "-12";
        } else if (afterMonth < 10) {
            newStartTime = String.valueOf(afterYear) + "-0" + String.valueOf(afterMonth);
        } else {
            newStartTime = String.valueOf(afterYear) + "-" + String.valueOf(afterMonth);
        }
        System.out.println(newStartTime);
        return newStartTime;

    }

    /**
     * 当月第一天
     *
     * @param date 天
     * @return 天
     * @throws ParseException 异常
     */
    public static String getFirstDayToMonth(Date date) throws ParseException {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String time = format.format(date);
        String firstDay = time + "-01";
        return firstDay;
    }

    /**
     * 获取当月的最后一天
     */

    public static String getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设定当前时间为每月一号
        // 当前日历的天数上-1变成最大值 , 此方法不会改变指定字段之外的字段
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        return dateToStr(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 当年第一天
     *
     * @param date 天
     * @return 天
     * @throws ParseException 异常
     */
    public static String getFirstDayToYear(Date date) throws ParseException {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String time = format.format(date);
        String firstDay = time + "-01-01";
        return firstDay;
    }

    /**
     * 获取当年的最后一天
     */
    public static String getLastDayOfYear(Date date) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        int last = cal.getActualMaximum(Calendar.DAY_OF_YEAR);

        cal.set(Calendar.DAY_OF_YEAR, last);

        return dateToStr(cal.getTime(), "yyyy-MM-dd");

    }
    
    /** 
     * 获取过去某天的未来或者之前几 天的日期 
     * @param past 
     * @return 
     */  
    public static String getSpecifiedDay(String specifieDay,int past) {  
 	   Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifieDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+past);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        //System.out.println(dayBefore);
        return dayBefore;
    }
    
    /** 
     * 获取上个月
     * @param past 
     * @return 
     */  
    public static String getLastMonth(String date) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    	
    	Calendar c = Calendar.getInstance();
    	
    	try {
			c.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	c.add(Calendar.MONTH, -1);
    	Date m = c.getTime();
    	String mon = format.format(m);
    	return mon;
    }
}
