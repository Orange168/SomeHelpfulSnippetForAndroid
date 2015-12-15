static class MySensorEventListener implements SensorEventListener {

		SensorManager mSensorMgr ;
		PowerManager mPowerMgr ;
		PowerManager.WakeLock mWakeLock ;

		public MySensorEventListener(Context context) {
			mSensorMgr = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
			mPowerMgr = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
			mWakeLock = mPowerMgr.newWakeLock(32, "AvActivity");
		}

		public void onStart(){
			mSensorMgr.registerListener(this, mSensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY),
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		/**
		 * release resource Activity onDestroy invoke
		 */
		public void onDestroy(){
			if (mWakeLock.isHeld()) mWakeLock.release();
			mSensorMgr.unregisterListener(this);
		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			float values[] = event.values;
			if (values != null && event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
				Log.e("sensor", "value ==>>" + values[0]);
				if (values[0] == 0) { // when face close to phone
					if (!mWakeLock.isHeld()){
						mWakeLock.acquire();
					}
				}else { // when face far from phone
					if (mWakeLock.isHeld()){
						mWakeLock.setReferenceCounted(false);
						mWakeLock.release();
					}
				}
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	}
