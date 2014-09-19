package com.wajawinc.nativejs;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wajawinc.nativejs.view.View;

public class Screen extends ScriptableObject {
	private static final long serialVersionUID = 7613706443240765912L;
	private View rootView;
	private JSFragment _fragment;

	public String getClassName() {
		return "Screen";
	}

	public void jsConstructor(View rootView) {
		this.rootView = rootView;
		_fragment = new JSFragment();
	}

	/**
	 * @return this screen's root view
	 */
	public View jsFunction_getRootView() {
		return rootView;
	}

	public Fragment getAndroidFragment() {
		return _fragment;
	}
	
	private void callFunc(String func) {
		if (has(func, Screen.this)) {
			Function f = (Function) get(func, Screen.this);
			Scriptable s = NativeJsActivity.getActivity().getJsScope();
			org.mozilla.javascript.Context c = NativeJsActivity.getActivity().getJsContext();
			f.call(c, s, null, new Object[0]);
		}
	}

	private class JSFragment extends Fragment {
		@Override
		public android.view.View onCreateView(LayoutInflater inflater,
				ViewGroup container, Bundle savedInstanceState) {
			ViewGroup parent = (ViewGroup) rootView.getAndroidView().getParent();
			if (parent != null)
				parent.removeView(rootView.getAndroidView());
			return rootView.getAndroidView();
		}

		@Override
		public void onResume() {
			super.onResume();
			callFunc("onScreenResumed");
		}

		@Override
		public void onPause() {
			super.onPause();
			callFunc("onScreenPaused");
		}

		@Override
		public void onStart() {
			super.onStart();
			callFunc("onScreenShown");
		}

		@Override
		public void onStop() {
			super.onStop();
			callFunc("onScreenHidden");
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			callFunc("onScreenCreated");
			setRetainInstance(true);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			callFunc("onScreenDestroyed");
		}
	}
}
