package com.comm.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;

public class ClassUtil {
	private static ClassUtil instance = null;

	private ClassUtil() {
	}
	public static ClassUtil getInstance() {
		if (instance == null) {
			instance = new ClassUtil();
		}
		return instance;
	}
	
	public void exeTask(Thread thread,long delay,long period,int times){
		Timer timer = new Timer();
		MyTask myTask = new MyTask(thread);
		if(times>0){
			for (int i = 0; i < times; i++) {
				timer.schedule(myTask, delay+period*i);
			}
		}
	}
	
	public void exeTask(Thread thread,long delay){
		Timer timer = new Timer();
		MyTask myTask = new MyTask(thread);
		timer.schedule(myTask, delay);
	}
	
	class MyTask extends TimerTask{// 任务调度类都要继承TimerTask
    	Thread thread;
    	public MyTask(Thread thread) {
			this.thread = thread;
		}
		@Override
		public void run() {
			this.thread.start();
		}
    }
	
	/**
     * 
     * 获得当前时分，在144位字符串中的下标(下标从0开始)
     * 
     * @param time 字符串
     * @return
     */
    public static int getNum(String time) {
        int min = Integer.parseInt(time.substring(2, 4));
        int hour = Integer.parseInt(time.substring(0, 2));
        
        //结束时间小于5分钟，取00；大于等于5分钟，取5分钟
        if (min%10 < 5) {
            min = (int) Math.round(min / 10.0) * 10;
        } else {
            min = (int) Math.round(min / 10.0) * 10 - 5;
        } 
        
        return getNum(hour,min);
    }
    
    /**
     * 
     * 获得当前时分，在144位字符串中的下标(下标从0开始)
     * 
     * @param time 整型
     * @return
     */
    private static int getNum(int curTime) {
        int hour = curTime/100;
        int min = curTime - hour*100;
        return getNum(hour, min);
    }
    
    /**
     * 
     * @param hour 小时
     * @param min  分钟
     * @return
     */
    public static int getNum(int hour, int min) {
        min = min*2;
        hour = (hour*120+min)/10;
        return hour;
    }
    
    /**
     * 获取当前时间的 时分 格式: HHMM
     * 
     * @return 
     */
    public static int getNowHM() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int time = c.get(Calendar.HOUR_OF_DAY);         //获取当前小时
        int min = c.get(Calendar.MINUTE);               //获取当前分钟
        
        String strHour = time < 10 ? "0" + time : "" + time;
        String strMin = min < 10 ? "0" + min : "" + min;
        return getNum(strHour + strMin);
    }
    
    /**
     * 获取当前星期
     * 
     * @return 
     */
    public static String getNowWeek() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            return String.valueOf(7);
        } else {
            return String.valueOf(day - 1);
        }
 
    }
    
    /**
     * 
     * 时分加上时间间隔
     * 
     * @param start
     * @param space
     * @return HHMM
     * 
     */
    public static int addSpace(int start, int space) {
        int hour = start / 100;
        int min = start - hour * 100;
        
        min += space;
        
        if(min >= 60) {
            hour += 1;
            min -= 60;
        } 
        
        return hour * 100 + min;
    }
    
    /**
     * 
     * @param openTime
     * @param endTime
     * @return {start HHMM, end HHMM}
     * 
     * @author yuemr
     * 
     */
    public static String[] caculateTimeStr(String openTime, String endTime) {
        int tmp[] = caculateTime(openTime,endTime);
        StringBuffer sT = new StringBuffer();
        StringBuffer eT = new StringBuffer();
        sT.append(tmp[0]<10?"0"+tmp[0]:""+tmp[0]).append(tmp[1]<10?"0"+tmp[1]:""+tmp[1]);
        eT.append(tmp[2]<10?"0"+tmp[2]:""+tmp[2]).append(tmp[3]<10?"0"+tmp[3]:""+tmp[3]);
        
        String[] result = {sT.toString(), eT.toString()};
        
        return result;
    }
    
    /**
     * 
     * @param openTime
     * @param endTime
     * @return {openh, openm, endh, endm}
     * 
     * @author liweiwei
     */
    public static int[] caculateTime(String openTime, String endTime){
        // 截取开始时的点
        int openh = Integer.parseInt(openTime.substring(0, 2));
        // 截取开始分的点
        int openm = Integer.parseInt(openTime.substring(2, 4));
        // 截取结束时的点
        int endh = Integer.parseInt(endTime.substring(0, 2));
        // 截取结束分的点
        int endm = Integer.parseInt(endTime.substring(2, 4));

        //开始时间小于5分钟，取5分钟；等于5分钟，取5分钟；大于5分钟，取十分钟
        int temp = openm % 10;
        if (temp < 5 && temp > 0) {
            openm = (int) Math.round(openm / 10.0) * 10 + 5;
        } else if (temp == 5) {
            openm = (int) Math.round(openm / 10.0) * 10 - 5;
        } else if (temp > 5) {
            openm = (int) Math.round(openm / 10.0) * 10;
            
            //如果分钟等于60，时进位
            if (openm == 60) {
                openm = 00;
                openh++;
            } 
        }
        //结束时间小于5分钟，取00；大于等于5分钟，取5分钟
        temp=endm%10;
        if (temp < 5) {
            endm = (int) Math.round(endm / 10.0) * 10;
        } else {
            endm = (int) Math.round(endm / 10.0) * 10 - 5;
        } 
        int[] rst = {openh, openm, endh, endm};
        return rst;
    }
    
    /**
     * 把异常中的堆栈信息转化为文字列
     * 
     * @param ex 异常
     * @return 异常信息
     */
    public static String getExceptionInfo(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
    }
    
    /**
     * 生成log文字列
     * 
     * @param account 操作用户
     * @param telNum 操作用户电话
     * @param f_account 被操作用户
     * @param f_telNum 被操作用户电话
     * @return log文字列
     */
    public static String getLogStr(String account, String telNum, String f_account, String f_telNum) {
    	StringBuffer logBuf = new StringBuffer();
    	
    	// 操作用户
    	logBuf.append("account: ");
    	if (account != null && account.trim().length() != 0) {
    		logBuf.append(account);
    	}
    	logBuf.append("   ");
    	
    	// 操作用户电话
    	logBuf.append("telNum: ");
    	if (telNum != null && telNum.trim().length() != 0) {
    		logBuf.append(telNum);
    	}
    	logBuf.append("   ");
    	
    	// 被操作用户
    	logBuf.append("f_account: ");
    	if (f_account != null && f_account.trim().length() != 0) {
    		logBuf.append(f_account);
    	}
    	logBuf.append("   ");
    	
    	// 被操作用户电话
    	logBuf.append("f_telNum: ");
    	if (f_telNum != null && f_telNum.trim().length() != 0) {
    		logBuf.append(f_telNum);
    	}

		return logBuf.toString();
    }
    
    
	/**
	 * iPhone机型判断
	 *
	 * @return 
	 */
    public static boolean isIPhone(HttpServletRequest req) {
		//HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		String userAgent = req.getHeader("User-Agent");
		String hd = req.getHeader("mode");
		if (null != userAgent && !"".equals(userAgent)) {
			if (userAgent.indexOf("iPhone") >= 0 || userAgent.indexOf("iPod") >= 0) {
				return true;
			}
		} else if (null != hd && !"".equals(hd)) {
			if ("iPhone".equals(hd)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * Android机型判断
	 *
	 * @return 
	 */
    public static boolean isAndroid(HttpServletRequest req) {
		//HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		String userAgent = req.getHeader("User-Agent");
		String hd = req.getHeader("mode");
		if (null != userAgent && !"".equals(userAgent)) {
			if (userAgent.indexOf("Android") >= 0 || userAgent.indexOf("Mobile") >= 0) {
				return true;
			}
		} else if (null != hd && !"".equals(hd)) {
			if ("android".equals(hd)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
    
}