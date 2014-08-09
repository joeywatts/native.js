# native.js

**native.js** is a framework that aims to simplify cross-platform mobile development through the use of JavaScript and an abstracting UI framework that relies on native UI elements.


Because **native.js** runs directly on top of a JavaScript engine and not in a browser, it doesn't have the performance overhead that solutions like PhoneGap have. Moreover, the framework uses native UI elements (instead of those provided by a WebView) for familiar design and no-compromise performance. This is all made possible by the UI framework, which binds the native UI elements of the respective platforms to a JavaScript API that is **platform-agnostic**.

# Project Status

## Android

Very little ground work has been laid at this point. So far, I have started the Android implementation of **native.js**, running on top of Mozilla's *Rhino* JavaScript engine. Another option was Google's *V8* JavaScript engine, but I decided on Rhino because V8 is a much larger library and it uses C++, which would require me to use the NDK, while Rhino is pure Java.

### [Android UI Framework Status](android/UIFrameworkStatus.md)

## iOS

The iOS implementation has not been started yet.

