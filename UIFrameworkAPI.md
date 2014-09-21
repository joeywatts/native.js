# native.js UI Framework

## View
A **View** is any UI element that is drawn to the screen. Buttons, Labels, Fields, etc. are all types of Views. The **View** super class also contains a few enums to help with Containers:

*  `View.left`, `View.center`, `View.right` - for horizontal alignment (`View.horizontalAlignment` property)
*  `View.top`, `View.center`, `View.bottom` - for vertical alignment (`View.verticalAlignment` property)
*  `View.fill_container`, `View.wrap_view` - for view sizing (horizontal - `View.horizontalSize` property & vertical - `View.verticalSize` property)

## Container

A **Container** is a view that contains other views (and other containers, because a Container is a subclass of View) and decides where they are all layed out onto the screen.

```javascript

var container = new Container();
var view = new Label("example view");
container.onLayout = function(left, top, right, bottom) {
	view.layout(left, top, left + view.width, top + view.height);
};
container.addView(view);
```

### LinearContainer

A **LinearContainer** is a container that lays out views linearly either horizontally or vertically in order.

```javascript

// true=horizontal, false (or omitted)=vertical
var container = new LinearContainer(false);
var label1 = new Label("hello, world");
var label2 = new Label("this is the second line");
container.addView(label1);
container.addView(label2);
```

## Screen

A **Screen** represents a single screen on an app. Somewhat equivalent to an Android Activity (although, it was implemented as a [Fragment](android/src/com/wajawinc/nativejs/Screen.java)) or an iOS UIViewController. It is the *model* in the MVC paradigm, so all of your logic to wire up your views to your models can go within a Screen object.

**Screen** supports the following lifecycle methods:

```javascript

var screen = new Screen(myView);
screen.onScreenCreated = function() {
	// called when the screen is created.
};
screen.onScreenShown = function() {
	// called when the screen is displayed on the screen.
};
screen.onScreenResumed = function() {
	// called when the screen starts accepting user input.
};
screen.onScreenPaused = function() {
	// called when the screen stops accepting user input.
};
screen.onScreenHidden = function() {
	// called when the screen is removed from the screen.
};
screen.onScreenDestroyed = function() {
	// called before the screen object is destroyed.
};
```

## Example

With the Screen, Container, and Views put together, we can make fully-functional apps.

```javascript

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
nativeJs.setScreen(screen);
```