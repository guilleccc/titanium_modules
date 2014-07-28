/**
 * Copyright (c) 2011 by Studio Classics. All Rights Reserved.
 * Author: Brian Kurzius
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.admob;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext.OnLifecycleEvent;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;

@Kroll.proxy(creatableInModule = AdmobModule.class)
public class InterstitialAdProxy extends TiViewProxy  {
	private Interstitial adMob;
	private static final String TAG = "AdMobViewProxy";

	public InterstitialAdProxy() {
		super();
	}

	@Override
	protected KrollDict getLangConversionTable() {
		KrollDict table = new KrollDict();
		table.put("title", "titleid");
		return table;
	}

	@Override
	public TiUIView createView(Activity activity) {
		adMob = new Interstitial(this);
		return adMob;
	}

	@Kroll.method
	public void displayInterstitial() {
		Log.d(TAG, "Proxy - displayInterstitial(): ");
		adMob.displayInterstitial();
	}

}
