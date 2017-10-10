package com.upbos.upm.dict;

import java.util.List;
import java.util.Map;

import com.upbos.framework.context.SpringContext;
import com.upbos.upm.entity.Dict;
import com.upbos.util.ReflectUtils;

/**
 * <p>ClassName: DictUtil</p>
 * <p>Description: 数据字典翻译工具</p>
 * <p>Company： upbos.com</p>
 * @author wangjz
 * @date 2016年11月13日 下午3:50:19
 * @version v2.5.0
 */
public class DictUtil {
	
	
	/**
	 * @Method: convert
	 * @Description: 对数据进行字典翻译，可以翻译多组字典
	 * @param @param list 需要翻译的数据
	 * @param @param props 映射关系，格式：["user_type:userType->userTypeName", "sexCode->sexName"];
	 * @param @throws Exception
	 * @return List<T> 字典翻译后的数据
	 * @throws
	 */
	public static Object tranlate(Object data, String[] mappings) throws Exception{
		DictService srv = SpringContext.getBean("dictService");
		if(mappings == null) return data;
		
		String sqlParams = "";
		for(String mapping : mappings) {
			sqlParams += "," + resolveMapping(mapping)[0];
		}
		
		List<Dict> dicts = srv.queryDictByKey(sqlParams.length() > 0 ? sqlParams.substring(1):sqlParams);
		
		for(String mapping : mappings) {
			String[] resolveMapping = resolveMapping(mapping);
			translate(dicts, data, resolveMapping[0], resolveMapping[1], resolveMapping[3]);
		}
		return data;
	}
	
	
	public static Object tranlate(Object data, String mapping) throws Exception{
		DictService srv = SpringContext.getBean("dictService");
		String[] res = resolveMapping(mapping);
		List<Dict> dicts = srv.queryDictByKey(res[0]);
		translate(dicts, data, res[0], res[1], res[2]);
		return data;
	}
	
	
	/**
	 * @Method: convert
	 * @Description: 对数据进行字典翻译
	 * @param @param list 需要翻译的数据对象
	 * @param @param srcColName 需要翻译的字段名称
	 * @param @param destColName 翻译后写入的字段名称
	 * @param @throws Exception
	 * @return 翻译后的数据
	 * @throws
	 */
	private static Object translate(List<Dict> dicts, Object data, String dictName, String srcProp, String targetProp) throws Exception{
		if(data == null) {
			return data;
		}
		
		if(data instanceof Map) {
			String value = getValue(dicts, dictName, (String)((Map)data).get(srcProp));
			((Map)data).put(targetProp, value);
		}else if(data instanceof List) {
			for(Object obj : (List)data) {
				try {
					if(obj instanceof Map) {
						String value = getValue(dicts, dictName, (String)((Map)obj).get(srcProp));
						((Map)obj).put(targetProp, value);
					}else {
						String value = getValue(dicts, dictName, (String)ReflectUtils.getValueByFieldName(obj, srcProp));
						ReflectUtils.setValueByFieldName(obj, targetProp, value);
					}
				}catch(Exception e) {
					throw e;
				}
			}
		}else {
			String value = getValue(dicts, dictName, (String)ReflectUtils.getValueByFieldName(data, srcProp));
			ReflectUtils.setValueByFieldName(data, targetProp, value);
		}
		return data;
	}
	
	private static String[] resolveMapping(String mapping) {
		String dictName = "", srcProp = "", targetProp = "";
		int p1 = mapping.indexOf(":");
		int p2 = mapping.indexOf("->");
		
		//格式:user_type:userType->userTypeName
		if(p1 != -1 && p2 != -1) {
			dictName = mapping.substring(0, p1);
			srcProp = mapping.substring(p1 + 1, p2);
			targetProp = mapping.substring(p2+2, mapping.length());
		}
		
		//格式: user_type:userType
		if(p1 != -1 && p2 == -1) {
			dictName = mapping.substring(0, p1);
			srcProp = mapping.substring(p1 + 1, mapping.length());
			targetProp = srcProp;
		}
		
		//格式: userType->userTypeName
		if(p1 == -1 && p2 != -1) {
			srcProp = mapping.substring(0, p2);
			targetProp = mapping.substring(p2+2, mapping.length());
			dictName = srcProp;
		}
		
		//格式：userType
		if(p1 == -1 && p2 == -1) {
			dictName = srcProp = targetProp = mapping;
		}
		return new String[]{dictName, srcProp, targetProp};
	}
	
	private static String getValue(List<Dict> dicts, String dictName, String key) {
		for(Dict v : dicts) {
			if(v.getKey().equals(dictName) && v.getCode().equals(key)) {
				return v.getValue();
			}
		}
		return null;
	}
}
