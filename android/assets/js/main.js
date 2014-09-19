
var label = new Label("This is a label");
label.addOnClickListener(function() {
	nativeJs.popScreen();
});
var button = new Button("This is a button");
button.addOnClickListener(function() {
	var screen2 = new Screen(label);
	nativeJs.pushScreen(screen2);
});
var screen = new Screen(button);
screen.onScreenResumed = function() {
	Packages.android.util.Log.d("native.js", "onScreenResume from js");
};
nativeJs.setScreen(screen);
