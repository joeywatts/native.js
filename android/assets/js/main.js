
var button = new Button("This is a button");
var label = new Label("This is a label");
var field = new Field("Hint");

var container = new LinearContainer();
container.setPadding(200);
button.horizontalSize = View.fill_container;
label.horizontalAlignment = View.center;
label.verticalAlignment = View.center;
field.horizontalAlignment = View.right;
container.addView(button);
container.addView(label);
container.addView(field);

var screen = new Screen(container);
nativeJs.setScreen(screen);
