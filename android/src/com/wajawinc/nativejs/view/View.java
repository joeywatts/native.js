package com.wajawinc.nativejs.view;

import java.util.ArrayList;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.wajawinc.nativejs.NativeJsActivity;

public class View extends ScriptableObject {
	private static final long serialVersionUID = -5177893123940574746L;
	private android.view.View _view;
	private ArrayList<com.wajawinc.nativejs.view.OnClickListener> onClickListeners;
	
	public String getClassName() {
		return "View";
	}
	
	public View() {}
	
	public void jsConstructor() {
		init(new AndroidView(NativeJsActivity.getActivity()));
	}
	
	protected void init(android.view.View v) {
		_view = v;
		onClickListeners = new ArrayList<com.wajawinc.nativejs.view.OnClickListener>();
		_view.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				for (com.wajawinc.nativejs.view.OnClickListener l : onClickListeners) {
					l.onClick();
				}
			}
		});
	}
	
	/**
	 * Called when the View is added to the Screen.
	 */
	public void jsFunction_onViewAddedToScreen() {}
	
	/**
	 * Called when the View is removed from the Screen.
	 */
	public void jsFunction_onViewRemovedFromScreen() {}
	
	/**
	 * Cause the screen to redraw the view.
	 */
	public void jsFunction_invalidateView() {
		_view.postInvalidate();
	}
	
	/**
	 * Called when the View is drawn to the Screen
	 */
	public void jsFunction_drawView() {
	}
	
	public void jsFunction_addOnClickListener(Function o) {
		onClickListeners.add(newOnClickListener(o));
	}
	
	private OnClickListener newOnClickListener(final Function f) {
		return new OnClickListener() {
			@Override
			public void onClick() {
				Scriptable s = NativeJsActivity.getActivity().getJsScope();
				org.mozilla.javascript.Context c = NativeJsActivity.getActivity().getJsContext();
				f.call(c, s, null, new Object[0]);
			}
		};
	}
	
	public android.view.View getAndroidView() {
		return _view;
	}
	
	private class AndroidView extends android.view.View {
		
		public AndroidView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}

		public AndroidView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public AndroidView(Context context) {
			super(context);
		}
		
		@Override
		public void draw(Canvas canvas) {
			/* TODO: implement custom drawing in js */
			super.draw(canvas);
			jsFunction_drawView();
		}
		
		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			jsFunction_onViewAddedToScreen();
		}
		
		@Override
		protected void onDetachedFromWindow() {
			super.onDetachedFromWindow();
			jsFunction_onViewRemovedFromScreen();
		}
	}
}
