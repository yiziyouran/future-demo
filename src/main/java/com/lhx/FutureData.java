package com.lhx;

public class FutureData implements Data {
	protected RealData realData = null;
	protected boolean isRealdy = false;

	public synchronized void setRealData(RealData realData) {
		if (isRealdy) {
			return;
		}
		this.realData = realData;
		isRealdy = true;
		notifyAll();
	}

	@Override
	public synchronized String getResult() {
		while (!isRealdy) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return realData.getResult();
	}

}
