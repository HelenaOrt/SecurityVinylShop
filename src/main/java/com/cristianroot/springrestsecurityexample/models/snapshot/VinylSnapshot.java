package com.cristianroot.springrestsecurityexample.models.snapshot;

import com.cristianroot.springrestsecurityexample.constants.VinylSize;
import com.cristianroot.springrestsecurityexample.models.VinylModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VinylSnapshot {

	private List<VinylModel> top5vinyls = new ArrayList<>();
	private long vinylNumber;
	Map<VinylSize, Long> vinylSizeMap = new HashMap<>();

	public List<VinylModel> getTop5vinyls() {
		return top5vinyls;
	}

	public void setTop5vinyls(List<VinylModel> top5vinyls) {
		this.top5vinyls = top5vinyls;
	}

	public long getVinylNumber() {
		return vinylNumber;
	}

	public void setVinylNumber(long vinylNumber) {
		this.vinylNumber = vinylNumber;
	}

	public Map<VinylSize, Long> getVinylSizeMap() {
		return vinylSizeMap;
	}

	public void setVinylSizeMap(Map<VinylSize, Long> vinylSizeMap) {
		this.vinylSizeMap = vinylSizeMap;
	}
}
