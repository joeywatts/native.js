package com.wajawinc.nativejs.view;

import java.util.ArrayList;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.FunctionObject;
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
	
	protected void callFunc(String func, Object... params) {
		if (has(func, this)) {
			Function f = (Function) get(func, this);
			Scriptable s = NativeJsActivity.getActivity().getJsScope();
			org.mozilla.javascript.Context c = NativeJsActivity.getActivity().getJsContext();
			f.call(c, s, null, params);
		}
	}
	
	/**
	 * Cause the screen to redraw the view.
	 */
	public void jsFunction_invalidateView() {
		_view.postInvalidate();
	}
	
	public void jsFunction_layout(int l, int t, int r, int b) {
		_view.layout(l, t, r, b);
	}
	
	public int jsGet_width() {
		return _view.getMeasuredWidth();
	}
	
	public int jsGet_height() {
		return _view.getMeasuredHeight();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o == null)
			return false;
		if (o instanceof View)
			return _view.equals(((View)o).getAndroidView());
		return false;
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
	
	public static final int LEFT = 0, CENTER = 1, RIGHT = 2, TOP = 0, BOTTOM = 2;
	public static final int WRAP_VIEW = 0, FILL_CONTAINER = 1;
	
	public static void finishInit(Scriptable scope, FunctionObject constructor,
			Scriptable prototype) {
		int flags = DONTENUM | READONLY | PERMANENT;
		/* for horizontalSize, verticalSize */
		constructor.defineProperty("wrap_view", WRAP_VIEW, flags);
		constructor.defineProperty("fill_container", FILL_CONTAINER, flags);
		
		/* for verticalAlignment, horizontalAlignment */
		constructor.defineProperty("left", LEFT, flags);
		constructor.defineProperty("center", CENTER, flags);
		constructor.defineProperty("right", RIGHT, flags);
		constructor.defineProperty("top", TOP, flags);
		constructor.defineProperty("bottom", BOTTOM, flags);
	}
	
	public class AndroidView extends android.view.View {
		
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
			callFunc("drawView");
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
}
