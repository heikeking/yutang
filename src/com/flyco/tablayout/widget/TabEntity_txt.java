package com.flyco.tablayout.widget;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity_txt implements CustomTabEntity {
    public String title;

    public TabEntity_txt(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

	@Override
	public int getTabSelectedIcon() {
		return 0;
	}

	@Override
	public int getTabUnselectedIcon() {
		return 0;
	}
}
