
function onClick() {
	label.text = "We are at index " + index;
	nativeJs.pushScreen(screens[(index++)%screens.length]);
}

var button = new Button("This is a button");
button.addOnClickListener(onClick);
var screen = new Screen(button);

var label = new Label("This is a label");
label.addOnClickListener(onClick);
var screen2 = new Screen(label);

var field = new Field("Hint");
var screen3 = new Screen(field);

var screens = [screen, screen2, screen3];

var index = 0;
onClick();
