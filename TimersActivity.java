package com.fc2.blog.ararabo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class TimersActivity extends Activity {

	// private int Numst;
	private int mNocalc_Min;
	private int mNocalc_Sec;
	private int mNocalc_MiliSec;
	private int mNoInputMin;
	private int mNoInputSec;
	private int mNoInputMiliSec;
	// �萔
	private final int BTN_Number_Max = 8;
	private final int mSec_Max = 60;
	private final int mMiliSec_Max = 100;
	private final int mMin_Min = 0;
	private final int mSec_Min = 0;
	private final int mMiliSec_Min = 0;
	// �\��
	private EditText nMin;
	private EditText nSec;
	private EditText nMiliSec;
    public TextView TimerShow;
	private int mTmpNo;
	private TimersThread mTT;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// �\�����̎擾
		nMin = (EditText) findViewById(R.id.editText1);
		nSec = (EditText) findViewById(R.id.editText2);
		nMiliSec = (EditText) findViewById(R.id.editText3);

		//���ԕ\���e�L�X�g�̎擾
		TimerShow=(TextView)findViewById(R.id.textView1);
		// �{�^���̎擾
		Button[] btns = new Button[BTN_Number_Max];

		btns[0] = (Button) findViewById(R.id.button0);
		btns[1] = (Button) findViewById(R.id.button1);
		btns[2] = (Button) findViewById(R.id.button2);
		btns[3] = (Button) findViewById(R.id.button3);
		btns[4] = (Button) findViewById(R.id.button4);
		btns[5] = (Button) findViewById(R.id.button5);
		btns[6] = (Button) findViewById(R.id.button6);
		btns[7] = (Button) findViewById(R.id.button7);

		/*
		 * View.OnClickListener cad = new View.OnClickListener() { public void
		 * onClick(View v) {
		 * Numst=Integer.parseInt(((Button)v).getText().toString()); if
		 * ((Numst=='0')||(Numst=='1')||(Numst=='2')){ Add(Numst,true); } else{
		 * Dec(Numst,true); } } }; for (int i=0; i<BTN_Number_Max; i++){
		 * btns[i].setOnClickListener(cad); }
		 */
//--------------------�{�^���ݒ�----------------------------------------------------//
		btns[0].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Add('0', true);
			}
		});
		btns[1].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Add('1', true);
			}
		});
		btns[2].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Add('2', true);
			}
		});
		btns[3].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Dec('3', true);
			}
		});
		btns[4].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Dec('4', true);
			}
		});
		btns[5].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Dec('5', true);
			}
		});
		// �ŏ��̍X�V
		Add('0', true);
		Add('1', true);
		Add('2', true);
		
//-------------------�J�n�{�^���ݒ�[����]----------------------------------//
		//Button btns[6]=(Button)findViewById(R.id.button6);
		btns[6].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//resetEditView();//���͗��̃��Z�b�g
				mTT.doStart(mNocalc_Min*60*1000+mNocalc_Sec*1000+mNocalc_MiliSec*10);     //�J�n
			}
		});
		
//-------------------��~�{�^���ݒ�----------------------------------//
		//Button btnRst =(Button)findViewById(R.id.button7);
		btns[7].setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				mTT.doStop();
			}
		});
		
		//�^�C�}�[�X���b�h
		mTT=new TimersThread(this);
	}// onCreate��


//--------------�{�^�������Ɓ{�P�����--------------------------//
	private void Add(int no, boolean add) {
		if (add) {
			if (no == '0') {
				mNocalc_Min = mNoInputMin + 1;
				mNoInputMin = mNocalc_Min;
				nMin.setText("" + mNocalc_Min);
			} else if (no == '1') {
				mNocalc_Sec = mNoInputSec + 1;
				mNoInputSec = mNocalc_Sec;
				if(mNoInputSec>mSec_Max-1){
					mNocalc_Sec=0;
					mNoInputSec=mNocalc_Sec;
					nSec.setText(""+mNocalc_Sec);
				}else{
				nSec.setText("" + mNocalc_Sec);
				}
			} else if (no == '2') {
				mNocalc_MiliSec = mNoInputMiliSec + 1;
				mNoInputMiliSec = mNocalc_MiliSec;
				if(mNoInputMiliSec>mMiliSec_Max-1){
					mNocalc_MiliSec=0;
					mNoInputMiliSec=mNocalc_MiliSec;
					nMiliSec.setText(""+mNocalc_MiliSec);
				}else{
				nMiliSec.setText("" + mNocalc_MiliSec);
			}
			}
		}
	}
//---------------�{�^�������Ɓ|1�����-----------------------//
	private void Dec(int no, boolean dec) {
		if (dec) {
			if (no == '3') {
				mNocalc_Min = mNoInputMin - 1;
				mNoInputMin = mNocalc_Min;
				if (mNoInputMin < mMin_Min + 1) {
					mNocalc_MiliSec = 0;
					mNoInputMin = mNocalc_Min;
					nMin.setText("" + mNocalc_Min);
				} else {
					nMin.setText("" + mNocalc_Min);
				}
			} else if (no == '4') {
				mNocalc_Sec = mNoInputSec - 1;
				mNoInputSec = mNocalc_Sec;
				if (mNoInputSec < mSec_Min ) {
					mNocalc_Sec = 59;
					mNoInputSec = mNocalc_Sec;
					nSec.setText("" + mNocalc_Sec);
				} else {
					nSec.setText("" + mNocalc_Sec);
				}
			} else if (no == '5') {
				mNocalc_MiliSec = mNoInputMiliSec - 1;
				mNoInputMiliSec = mNocalc_MiliSec;
				if (mNoInputMiliSec < mMiliSec_Min ) {
					mNocalc_MiliSec = 99;
					mNoInputMiliSec = mNocalc_MiliSec;
					nMiliSec.setText("" + mNocalc_MiliSec);
				} else {
					nMiliSec.setText("" + mNocalc_MiliSec);
				}
			}
		}
	}
	//---------���͗��̃��Z�b�g--------------------//
	public void resetEditView(){
		nMin.setText("0");
		nSec.setText("0");
		nMiliSec.setText("0");
	}
}// Activity��