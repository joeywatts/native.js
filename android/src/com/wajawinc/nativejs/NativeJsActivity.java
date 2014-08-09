package com.wajawinc.nativejs;

import java.io.IOException;
import java.io.InputStreamReader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class NativeJsActivity extends ActionBarActivity {

	protected Context jsContext;
	protected Scriptable jsScope;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_js);
        initJsContext();
        loadJsScript("lib/nativejs_ui.js");
        loadJsScript("js/main.js");
        jsContext.evaluateString(jsScope, "onCreate();", "<onCreate>", 1, null);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Context.exit();
    }
    
    protected void initJsContext() {
    	jsContext = Context.enter();
        jsContext.setOptimizationLevel(-1);
        jsScope = jsContext.initStandardObjects();
        initStandardObjects();
        wrapConstObject("osname", "ANDROID");
    }
    
    protected void loadJsScript(String fileName) {
    	try {
        	jsContext.evaluateReader(jsScope, new InputStreamReader(getAssets().open(fileName)), fileName, 1, null);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    protected void initStandardObjects() {
    	wrapObject("activity", this);
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
}
