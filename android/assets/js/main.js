
var button = new Button("This is a button");
var label = new Label("This is a label");
var field = new Field("Hint");

var container = new LinearContainer();
button.horizontalSize = View.fill_container;
label.horizontalAlignment = View.center;
label.verticalAlignment = View.center;
field.horizontalAlignment = View.right;
container.addView(button);
container.addView(label);
container.addView(field);

// Unsupported
var innerLabel1 = new Label("top");
var innerContainer = new LinearContainer(true);
innerContainer.horizontalSize = View.fill_container;
//innerContainer.verticalSize = View.fill_container;
// innerLabel1.verticalAlignment = View.bottom;
innerContainer.addView(innerLabel1);
container.addView(innerContainer);

var screen = new Screen(container);
nativeJs.setScreen(screen);
