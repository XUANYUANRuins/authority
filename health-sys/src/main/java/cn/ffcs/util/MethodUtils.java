package cn.ffcs.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MethodUtils {
    
    public static final String salt = "javacoder";
	
    public static String md5(String str){
        return md5(str, salt);
    }
    
	public static String md5(String str,String salt)
	{
		return new Md5Hash(str, salt).toString();
	}
	

    /**
     * 取某对象中某字段的值
     * @param object
     * @param fieldName
     * @return
     */
    
    public static Object getFieldValue(Object object,String fieldName) {
        Object value =null;
        if(object!=null){
            Class clszz=object.getClass();
            Method getMdthod=null;
            try {
                getMdthod = clszz.getMethod("get"+StringUtils.capitalize(fieldName));
                value=(Object) getMdthod.invoke(object);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                getMdthod=null;
                clszz=null;
            }
        }
        return value;
    }
    

    /**
     * 给某个字段赋值
     * @param object
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object object,String fieldName,Object value) {
        if(object!=null){
            Class clszz=object.getClass();
            String setMethodName="";
            Method setMdthod=null;
            Field field =null;
            try {
                field = object.getClass().getDeclaredField(fieldName);
                setMethodName="set"+StringUtils.capitalize(fieldName);
                setMdthod=clszz.getDeclaredMethod(setMethodName, field.getType()); 
                setMdthod.invoke(object, value);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                setMdthod=null;
                setMethodName=null;
                clszz=null;
            }
        }
    }
    	
	
    /**
     * 设置Map的值
     * @param map
     * @param key
     * @param value
     */
    public static void setMapValue(Map<String, Float> map, String key, Float value){
        if(map.containsKey(key)){
            Float f = map.get(key);
            map.put(key, f+value);
        } else {
            map.put(key, value);
        }
    }
    
    
    /**
     * javaBean 转 Map
     * @param object 需要转换的javabean
     * @return  转换结果map
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object object) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
 
        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }
 
    /**
     *
     * @param map   需要转换的map
     * @param cls   目标javaBean的类对象
     * @return  目标类object
     * @throws Exception
     */
    public static Object mapToBean(Map<String, Object> map, Class cls) throws Exception {
        Object object = cls.newInstance();
        for (String key : map.keySet()){
            Field temFiels = cls.getDeclaredField(key);
            temFiels.setAccessible(true);
            temFiels.set(object, map.get(key));
        }
        return object;
    }
	
}
