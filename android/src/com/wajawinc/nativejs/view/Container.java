package com.wajawinc.nativejs.view;

import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.NativeArray;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.wajawinc.nativejs.NativeJsActivity;

public class Container extends View {
	private static final long serialVersionUID = 8479335933083554279L;
	private List<View> children;

	public void jsConstructor() {
		children = new ArrayList<View>();
		init(new AndroidViewGroup(NativeJsActivity.getActivity()));
	}

	public NativeArray jsGet_children() {
		NativeArray array = new NativeArray(children.toArray());
		return array;
	}

	public void jsFunction_addView(View v, Integer index) {
		if (index == null)
			index = children.size();
		children.add(index, v);
		((ViewGroup) getAndroidView()).addView(v.getAndroidView(), index);
	}

	public void jsFunction_removeView(View v) {
		children.remove(v);
		((ViewGroup) getAndroidView()).removeView(v.getAndroidView());
	}
	
	public int jsGet_height() {
		return getAndroidView().getHeight();
	}

	public int jsGet_width() {
		return getAndroidView().getWidth();
	}

	public class AndroidViewGroup extends ViewGroup {

		public AndroidViewGroup(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
		}

		public AndroidViewGroup(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public AndroidViewGroup(Context context) {
			super(context);
		}
		
		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			callFunc("onLayout", l, t, r, b);
		}
		
		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			callFunc("onViewAddedToScreen");
		}

		@Override
		protected void onDetachedFromWindow() {
			super.onDetachedFromWindow();
			callFunc("onViewRemovedFromScreen");
		}

	}

	@Override
	public String getClassName() {
		return "Container";
	}
}
