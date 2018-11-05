package com.dk.op;

import com.dk.entity.AP;

import java.util.Collections;
import java.util.List;

/**
 * 计算距离
 * pt为发送功率
 * k是一个取决于环境和频率的常熟
 * a为路径损耗指数
 * errorD为误差距离
 */

public class ComputeDistance {
	private double pt;
	private double k = 49;
	private double a = 4.5;

	private double errorD = 10*100;

	public double getPt() {
		return pt;
	}

	public void setPt(double pt) {
		this.pt = pt;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getErrorD() {
		return errorD;
	}

	public void setErrorD(double errorD) {
		this.errorD = errorD;
	}

	/**
	 * 根据rssi获取距离
	 * @param rssi
	 * @return
	 */
	public Double getDistance(double rssi){
		Double d = null;
		if(this.a>0.000001 || this.a<-0.000001){
//			d = Math.pow(10,((this.pt-this.k-rssi)/(10*this.a)));
			d = Math.pow(10,(Math.abs(rssi)-this.k)/(10*this.a))*100;
		}
		return d;
	}

	/**
	 * 根据传入的ap信息计算经纬度
	 * @param aps
	 *
	 * @return
	 */
	public double[] getLocation(List<AP> aps){
		if(aps.size()!=3){
			System.out.println("需要三个ap信息！");
			System.exit(1);
		}

//		this.a = 2;
//		this.k = 45;

//		Collections.shuffle(aps);

		double d1 = getDistance(aps.get(0).getRssi());
		double d2 = getDistance(aps.get(1).getRssi());
		double d3 = getDistance(aps.get(2).getRssi());

		System.out.println("------- "+d1+" : "+d2+" : "+d3);

		double xloc1 = aps.get(0).getXloc();
		double yloc1 = aps.get(0).getYloc();

		double xloc2 = aps.get(1).getXloc();
		double yloc2 = aps.get(1).getYloc();

		double xloc3 = aps.get(2).getXloc();
		double yloc3 = aps.get(2).getYloc();

		double[] xy = getXlocAndYloc(d1,xloc1,yloc1,d2,xloc2,yloc2,d3,xloc3,yloc3);

		return xy;
	}

	//	计算经纬度
	private double[] getXlocAndYloc(double d1,double xloc1,double yloc1,double d2,double xloc2,double yloc2,double d3,double xloc3,double yloc3){
		double xloc = 0;
		double yloc = 0;
		double x = 0;
		double y = 0;
		double[] xy = {xloc,yloc};


		double dactive1 = algorithm(yloc1,yloc2,xloc1,xloc2);
		System.out.println("------- "+dactive1);
		if(dactive1<=d1+d2){
			if(yloc1>=yloc2&&xloc1>=xloc2){
				double xx1 = xloc2 + ((xloc1 - xloc2)*d2/dactive1);
				double yy1 = (yloc2 + (yloc1 - yloc2)*d2/dactive1);

				double xx2 = xloc1 - (xloc1 - xloc2)*d1/dactive1;
				double yy2 = yloc1 - (yloc1 - yloc2)*d1/dactive1;

				xloc = (xx1 + xx2)/2;
				yloc = (yy1 + yy2)/2;

			}else if(yloc1>=yloc2&&xloc1<=xloc2){
				double xx1 = xloc1 + (xloc2 - xloc1)*d1/dactive1;
				double yy1 = yloc1 - (yloc1 - yloc2)*d1/dactive1;

				double xx2 = xloc2 - (xloc2 - xloc1)*d2/dactive1;
				double yy2 = yloc2 + (yloc1 - yloc2)*d2/dactive1;

				xloc = (xx1 + xx2)/2;
				yloc = (yy1 + yy2)/2;

			}else if(yloc1<=yloc2&&xloc1>=xloc2){
				double xx1 = xloc2 + (xloc1 - xloc2)*d1/dactive1;
				double yy1 = yloc2 - (yloc2 - yloc1)*d1/dactive1;

				double xx2 = xloc1 - (xloc1 - xloc2)*d2/dactive1;
				double yy2 = yloc1 + (yloc2 - yloc1)*d2/dactive1;

				xloc = (xx1 + xx2)/2;
				yloc = (yy1 + yy2)/2;

			}else if(yloc1<=yloc2&&xloc1<=xloc2){
				double xx1 = xloc1 + (xloc2 - xloc1)*d1/dactive1;
				double yy1 = yloc1 + (yloc2 - yloc1)*d1/dactive1;

				double xx2 = xloc2 - (xloc2 - xloc1)*d2/dactive1;
				double yy2 = yloc2 - (yloc2 - yloc1)*d2/dactive1;

				xloc = (xx1 + xx2)/2;
				yloc = (yy1 + yy2)/2;

			}





		}else if(dactive1>=d1+d2&&dactive1<=d1+d2+this.errorD){
			if(yloc1>=yloc2&&xloc1>=xloc2){
				double xx1 = (xloc1-xloc2)*d2/dactive1+xloc2;
				double yy1 = (yloc1-yloc2)*d2/dactive1+yloc2;

				double xx2 = xloc1-((xloc1-xloc2)*d1/dactive1);
				double yy2 = yloc1-((yloc1-yloc2)*d1/dactive1);

				xloc = (xx1+xx2)/2;
				yloc = (yy1+yy2)/2;

			}else if(yloc1<=yloc2&&xloc1<=xloc2){
				double xx1 = (xloc2-xloc1)*d1/dactive1+xloc1;
				double yy1 = (yloc2-yloc1)*d1/dactive1+yloc1;

				double xx2 = xloc2-((xloc2-xloc1)*d2/dactive1);
				double yy2 = yloc2-((yloc2-yloc1)*d2/dactive1);

				xloc = (xx1+xx2)/2;
				yloc = (yy1+yy2)/2;

			}else if(yloc1<=yloc2&&xloc1>=xloc2){
				double xx1 = xloc2+(xloc1-xloc2)*d2/dactive1;
				double yy1 = yloc2-(yloc2-yloc1)*d2/dactive1;

				double xx2 = xloc1-(xloc1-xloc2)*d1/dactive1;
				double yy2 = yloc1+(yloc2-yloc1)*d1/dactive1;

				xloc = (xx1+xx2)/2;
				yloc = (yy1+yy2)/2;

			}else if(yloc1>=yloc2&&xloc1<=xloc2){
				double xx1 = xloc1+(xloc2-xloc1)*d1/dactive1;
				double yy1 = yloc1-(yloc1-yloc2)*d1/dactive1;

				double xx2 = xloc2-(xloc2-xloc1)*d2/dactive1;
				double yy2 = yloc2+(yloc1-yloc2)*d2/dactive1;

				xloc = (xx1+xx2)/2;
				yloc = (yy1+yy2)/2;

			}
		}

		double dactive2 = algorithm(yloc,yloc3,xloc,xloc3);
		System.out.println("---------- " +dactive2);
		if(dactive2<=d3+errorD){
			if(xloc >= xloc3 && yloc >= yloc3){
				double xx = xloc3 + ((xloc - xloc3)*d3/dactive2);
				double yy = yloc3 + ((yloc - yloc3)*d3/dactive2);

				x = (xloc + xx)/2;
				y = (yloc + yy)/2;


			}else if(xloc <= xloc3 && yloc <= yloc3){
				double xx = xloc3 - ((xloc3 - xloc)*d3/dactive2);
				double yy = yloc3 - ((yloc3 - yloc)*d3/dactive2);

				x = (xloc + xx)/2;
				y = (yloc + yy)/2;

			}else if(xloc >= xloc3 && yloc <= yloc3){
				double xx = xloc3 + (xloc - xloc3)*d3/dactive2;
				double yy = yloc3 - (yloc3 - yloc)*d3/dactive2;

				x = (xloc + xx)/2;
				y = (yloc + yy)/2;

			}else if(xloc <= xloc3 && yloc >= yloc3){
				double xx = xloc3 - ((xloc3 - xloc) * d3 / dactive2);
				double yy = yloc3 + ((yloc - yloc3) *d3 / dactive2);

				x = (xloc + xx)/2;
				y = (yloc + yy)/2;
			}


		}

		xy[0] = x;
		xy[1] = y;

		return xy;
	}


	/**
	 * 根据经纬度获取两点距离
	 * @param lat1	yloc1
	 * @param lat2	yloc2
	 * @param lon1	xloc1
	 * @param lon2	xloc2
	 * @return
	 */
	private double getDistatce(double lat1, double lat2, double lon1, double lon2) {
		double R = 6371;
		double distance = 0.0;
		double dLat = (lat2 - lat1) * Math.PI / 180;
		double dLon = (lon2 - lon1) * Math.PI / 180;
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)+ Math.cos(lat1 * Math.PI / 180)* Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
		return distance;
	}


	private double algorithm(double latitude1, double latitude2, double longitude1, double longitude2) {
		double Lat1 = rad(latitude1); // 纬度
		double Lat2 = rad(latitude2);
		double a = Lat1 - Lat2;//两点纬度之差
		double b = rad(longitude1) - rad(longitude2); //经度之差
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式
		s = s * 6378137.0;//弧长乘地球半径（半径为米）
		s = Math.round(s * 10000d) / 10000d;//精确距离的数值
		return s;
	}

	private double rad(double d) {
		return d * Math.PI / 180.00; //角度转换成弧度
	}
}
