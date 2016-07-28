package com.example.textaction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * �Զ���TextView�������ֵ����ת custtom textview that you can set action by the format
 * you need
 * 
 * @author Aeiric 2015.05.14
 * 
 */

public class ActionTextView extends TextView {

         ///ssssssssss
	// You can declare the format by your need here
	// like "#xxx "(note:there is a blank space at the end)
	private static final Pattern tag = Pattern.compile("#(.*?) ");
	// like "@xxx "(note:there is a blank space at the end)
	private static final Pattern atUser = Pattern.compile("@(.+?) ");

	private SpannableString ss;

	public ActionTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ActionTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public interface TextStringClickListener {
		void OnStringClick();
	}

	public void setOnTextStringClickListener() {

	}

	public ActionTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	//refresh the textview ;
	public void onrefresh() {
		ss = null;
	}

	/**
	 * 
	 * @param source
	 *            text�ַ� the source text in the textview
	 * @param actionStr
	 *            Ҫ������ת���ַ� the action String you need to set
	 * @param mode
	 *            ��תģʽ��������#�ŵ�tag��ǩModeAcrion.MODE_TAG�Լ�@�û�MODE_ATUSER�� the
	 *            action mode ��in this demo there are two diffrent types
	 *            ,ModeAcrion.MODE_TAG replace "#xxx " and MODE_ATUSER replace
	 *            "@xxx "
	 * @param color
	 *            ������ת�������ɫ the color of the action string you want set
	 * @param listener
	 *            ��ת������ the listener in which you can write your action code
	 */
	public void setActionTextSpecialOne(String source, String actionStr,
			int mode, final int color, final TextStringClickListener listener) {
		if (ss == null) {
			ss = new SpannableString(source);
		}
		Pattern pattern = null;
		// select the format you need
		switch (mode) {
		case ModeAction.MODE_TAG:
			pattern = tag;
			break;
		case ModeAction.MODE_ATUSER:
			pattern = atUser;
			break;
		}

		// match all the fomated place and set action
		Matcher matcher = pattern.matcher(source);
		int end = 0;
		while (matcher.find()) {
			String key = matcher.group();
			if (!"".equals(key) && actionStr.equals(key)) {
				int start = source.indexOf(key, end);
				end = start + key.length();
				ss.setSpan(new ClickableSpan() {
					@Override
					public void updateDrawState(TextPaint ds) {
						// TODO Auto-generated method stub
						// super.updateDrawState(ds);
						ds.setUnderlineText(false);
						ds.setColor(color);

					}

					@Override
					public void onClick(View widget) {
						// TODO Auto-generated method stub
						listener.OnStringClick();
					}
				}, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
		setText(ss);
		setMovementMethod(LinkMovementMethod.getInstance());
		//33333333333

	}

	// replace the format type you declare while MODE_TAG represent Pattern tag
	// and MODE_ATUSER represent Pattern atuser
	public class ModeAction {
		public static final int MODE_TAG = 1;
		public static final int MODE_ATUSER = 2;
	}
}
