package org.foxconn.springbootStart;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ReflectUtil {
	Logger logger = Logger.getLogger(ReflectUtil.class); 
	
	public static void invote(String className,String functionName,List<String> argType,List<?> args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Class<?> cls = Class.forName(className);
		int j=argType.size();
		Class<?>[] argTypes=null;
		if(j>0){
			argTypes= new Class<?>[j];
		}else{
			argTypes = new Class<?>[]{};
		}
		for(int i=0;i<argTypes.length;i++){
			Class<?> tempcls = Class.forName("java.lang."+argType.get(i));
			argTypes[i] = tempcls;
		}
		Method method = cls.getMethod(functionName,argTypes);
//		method.invoke(cls.newInstance(), args.get(0),args.get(1),args.get(2));
		method.invoke(cls.newInstance(), "xiaoming",1,2l);
		
	}
	public static void invote(String className,String functionName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		invote(className,functionName,new ArrayList<String>(),new ArrayList<Object>());
	}
	public void run(){
		logger.info("running....");
	}
	public void speak(String name,String language){
		logger.info(name+language);
		
	}
	
	public void eat(String name,Integer count,Long totalCount){
		logger.info(name+count+totalCount);
	}
	
	@Test
	public void testInvote() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		List<String> arginput = new ArrayList<String>();
		arginput.add("String");
		arginput.add("Integer");
		arginput.add("Long");
		List arginputValues = new ArrayList();
		arginputValues.add("xiaoming");
		arginputValues.add(1);
		arginputValues.add(2l);
		invote("org.foxconn.springbootStart.ReflectUtil","eat",arginput,arginputValues);
	}
}
