package cn.ffcs.util;

import com.alibaba.fastjson.JSONObject;

public class AdjustUtil {

	/**
	 * 远程读报告
	 * @param url
	 * @return
	 */
	public static JSONObject getAdjust(String url){
		
		String result = HttpClientUtil.get(url);
		
		JSONObject json = JSONObject.parseObject(result);
		JSONObject data = json.getJSONObject("data");
		JSONObject adjust = new JSONObject();
		if(data!=null) {
			// 机器判读结果
			JSONObject machine_adjust = data.getJSONObject("machine_adjust");
			if(machine_adjust!=null){
				// 只有一个图片ID的key
				for(String key : machine_adjust.keySet()){
					adjust = machine_adjust.getJSONObject(key);
				}
				
			}
		}
		return adjust;
	}
	
}
