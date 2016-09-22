

/**
 * Description:
 * <br/>Copyright (C), 2005-2008, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */

class Animal
{
	private void beat()
	{
		System.out.println("��������...");
	}
	public void breath()
	{
		beat();
		System.out.println("��һ��������һ������������...");
	}
}
class Bird
{
	//��ԭ���ĸ���Ƕ��ԭ�������࣬��Ϊ�����һ����ϳɷ�
	private Animal a;
	public Bird(Animal a)
	{
		this.a = a;
	}
	//���¶���һ���Լ���breath����
	public void breath()
	{
		//ֱ�Ӹ���Animal�ṩ��breath������ʵ��Bird��breath������
		a.breath();
	}

	public void fly()
	{
		System.out.println("����������ڵķ���...");
	}
}
class Wolf
{
	//��ԭ���ĸ���Ƕ��ԭ�������࣬��Ϊ�����һ����ϳɷ�
	private Animal a;
	public Wolf(Animal a)
	{
		this.a = a;
	}
	//���¶���һ���Լ���breath����
	public void breath()
	{
		//ֱ�Ӹ���Animal�ṩ��breath������ʵ��Bird��breath������
		a.breath();
	}
	public void run()
	{
		System.out.println("����½���ϵĿ��ٱ���...");
	}
}
public class TestComposite
{
	public static void main(String[] args)
	{
		//��ʱ��Ҫ��ʽ������Ƕ��Ķ���
		Animal a1 = new Animal();
		Bird b = new Bird(a1);
		b.breath();
		b.fly();
		//��ʱ��Ҫ��ʽ������Ƕ��Ķ���
		Animal a2 = new Animal();
		Wolf w = new Wolf(a2);
		w.breath();
		w.run();		
	}
}