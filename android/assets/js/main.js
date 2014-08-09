
var namespace = JavaImporter(Packages.com.wajawinc.nativejs,
	Packages.android.util.Log);

with (namespace) {
	function onCreate() {
		if (osname == 'ANDROID')
			var main_view = activity.findViewById(R.id.main_layout);

		var label = new nativeJsUi.Label();
		label.text = "this is a label";
		label.addToView(main_view);

		var button = new nativeJsUi.Button();
		button.text = "Hello, World!";
		button.onClick = function() {
			Log.d("native.js UI", "the button has been clicked");
			button.onClick = function() {
				Log.d("native.js UI", "the button has been clicked again");
			};
		};
		button.addToView(main_view);

		var textField = new nativeJsUi.TextField();
		textField.text = "this is some text";
		textField.beforeTextChanged = function() {
			Log.d("native.js UI", "text is about to be changed");
		};
		textField.onTextChanged = function() {
			Log.d("native.js UI", "text has just been changed");
		};
		textField.afterTextChanged = function() {
			Log.d("native.js UI", "text has been changed");
		};
		textField.addToView(main_view);
		
		var _switch = new nativeJsUi.Switch();
		_switch.textOn = "on";
		_switch.textOff = "off";
		_switch.state = true;
		_switch.onToggled = function() {
			if (arguments[1])
				Log.d("native.js UI", "this switch is on");
			else
				Log.d("native.js UI", "this switch is off");
		};
		_switch.addToView(main_view);

		var imgView = new nativeJsUi.ImageView();
		imgView.image = "octocat_setup.png";
		imgView.addToView(main_view);
	}
}
