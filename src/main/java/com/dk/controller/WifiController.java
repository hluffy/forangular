package com.dk.controller;

import com.dk.entity.AP;
import com.dk.entity.WifiLocation;
import com.dk.op.ComputeDistance;
import com.dk.repository.WifiLocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wifi")
public class WifiController {
	@Resource
	private WifiLocationRepository wifiLocationRepository;

//	保存wifi信息
	@RequestMapping("/savewifi")
	public boolean saveWifi(String imei,String info){
		try {
			info = URLDecoder.decode(info,"utf-8");
			System.out.println(info);
		}catch (Exception e){
			e.printStackTrace();
		}

		if(imei!=null&&info!=null&& !StringUtils.isEmpty(imei.trim())&& !StringUtils.isEmpty(info.trim())){
			Map<String,double[]> map = getApLoc();
			List<AP> aps = new ArrayList<AP>();

			String[] infos = info.split("\\|");
			if(infos.length==3){
				for(int i = 0;i<infos.length;i++){
					String[] datas = infos[i].split(",");
					if(datas.length==2){
						AP ap = new AP();
						ap.setBssid(datas[0]);
						ap.setRssi(Double.parseDouble(datas[1]));

						double[] loc = map.get(datas[0]);
						ap.setXloc(loc[0]);
						ap.setYloc(loc[1]);

						aps.add(ap);
					}
				}
			}

			if(aps.size()==3){
				ComputeDistance cd = new ComputeDistance();
				cd.setA(2);
				cd.setK(45);

				double[] xy = cd.getLocation(aps);
				System.out.println("------- "+xy[0]+ " : "+xy[1]);
				if(xy[0]>1&&xy[1]>1){
//					保存经纬度
					WifiLocation wifi = new WifiLocation();
					wifi.setImei(imei);
					wifi.setXloc(xy[0]);
					wifi.setYloc(xy[1]);
					wifi.setInfo(info);
					try {
						wifiLocationRepository.save(wifi);

					} catch (Exception e){
						e.printStackTrace();
					}

					System.out.println("------------ "+imei+" : 保存成功！");
				}
			}
			return true;
		}else{
			return false;
		}


	}

//	查询最新的一条数据
	@GetMapping("/findbyimei")
	public Page<WifiLocation> findByImei(String imei){
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"lastModifyDate");
			Pageable pageable = new PageRequest(0,1,sort);
			return wifiLocationRepository.findByImei(imei,pageable);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private Map<String,double[]> getApLoc(){
		Map<String,double[]> map = new HashMap<String,double[]>();
		map.put("00:a0:45:37:b9:d0",new double[]{31.0972246,121.555265});
		map.put("00:a0:45:92:99:12",new double[]{31.0972062,121.5605436});
		map.put("00:a0:45:41:da:a9",new double[]{31.0927286,121.5552664});
		return map;
	}
}
