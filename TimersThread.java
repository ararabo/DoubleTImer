package com.fc2.blog.ararabo;

import android.os.Handler;

public class TimersThread extends Thread {
	//�ϐ�
	private TimersActivity mRT;
	private boolean mRun =false;
	private long mTmStrt;
	private long mTmElapse;
	private int mTmMax;
	private Handler mHandler;
	
//-------�i��؂�j---------------------------------------------
	
	public TimersThread(TimersActivity rt){
		mRT=rt;
		mHandler=new Handler();
		//�X���b�h�̊J�n
		start();
	}
	//�J�n
	public void doStart(int tmMax){
		mTmMax= tmMax;
		mTmStrt=System.currentTimeMillis();
		mRun=true;
	}
	//��~
	public void doStop(){
		mRun=false;
	}
	
	//'run'
	@Override
	public void run(){
		while (true){
			//�i�s�Ǘ�
			try{
				if(mRun){
					sleep(1);//���s��
				}else{
					sleep(500);//��~��
					continue;//�����Ȃ�
				}
			}catch(Exception e){}
			//�o�ߎ���
			mTmElapse=System.currentTimeMillis()-mTmStrt;
			//�I������
			if(mTmElapse>mTmMax){
				mRun=false;
			}
			//���ݎ��ԍX�V
			update();
		}
	}
	
	//���ݎ��ԍX�V�p
	public void update(){
		mHandler.post(new Runnable(){
			@Override
			public void run(){
				//�ϐ��̏�����
				int tmRst=(int)(mTmMax-mTmElapse);
				
				//���ԕ\���̍X�V
				if(tmRst<0){
					tmRst=0;					
				}
				mRT.TimerShow.setText((tmRst/60/1000)+":"+((tmRst/1000)%60)+":"+(tmRst-tmRst/60-((tmRst/1000)%60*1000)/10)%100);
		}
	});
	}
}//Thread��