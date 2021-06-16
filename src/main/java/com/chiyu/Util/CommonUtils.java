package com.chiyu.Util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class CommonUtils {

    /**
     * 当天时间00:00:00
     * @return
     */
    public static String StartDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return format.format(c.getTime());
    }

    /**
     * 当天时间23:59:59
     * @return
     */
    public static String EenDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return format.format(c.getTime());
    }

    /**
     * 当月第一天
     * @return
     */
    public static String StartDaYeDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 当月最后一天
     * @return
     */
    public static String EenDaYeDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 当天前一个月
     * @return
     */
    public static String monthBeforeDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去一月（上个月的今天）
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

    /**
     * 过去的时间
     * @return
     */
    public static void DemoDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天：" + day);

        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月：" + mon);

        //过去三个月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        Date m3 = c.getTime();
        String mon3 = format.format(m3);
        System.out.println("过去三个月：" + mon3);

        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        System.out.println("过去一年：" + year);
    }


    /**
     * 返回指定天数后的指定格式的日期(未来的)
     * @param days
     * @param pattern
     * @return
     */
    public static String getAfterDaysTime(int days, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime1.format(formatter);
    }

    /**
     * "2021-05-25 09:14:27"转成"2021-05-25T01:14:27+08:00"
     * @param regtime
     * @return
     */
    public static String getDaysTime(String regtime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //先转成13位的Java时间戳
        LocalDateTime localDateTime = LocalDateTime.parse(regtime, formatter);
        long l = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //再转成下面的格式
        SimpleDateFormat utcSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Calendar cal = Calendar.getInstance();
        // 取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        l = l - zoneOffset - dstOffset;
        Date UTCDate2 = new Date(l);
        String format2 = utcSdf.format(UTCDate2);
        return format2;
    }
	    /**
     * yyyy-MM-dd'T'HH:mm:ssXXX转为yyyy-MM-dd HH:mm:ss
     * @param regtime
     * @return
     */
    public static String getDaysTime1(String regtime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        //先转成13位的Java时间戳
        LocalDateTime localDateTime = LocalDateTime.parse(regtime, formatter);
        long l = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //再转成下面的格式
        SimpleDateFormat utcSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        // 取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        l = l +zoneOffset + dstOffset;
        Date UTCDate2 = new Date(l);
        String format2 = utcSdf.format(UTCDate2);
        return format2;
    }
	/**
     * 字符串转utf-8
     * @param str
     * @param newCharset
     * @return
     */
    private static String changeCharSet(String str, String newCharset) {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            try {
                return new String(bs, newCharset);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 传入String类型日期(yyyy-MM-dd HH:mm:ss)，返回字符串类型时间（ISO8601标准时间）
     * @param timestamp
     * @return
     */
    public static String getISO8601TimestampFromDateStr(String timestamp) {
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(timestamp, dtf1);
        ZoneOffset offset = ZoneOffset.of("+08:00");
        OffsetDateTime date = OffsetDateTime.of(ldt, offset);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        return date.format(dtf2);
    }

    /**
     * 需要的依赖
     * <dependency>
     * <groupId>joda-time</groupId>
     * <artifactId>joda-time</artifactId>
     * <version>2.9.9</version>
     * </dependency>
     * 传入String类型日期(ISO8601标准时间:yyyy-MM-dd'T'HH:mm:ss.SSS'Z')，返回字符串类型时间(yyyy-MM-dd HH:mm:ss)
     * @param ISOdate
     * @return
     */
    public static String getDateStrFromISO8601Timestamp(String ISOdate) {
        org.joda.time.format.DateTimeFormatter dtf1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTime dt = dtf1.parseDateTime(ISOdate);
        org.joda.time.format.DateTimeFormatter dtf2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        return dt.toString(dtf2);
    }


    /**
     * 传入Data类型日期，返回字符串类型时间（ISO8601标准时间）
     * @param date
     * @return
     */
    public static String getISO8601Timestamp(Date date) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }
    /**
     * 时间戳转日期格式
     * @param ll
     * @return
     */
    public String date(Long ll){
        long l = System.currentTimeMillis();
        LocalDateTime time = LocalDateTime.ofEpochSecond(l / 1000, 0, ZoneOffset.ofHours(8));
        String format = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }
    /**
     * 日期格式转时间戳
     * @param regtime
     * @return
     */
    public Long datea(String regtime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //先转成13位的Java时间戳
        LocalDateTime localDateTime = LocalDateTime.parse(regtime, formatter);
        long l = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        return l;
    }
    public static final String PATTER1="yyyyMMddHHmmssSSS";
    public static final String PATTER2="yyyyMMddHHmmss";
    public static final String PATTER3="yyyyMMdd";

    public static String getTimeByFormat(String format){
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    /**
     * 当前日期+1天
     * @return
     */
    public static Date addOneDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();
        return date;
    }

    /**
     * 获取毫秒级系统时间字符串
     * @return
     */
    public static String getTimeStringByMillisecond(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);//字符串转时间
    }


    /**
     * 获取当天的时间 年月日
     * @return
     */
    public static String getTimeToday(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);//字符串转时间
    }

    /**
     * 获取指定日期格式的当前时间
     * @param pattern
     * @return
     */
    public static String getTimeNowByPattern(String pattern){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);//字符串转时间
    }

    /**
     * 生成指定位数的随机数字符串
     * @param nums
     * @return
     */
    public static String getRandomStrByNums(Integer nums){
        Random r=new Random();
        String result="";
        for(int i=0;i<nums;i++){
            result+=r.nextInt(10);
        }
        return result;
    }



    /**
     * 生成随机数字和字母
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
