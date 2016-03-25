package com.lixinsheng.foodmenu.base;

import android.content.Context;
import android.view.View;

public interface IBaseActivity {

	public int bindLayout();
	
	public void initView(final View view);
	
	public void doBusiness(Context mContext);
	
	public void resume();
	
	public void destroy();
	
}
