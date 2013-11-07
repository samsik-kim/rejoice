package com.omp.admin.adminmgr.auth.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AdMenuItem extends AdMenuMgrauth {

	private List<AdMenuItem> subMenuItem = new ArrayList<AdMenuItem>();

	public List<AdMenuItem> getSubMenuItem() {
		return subMenuItem;
	}

	public void setSubMenuItem(List<AdMenuItem> subMenuItem) {
		this.subMenuItem = subMenuItem;
	}

}
