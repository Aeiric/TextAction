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
 * 自定义TextView部分文字点击跳转 custtom textview that you can set action by the format
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
	 *            text字符串 the source text in the textview
	 * @param actionStr
	 *            要设置跳转的字符串 the action String you need to set
	 * @param mode
	 *            跳转模式（分两种#号的tag标签ModeAcrion.MODE_TAG以及@用户MODE_ATUSER） the
	 *            action mode （in this demo there are two diffrent types
	 *            ,ModeAcrion.MODE_TAG replace "#xxx " and MODE_ATUSER replace
	 *            "@xxx "
	 * @param color
	 *            设置跳转字体的颜色 the color of the action string you want set
	 * @param listener
	 *            跳转监听器 the listener in which you can write your action code
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

	}

	// replace the format type you declare while MODE_TAG represent Pattern tag
	// and MODE_ATUSER represent Pattern atuser
	public class ModeAction {
		public static final int MODE_TAG = 1;
		public static final int MODE_ATUSER = 2;
	}
}
