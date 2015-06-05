	private Boolean wifiFlag = false ;
	private WifiManager wifiManager;
	WifiConfiguration apConfig = null ;

private void initWifiConfigure() {
		apConfig = new WifiConfiguration();
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	}

	private void switchWifiSSID() {

		apConfig.SSID = (wifiFlag? "\"ANL-DEV-TEAM\"" :"\"ANL-GUEST\"");
		apConfig.preSharedKey= (wifiFlag? "\"20131017\"" :"\"075586503590\"");
		apConfig.status = WifiConfiguration.Status.ENABLED;
		int wcgID = wifiManager.addNetwork(apConfig) ;
		System.out.println("connect success "+wifiManager.enableNetwork(wcgID,true));
		wifiFlag = ! wifiFlag ;

	}
