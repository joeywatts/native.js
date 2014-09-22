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
	private int paddingTop, paddingLeft, paddingRight, paddingBottom;

	public void jsConstructor() {
		children = new ArrayList<View>();
		init(new AndroidViewGroup(NativeJsActivity.getActivity()));
	}

	public NativeArray jsGet_children() {
		NativeArray array = new NativeArray(children.toArray());
		return array;
	}
	
	public void jsFunction_setPadding(int i) {
		jsSet_paddingLeft(i);
		jsSet_paddingRight(i);
		jsSet_paddingTop(i);
		jsSet_paddingBottom(i);
	}
	
	public void jsSet_paddingLeft(int i) {
		paddingLeft = i; 
	}
	
	public void jsSet_paddingRight(int i) {
		paddingRight = i; 
	}
	
	public void jsSet_paddingTop(int i) {
		paddingTop = i; 
	}
	
	public void jsSet_paddingBottom(int i) {
		paddingBottom = i; 
	}
	
	public int jsGet_paddingLeft() {
		return paddingLeft;
	}
	
	public int jsGet_paddingRight() {
		return paddingRight;
	}
	
	public int jsGet_paddingTop() {
		return paddingTop;
	}
	
	public int jsGet_paddingBottom() {
		return paddingBottom;
	}

	public void jsFunction_addView(View v) {
		jsFunction_addViewAt(v, children.size());
	}
	
	public void jsFunction_addViewAt(View v, int index) {
		children.add(index, v);
		((ViewGroup) getAndroidView()).addView(v.getAndroidView(), index);
	}

	public void jsFunction_removeView(View v) {
		children.remove(v);
		((ViewGroup) getAndroidView()).removeView(v.getAndroidView());
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
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			measureChildren(widthMeasureSpec, heightMeasureSpec);
			int width=0, height=0;
			if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY &&
					MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
				NativeArray calc = (NativeArray) callFunc("onMeasure");
				width = ((Number) calc.get(0)).intValue();
				height= ((Number) calc.get(1)).intValue();
			} else {
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
				return;
			}
			if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
				width = MeasureSpec.getSize(widthMeasureSpec);
			} if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
				height = MeasureSpec.getSize(heightMeasureSpec);
			}
			setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), 
					MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
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
