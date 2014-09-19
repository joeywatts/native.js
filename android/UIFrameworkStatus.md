# Android UI Framework Status

## View

View base class has been implemented. Custom drawing from JavaScript has not been implemented. Supports OnClickListener. Lots of **TODO**sies.

### Subclasses

#### Label

Basic label implemented. Only supports text property.


```javascript

var label = new Label();
label.text = "Hi, I'm a label";

var label2 = new Label("Label overloaded constructor");
```

#### Button

Button implemented. Only supports text property.

```javascript

var button = new Button();
button.text = "Hi, I'm a Button!";
button.addOnClickListener(function() {
	button.text = "I've been clicked";
});

var button = new Button("Constructor with default text");
```

#### Field

Field implemented. Only supports text and hint property.

```javascript

var field = new Field();
field.hint = "Type in something!";

var field = new Field("Default hint");
field.text = "Some text";
```

## Container

Containers have not been implemented yet.

## Screen

Implemented with full-support for lifecycle methods.

```javascript

var screen = new Screen(new Label("hello"));
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
nativeJs.setScreen(screen);
```