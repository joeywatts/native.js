package com.wajawinc.nativejs.view;

import android.widget.TextView;

import com.wajawinc.nativejs.NativeJsActivity;

public class Label extends View {
	private static final long serialVersionUID = -1066136889855797684L;
	public Label() {
		init(new TextView(NativeJsActivity.getActivity()));
		setPrototype(new View());
	}
	public void jsConstructor(String s) {
		jsSet_text(s);
	}
	public void jsSet_text(String s) {
		((TextView) this.getAndroidView()).setText(s);
	}
	public String jsGet_text() {
		return ((TextView) this.getAndroidView()).getText().toString();
	}
	
	public String getClassName() {
		return "Label";
	}
}
