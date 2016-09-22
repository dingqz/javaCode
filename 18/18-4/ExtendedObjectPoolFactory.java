
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
/**
 * Description:
 * <br/>Copyright (C), 2008-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ExtendedObjectPoolFactory
{
	//����һ�������,ǰ���Ƕ�������������ʵ�ʶ���
	private Map<String ,Object> objectPool = 
		new HashMap<String , Object>();
	private Properties config = new Properties();

	//��ָ�������ļ��г�ʼ��Properties����
	public void init(String fileName)
	{
		FileInputStream fis = null;
		try
		{
			//��ʼ���ļ�������
			fis = new FileInputStream(fileName);
			config.load(fis);
		}
		catch (IOException ex)
		{
			System.out.println("��ȡ" + fileName + "�쳣");
		}
		finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	//����һ����������ķ�����
	//�÷���ֻҪ����һ���ַ���������������Ը��ݸ���������Java����
	private Object createObject(String clazzName)
		throws InstantiationException , IllegalAccessException
		,ClassNotFoundException
	{
		//�����ַ�������ȡ��Ӧ��Class����
		Class<?> clazz =Class.forName(clazzName);
		//ʹ��clazz��Ӧ���Ĭ�Ϲ���������ʵ��
		return clazz.newInstance();	
	}
	//�÷�������ָ���ļ�����ʼ������أ�
	//������������ļ�����������
	public void initPool()throws InstantiationException 
		,IllegalAccessException , ClassNotFoundException
	{
		for (String name : config.stringPropertyNames())
		{
			//ÿȡ��һ��������������ֵ�ԣ�����������в������ٷֺţ�%��
			//�͸�������ֵ����һ������
			//����createObject�������󣬲����������ӵ��������
			if (!name.contains("%"))
			{
				objectPool.put(name , 
					createObject(config.getProperty(name)));
			} 
		}
	}

	//�÷�������ָ���ļ�����ʼ������أ�
	//������������ļ�����������
	public void initProperty()throws InvocationTargetException
		,IllegalAccessException,NoSuchMethodException
	{
		for (String name : config.stringPropertyNames())
		{
			//ÿȡ��һ��������������ֵ�ԣ�����������а����ٷֺţ�%��
			//������Ϊ������������Ϊ������������ֵ��
			//�»���ǰ��Ϊ�������֣����Ϊ������
			//���򽫵��ö�Ӧ��setter������Ϊ��Ӧ������������ֵ��
			if (name.contains("%"))
			{
				//�������ļ�����������_�ָ�
				String[] objAndProp = name.split("%");
				//ȡ����Ҫ�������Ե�Ŀ�������
				Object target = getObject(objAndProp[0]);
				//�����Զ�Ӧ��setter������:set + "���Ե�����ĸ��д" + ʣ�²���
				String mtdName = "set" + 
					objAndProp[1].substring(0 , 1).toUpperCase() +
					objAndProp[1].substring(1);
				//ͨ��target��getClass()��ȡ��ʵ��������Ӧ��Class����
				Class<?> targetClass = target.getClass();
				//��ȡ�����Զ�Ӧ��setter����
				Method mtd = targetClass.getMethod(mtdName , String.class);
				//ͨ��Method��invoke����ִ��setter������
				//��config.getProperty(name)������ֵ��Ϊ����setter�ķ�����ʵ��
				mtd.invoke(target , config.getProperty(name));
			} 
		}
	}
	public Object getObject(String name)
	{
		//��objectPool��ȡ��ָ��name��Ӧ�Ķ���
		return objectPool.get(name);
	}
	
	public static void main(String[] args)
		throws Exception
	{
		ExtendedObjectPoolFactory epf = new ExtendedObjectPoolFactory();
		epf.init("extObj.txt");
		epf.initPool();
		epf.initProperty();
		System.out.println(epf.getObject("a"));
	}
}