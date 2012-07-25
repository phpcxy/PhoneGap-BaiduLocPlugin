package com.fulstore.plugin.BaiduLoc;


import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class BaiduLocPlugin extends Plugin {
	private LocationClient mLocationClient = null;
	private MyLocationListenner myListener = new MyLocationListenner();
	private JSONObject jsonObj = new JSONObject(); 
	private PluginResult result = null; 
	
	public PluginResult execute(String action, JSONArray args, String callbackId) {
    	
        if (action.equals("get")) {
        	
        	cordova.getActivity().runOnUiThread(new RunnableLoc());
            
        } else if(action.equals("stop")) {
        	mLocationClient.stop();
        	result = new PluginResult(PluginResult.Status.OK);
        } else {	
           result = new PluginResult(PluginResult.Status.INVALID_ACTION);
        }
        
        
     // waiting ui thread to finish
 		while (this.result == null) {
 			try {
 				Thread.sleep(100);
 			} catch (InterruptedException e) {
 				// ignoring exception, since we have to wait
 				// ui thread to finish
 			}
 		}
 		
 		
 		return this.result;
        
      
    }
	
	@Override
    public void onDestroy(){
    	if (mLocationClient != null && mLocationClient.isStarted()){
    		mLocationClient.stop();
    		mLocationClient = null;
    	}
    	super.onDestroy();
    }
	
	
	class RunnableLoc implements Runnable {
				
		public void run() {
			mLocationClient = new LocationClient(cordova.getActivity());
			LocationClientOption option = new LocationClientOption();
			
	        option.setOpenGps(false);							
	        option.setCoorType("bd09ll");							
	        option.setPriority(LocationClientOption.NetWorkFirst);	
	        option.setProdName("BaiduLoc");							
	        option.setScanSpan(5000);								
	        mLocationClient.setLocOption(option);
	        
        	mLocationClient.registerLocationListener( myListener );
        	mLocationClient.start();
            mLocationClient.requestLocation();
            
		}

	}
    
    
	public class MyLocationListenner implements BDLocationListener {
			
			public void onReceiveLocation(BDLocation location) {
				if (location == null)
					return;			
				
				try {
					jsonObj.put("Latitude",location.getLatitude());
					jsonObj.put("Longitude", location.getLongitude());
					jsonObj.put("LocType", location.getLocType());
					jsonObj.put("Radius", location.getRadius());
					
					if (location.getLocType() == BDLocation.TypeGpsLocation){
						jsonObj.put("Speed", location.getSpeed());
						jsonObj.put("SatelliteNumber", location.getSatelliteNumber());
					} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
						jsonObj.put("AddrStr", location.getAddrStr());
					}
					
					result = new PluginResult(PluginResult.Status.OK, jsonObj);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					result = new PluginResult(PluginResult.Status.JSON_EXCEPTION);	  
				}
				
			}
			
	
			public void onReceivePoi(BDLocation poiLocation) {
				// TODO Auto-generated method stub
				
			}
			
	
		}
	

}
