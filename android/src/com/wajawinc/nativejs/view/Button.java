package com.wajawinc.nativejs.view;

import com.wajawinc.nativejs.NativeJsActivity;

public class Button extends View {
	private static final long serialVersionUID = -852888880777945670L;
	public Button() {
		init(new android.widget.Button(NativeJsActivity.getActivity()));
		setPrototype(new View());
	}
	public void jsConstructor(String s) {
		jsSet_text(s);
	}
	public String jsGet_text() {
		return ((android.widget.Button) getAndroidView()).getText().toString();
	}
	public void jsSet_text(String s) {
		((android.widget.Button) getAndroidView()).setText(s);
	}
	@Override
	public String getClassName() {
		return "Button";
	}
}
