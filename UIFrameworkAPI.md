# native.js UI Framework

## View
A **View** is any UI element that is drawn to the screen. Buttons, Labels, Fields, etc. are all types of Views.

## Container

A **Container** is a view that contains other views (and other containers, because a Container is a subclass of View) and decides where they are all layed out onto the screen.

## Screen

A **Screen** represents a single screen on an app. Somewhat equivalent to an Android Activity (although, it was implemented as a [Fragment](android/src/com/wajawinc/nativejs/Screen.java)) or an iOS UIViewController. It is the *model* in the MVC paradigm, so all of your logic to wire up your views to your models can go within a Screen object.

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