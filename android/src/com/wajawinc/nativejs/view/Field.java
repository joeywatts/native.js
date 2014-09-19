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
		setPrototype(new View());
	}
	
	/**
	 * Instantiate a new Field view.
	 * @param hint the Field's hint
	 */
	public Field(String hint) {
		this();
		setHint(hint);
	}
	
	public void setHint(String s) {
		((EditText) getAndroidView()).setHint(s);
	}
	
	public String getHint() {
		return ((EditText) getAndroidView()).getHint().toString();
	}
	
	public void setText(String s) {
		((EditText) getAndroidView()).setText(s);
	}
	
	public String getText() {
		return ((EditText) getAndroidView()).getText().toString();
	}
	
	public void onFieldChanged() {}
}
