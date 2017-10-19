package com.upbos.upm.dict;

import java.util.List;
import java.util.Map;

import com.upbos.framework.context.SpringContext;
import com.upbos.upm.entity.Dict;
import com.upbos.upm.entity.DictMapping;
import com.upbos.util.ReflectUtils;

/**
 * <b>Application name：</b> DictUtil.java <br>
 * <b>Application describing: 数据字典翻译工具</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2015-2017 upbos.com 版权所有。<br>
 * <b>Company：</b> upbos.com <br>
 * <b>Date：</b> 2017年10月18日 17:39 <br>
 * <b>author：</b> <a href="mailto:wjzchina2008@126.com"> Jason </a>
 * <b>version：</b>V3.0.0
 */
public class DictUtil {
	/**
	 * 对数据进行字典翻译，可以翻译多组字典
	 * @param data 待翻译的数据
	 * @param mappings 翻译映射关系
	 * @return
	 * @throws Exception
	 */
	public static Object tranlate(Object data, DictMapping[] mappings) throws Exception{
		DictService srv = SpringContext.getBean("dictService");
		if(mappings == null) return data;
		
		String sqlParams = "";
		for(DictMapping mapping : mappings) {
			sqlParams += "," + mapping.getDictKey();
		}
		
		List<Dict> dicts = srv.listDict(sqlParams.length() > 0 ? sqlParams.substring(1):sqlParams);
		
		for(DictMapping mapping : mappings) {
			translate(dicts, data, mapping);
		}
		return data;
	}
	
	
	public static Object tranlate(Object data, DictMapping mapping) throws Exception{
		DictService srv = SpringContext.getBean("dictService");
		List<Dict> dicts = srv.listDict(mapping.getDictKey());
		translate(dicts, data, mapping);
		return data;
	}


	/**
	 * 对数据进行字典翻译
	 * @param dicts 数据字典
	 * @param data 待翻译的数据
	 * @param mapping 翻译映射关系
	 * @return 翻译后的数据
	 * @throws Exception
	 */
	private static Object translate(List<Dict> dicts, Object data, DictMapping mapping) throws Exception{
		if(data == null) {
			return data;
		}
		
		if(data instanceof Map) {
			String value = getValue(dicts, mapping.getDictKey(), (String)((Map)data).get(mapping.getSourceProp()));
			((Map)data).put(mapping.getTargetProp(), value);
		}else if(data instanceof List) {
			for(Object obj : (List)data) {
				try {
					if(obj instanceof Map) {
						String value = getValue(dicts, mapping.getDictKey(), (String)((Map)obj).get(mapping.getSourceProp()));
						((Map)obj).put(mapping.getTargetProp(), value);
					}else {
						String value = getValue(dicts, mapping.getDictKey(), (String)ReflectUtils.getValueByFieldName(obj, mapping.getSourceProp()));
						ReflectUtils.setValueByFieldName(obj, mapping.getTargetProp(), value);
					}
				}catch(Exception e) {
					throw e;
				}
			}
		}else {
			String value = getValue(dicts, mapping.getDictKey(), (String)ReflectUtils.getValueByFieldName(data, mapping.getSourceProp()));
			ReflectUtils.setValueByFieldName(data, mapping.getTargetProp(), value);
		}
		return data;
	}

	
	private static String getValue(List<Dict> dicts, String dictKey, String code) {
		for(Dict v : dicts) {
			if(v.getKey().equals(dictKey) && v.getCode().equals(code)) {
				return v.getValue();
			}
		}
		return null;
	}

}
