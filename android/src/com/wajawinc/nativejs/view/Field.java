package com.wajawinc.nativejs.view;

import android.widget.EditText;

import com.wajawinc.nativejs.NativeJsActivity;

public class Field extends View {
	private static final long serialVersionUID = -4108851142831283801L;

	/**
	 * Instantiate a new Field view.
	 */
	public Field() {
		init(new EditText(NativeJsActivity.getActivity()));
	}
	
	/**
	 * Instantiate a new Field view.
	 * @param hint the Field's hint
	 */
	public Field(String hint) {
		this();
		jsSet_hint(hint);
	}
	
	public void jsSet_hint(String s) {
		((EditText) getAndroidView()).setHint(s);
	}
	
	public String jsGet_hint() {
		return ((EditText) getAndroidView()).getHint().toString();
	}
	
	public void jsSet_text(String s) {
		((EditText) getAndroidView()).setText(s);
	}
	
	public String jsGet_text() {
		return ((EditText) getAndroidView()).getText().toString();
	}
	
	public void onFieldChanged() {}
	
	@Override
	public String getClassName() {
		return "Field";
	}
}
