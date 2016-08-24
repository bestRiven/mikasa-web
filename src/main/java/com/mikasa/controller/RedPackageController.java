package com.mikasa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mikasa.utils.JsonUtil;
import com.mikasa.vo.RedPackageTemp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("RedPackage")
public class RedPackageController {
	
	private static final Logger log = LoggerFactory.getLogger(RedPackageController.class);
	
	@RequestMapping("/list")
	public String list() {
		return "pages/templateList";
	}
	
	@RequestMapping("/getTemplate")
	@ResponseBody
	public Object getTemplate(@RequestParam("page") int page,@RequestParam("rows") int rows,
			@RequestParam(value = "id",required=false) String id,
			@RequestParam(value = "template_des",required=false) String template_des,
			@RequestParam(value = "coupon_name",required=false) String coupon_name){
		log.info(page+""); 
		log.info(rows+"");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "1");
		map.put("img_url", "img_url");
		map.put("template_des", "template_des");
		map.put("coupon_name", "coupon_name");
		list.add(map);
		int count = 1;
		int totalPage = 1;
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("count", count);
		jsonResult.put("total", totalPage);
		jsonResult.put("list", list);
		return jsonResult;
	}
	
	@RequestMapping("/getById")
	public Object getById(@RequestParam("id") int id,Map<String, Object> map){
		log.info(map.toString());
		RedPackageTemp redPackageTemp = new RedPackageTemp();
		redPackageTemp.setId(id);
		redPackageTemp.setTemplate_des("这个杀手不太冷");
		redPackageTemp.setTemplate_url("http://localhost:8080/missfreshadmin/");
		redPackageTemp.setImg_url("http://localhost:8080/missfreshadmin/");
		redPackageTemp.setAdvertisement_url("http://localhost:8080/missfreshadmin/");
		redPackageTemp.setCoupon_code("abcdefg");
		map.put("template", redPackageTemp);
		return "pages/updateRedPackageTemp";
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object updateRedPackageTemp(RedPackageTemp redPackageTemp){
		log.info("redPackageTemp:"+redPackageTemp.getId());
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		boolean result = true;
		if(result){
			jsonResult.put("operationResult","success");
			jsonResult.put("successMsg", "修改成功");
		}else{
			jsonResult.put("operationResult","fail");
		}
        
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping("/updateTemplate")
	public Object update(@RequestParam("oper") String oper,
			@RequestParam("id") String id,
			@RequestParam(value="app_poi_code",required=false) String app_poi_code,
			@RequestParam(value="spu_code",required=false) String spu_code,
			@RequestParam(value="sku_code",required=false) String sku_code,
			@RequestParam(value="sku_name",required=false) String sku_name){
		log.info("oper:"+oper);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("plat_flg", "eleme");
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		if("edit".equals(oper)){
			if(StringUtils.isNotBlank(app_poi_code)){
	        	params.put("app_poi_code", app_poi_code);
	        	
	        }
			if(StringUtils.isNotBlank(spu_code)){
	        	params.put("spu_code", spu_code);
	        }
	        if(StringUtils.isNotBlank(sku_code)){
	        	params.put("sku_code", sku_code);
	        }
	        if(StringUtils.isNotBlank(sku_name)){
	        	params.put("sku_name", sku_name);
	        }
			//修改
			params.put("operation", "update");
			log.info("params:"+params);
			String jsonmap = "";
	        Map<String, Object> result = new HashMap<String, Object>();
			try {
				result = JsonUtil.json2map(jsonmap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((Boolean) result.get("flag")){
				jsonResult.put("operationResult","success");
				jsonResult.put("successMsg", "修改成功");
				
			}else{
				jsonResult.put("operationResult","fail");
			}
			return jsonResult;
		}else{
			//删除
			params.put("operation", "delete");
			log.info("params:"+params);
			String jsonmap = "";
	        Map<String, Object> result = new HashMap<String, Object>();
			try {
				result = JsonUtil.json2map(jsonmap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((Boolean) result.get("flag")){
				jsonResult.put("operationResult","success");
				jsonResult.put("successMsg", "删除成功");
			}else{
				jsonResult.put("operationResult","fail");
			}
			return jsonResult;
		}
	}
	
	@RequestMapping("/putin")
	public String putin() {
		return "pages/putin";
	}
	
	@RequestMapping("/grid")
	public String grid() {
		return "pages/grid";
	}

}
