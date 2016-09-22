

/**
 * Description:
 * <br/>Copyright (C), 2005-2008, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */

public class TestMod
{
	public static void main(String[] args) 
	{
		double a = 5.2;
		double b = 3.1;
		double mod = a % b;
		//mod��ֵΪ2.1
		System.out.println(mod);
		//�������:NaN
		System.out.println("5��0.0����Ľ����:" + 5 % 0.0);
		//�������:NaN
		System.out.println("-5.0��0����Ľ����:" + -5.0 % 0);
		//���0
		System.out.println("0��5.0����Ľ����:" + 0 % 5.0);
		//�������:NaN
		System.out.println("0��0.0����Ľ����:" + 0 % 0.0);
		//������뽫�����쳣 java.lang.ArithmeticException: / by zero
		System.out.println("-5��0����Ľ����:" + -5 % 0);
	}
}