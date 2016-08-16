package com.example.textaction;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.textaction.ActionTextView.ModeAction;
import com.example.textaction.ActionTextView.TextStringClickListener;

public class ActionActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_action);
		
		
		ActionTextView textView=(ActionTextView) findViewById(R.id.myTextView);
		
		String source="@Aeiric #���� ������һ���#���� ";
		String action1="@Aeiric ";
		String action2="#���� ";
		textView.setActionTextSpecialOne(source, action1,ModeAction.MODE_ATUSER,Color.parseColor("#00ffff"), new TextStringClickListener() {
			
			@Override
			public void OnStringClick() {
				// TODO Auto-generated method stub
				Toast.makeText(ActionActivity.this, "actionone,��Ӧ1", 1).show();
			}
		});
		textView.setActionTextSpecialOne(source, action2,ModeAction.MODE_TAG,Color.parseColor("#00ffff"), new TextStringClickListener() {
			
			@Override
			public void OnStringClick() {
				// TODO Auto-generated method stub
				Toast.makeText(ActionActivity.this, "actiontwo����Ӧ2", 1).show();
				////hahahahahha
				//2222222222222
				//3333333333333
                               //4444444444
                              ///7777777777
			}
		});
	}
	
}
//888888888
