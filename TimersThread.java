package com.fc2.blog.ararabo;

import android.os.Handler;

public class TimersThread extends Thread {
	//変数
	private TimersActivity mRT;
	private boolean mRun =false;
	private long mTmStrt;
	private long mTmElapse;
	private int mTmMax;
	private Handler mHandler;
	
//-------（区切り）---------------------------------------------
	
	public TimersThread(TimersActivity rt){
		mRT=rt;
		mHandler=new Handler();
		//スレッドの開始
		start();
	}
	//開始
	public void doStart(int tmMax){
		mTmMax= tmMax;
		mTmStrt=System.currentTimeMillis();
		mRun=true;
	}
	//停止
	public void doStop(){
		mRun=false;
	}
	
	//'run'
	@Override
	public void run(){
		while (true){
			//進行管理
			try{
				if(mRun){
					sleep(1);//実行時
				}else{
					sleep(500);//停止時
					continue;//処理なし
				}
			}catch(Exception e){}
			//経過時間
			mTmElapse=System.currentTimeMillis()-mTmStrt;
			//終了判定
			if(mTmElapse>mTmMax){
				mRun=false;
			}
			//現在時間更新
			update();
		}
	}
	
	//現在時間更新用
	public void update(){
		mHandler.post(new Runnable(){
			@Override
			public void run(){
				//変数の初期化
				int tmRst=(int)(mTmMax-mTmElapse);
				
				//時間表示の更新
				if(tmRst<0){
					tmRst=0;					
				}
				mRT.TimerShow.setText((tmRst/60/1000)+":"+((tmRst/1000)%60)+":"+(tmRst-tmRst/60-((tmRst/1000)%60*1000)/10)%100);
		}
	});
	}
}//Threadの