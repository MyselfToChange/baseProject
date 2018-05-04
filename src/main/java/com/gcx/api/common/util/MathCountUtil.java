package com.gcx.api.common.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;




/**
 * <p>作者:zhanglin</p>
 * <p>类名称:MathCountUtil </p>
 * <p>类描述:数学计算工具类</p>
 * <p>创建时间:2016.8.18</p>
 */
public class MathCountUtil implements Serializable {

	 private static final long serialVersionUID = 1L;  
     
	 private static final int UNIT_TWO=2;  //默认长度单位
	 
	 /**
	  * 四舍五入，保留2位小数
	  * @param  f
	  * @return
	  */
	 public static double setScale(double f){
		 BigDecimal b = new BigDecimal(f);
		 f = b.setScale(UNIT_TWO, BigDecimal.ROUND_HALF_UP).doubleValue();
		 return f;
	 }
	 /**
	  * 将double装换为百分比
	  * @param  f
	  * @return
	  */
	 public static String format(double f){
		 NumberFormat nt = NumberFormat.getPercentInstance();
		 nt.setMinimumFractionDigits(UNIT_TWO);
		 return nt.format(f);
	 }

	 /**
	  * 减法 保留2位小数
	  * @param  F1
	  * @param  F2
	  * @return
	  */
	 public static double  subtract(double F1,double F2){
		 
		 return MathCountUtil.setScale(F1-F2);
	 }

	 /**
	  * 乘法
	  * @param  F1
	  * @param  F2
	  * @return
	  */
	 public static double  multiply(double F1,double F2){
		 return F1*F2;
	 }

	 /**
	  * 除法
	  * @param F1
	  * @param F2
	  * @return
	  */
	 public static double divide(double F1,double F2){
		 if(F2!=0.0){
			 return F1/F2;
		 }
		 return 0.0;
	 }


	 
}