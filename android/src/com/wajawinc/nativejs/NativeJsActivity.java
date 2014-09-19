package com.wajawinc.nativejs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.wajawinc.nativejs.view.Button;
import com.wajawinc.nativejs.view.Field;
import com.wajawinc.nativejs.view.Label;
import com.wajawinc.nativejs.view.View;

public class NativeJsActivity extends FragmentActivity {

	private static NativeJsActivity instance;
	protected Context jsContext;
	protected Scriptable jsScope;

	private Screen currentScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.activity_native_js);
		initJsContext();
		loadJsScript("js/main.js");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Context.exit();
	}

	public void setScreen(Screen screen) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_layout, screen.getAndroidFragment())
				.commit();
		currentScreen = screen;
	}

	public void popScreen() {
		getSupportFragmentManager().popBackStack();
	}
	
	public void pushScreen(Screen screen) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_layout, screen.getAndroidFragment())
				.addToBackStack("").commit();
		currentScreen = screen;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}

	protected void initJsContext() {
		jsContext = Context.enter();
		jsContext.setOptimizationLevel(-1);
		jsScope = jsContext.initStandardObjects();
		initStandardObjects();
		wrapConstObject("osname", "ANDROID");
		wrapClass(Screen.class);
		wrapClass(View.class);
		wrapClass(Button.class);
		wrapClass(Field.class);
		wrapClass(Label.class);
	}

	protected void loadJsScript(String fileName) {
		try {
			jsContext.evaluateReader(jsScope, new InputStreamReader(getAssets()
					.open(fileName)), fileName, 1, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void initStandardObjects() {
		wrapObject("nativeJs", this);
	}

	protected void wrapClass(Class<? extends Scriptable> clazz) {
		try {
			ScriptableObject.defineClass(jsScope, clazz, false, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void wrapConstObject(String name, Object o) {
		Object wrappedObj = Context.javaToJS(o, jsScope);
		ScriptableObject.putConstProperty(jsScope, name, wrappedObj);
	}

	protected void wrapObject(String name, Object o) {
		Object wrappedObj = Context.javaToJS(o, jsScope);
		ScriptableObject.putProperty(jsScope, name, wrappedObj);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.native_js, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public Context getJsContext() {
		return jsContext;
	}
	
	public Scriptable getJsScope() {
		return jsScope;
	}
	
	public static NativeJsActivity getActivity() {
		return instance;
	}
}
