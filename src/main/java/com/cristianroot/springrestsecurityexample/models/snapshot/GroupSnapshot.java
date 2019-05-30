package com.cristianroot.springrestsecurityexample.models.snapshot;

import com.cristianroot.springrestsecurityexample.models.MusicGroupModel;

import java.util.List;

public class GroupSnapshot {

	private List<MusicGroupModel> top5groups;
	private int groupNumber;

	public int getGroupNumber() {
		return groupNumber;
	}

	public GroupSnapshot setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
		return this;
	}

	public List<MusicGroupModel> getTop5groups() {
		return top5groups;
	}

	public GroupSnapshot setTop5groups(List<MusicGroupModel> top5groups) {
		this.top5groups = top5groups;
		return this;
	}
}
