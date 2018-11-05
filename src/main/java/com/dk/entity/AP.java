package com.dk.entity;

public class AP {
	private String bssid;
	private double rssi;
	private double xloc;
	private double yloc;

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	public double getRssi() {
		return rssi;
	}

	public void setRssi(double rssi) {
		this.rssi = rssi;
	}

	public double getXloc() {
		return xloc;
	}

	public void setXloc(double xloc) {
		this.xloc = xloc;
	}

	public double getYloc() {
		return yloc;
	}

	public void setYloc(double yloc) {
		this.yloc = yloc;
	}
}
