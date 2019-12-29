package util;

import java.util.HashMap;

public class MySpring {
	private static HashMap<String,Object> objBox = new HashMap<>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T>T getBean(String className) {
		T obj = (T)objBox.get(className);
		if(obj==null) {
			Class clazz;
			try {
				clazz = Class.forName(className);
				obj = (T)clazz.newInstance();
				objBox.put(className, obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}
}
