package ti.admob;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.proxy.TiViewProxy;

import com.google.android.gms.ads.*;

public class Interstitial extends TiUIView {
    private static final String TAG = "Interstitial";
    private InterstitialAd interstitial;
    private String ad_unit_id;

    public Interstitial(final TiViewProxy proxy) {
	super(proxy);
    }

    private void createAdView() {
	interstitial = new InterstitialAd(proxy.getActivity());
	interstitial.setAdUnitId(ad_unit_id);
	loadAd();
    }

    private void loadAd() {
	proxy.getActivity().runOnUiThread(new Runnable() {
	    public void run() {
		// Create ad request.
		AdRequest adRequest = new AdRequest.Builder().build();
		Log.d(TAG, "load interstitial ad");
		// Begin loading your interstitial.
		interstitial.loadAd(adRequest);
		// display interstitial asynchronical
		// To avoid the "showInterstitial must be called on the main UI thread" error
		interstitial.setAdListener(new AdListener() {
		    public void onAdLoaded() {
		        displayInterstitial();
		    }
		});   
	    }

	});
    }

    @Override
    public void processProperties(KrollDict d) {
	super.processProperties(d);
	Log.d(TAG, "process properties");

	if (d.containsKey("adUnitId")) {
	    Log.d(TAG, "has adUnitId: " + d.getString("adUnitId"));
	    ad_unit_id = d.getString("adUnitId");
	    // now create the interstitialView
	    this.createAdView();
	} else {
	    throw new IllegalArgumentException("adUnitId is required");
	}
    }

    // Invoke displayInterstitial() when you are ready to display an interstitial.
    public void displayInterstitial() {
	Log.d(TAG, "interstitial -> displayInterstitial");
	if (interstitial.isLoaded()) {
	    Log.d(TAG, "interstitial is loaded");
	    interstitial.show();
	}
    }

}