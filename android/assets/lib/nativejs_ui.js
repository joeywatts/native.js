/* UI framework for native.js */
/* Android implementation */
var nativeJsUi = {};

/* View */
nativeJsUi.View = function() {};
with(nativeJsUi) {
    View.prototype.addToView = function(view) {
        if (typeof this.androidView !== 'undefined')
            view.addView(this.androidView);
    };
}

/* Button */
nativeJsUi.Button = function() {
    this.androidView = new Packages.android.widget.Button(activity);
    this.androidView.setLayoutParams(new Packages.android.widget.LinearLayout.LayoutParams(
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
};
with(nativeJsUi) {
	Button.prototype = new View();
    Object.defineProperty(Button.prototype, "text", {
        get: function() {
            return this.androidView.getText();
        },
        set: function(t) {
            this.androidView.setText(t);
        }
    });
    Object.defineProperty(Button.prototype, "onClick", {
        get: function() {
            return this.onClickFunc;
        },
        set: function(listener) {
            this.onClickFunc = listener;
            this.androidView.setOnClickListener(listener);
        }
    });
}

/* TextField */
nativeJsUi.TextField = function() {
	this.androidView = new Packages.android.widget.EditText(activity);
	this.androidView.setLayoutParams(new Packages.android.widget.LinearLayout.LayoutParams(
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
	var _this = this;
	this.androidView.addTextChangedListener(new Packages.android.text.TextWatcher({
		onTextChanged: function() { 
			if (typeof _this.onTextChangedFunc !== 'undefined')
				return _this.onTextChangedFunc(arguments); 
		},
		beforeTextChanged: function() { 
			if (typeof _this.beforeTextChangedFunc !== 'undefined')
				return _this.beforeTextChangedFunc(arguments); 
		},
		afterTextChanged: function() {
			if (typeof _this.afterTextChangedFunc !== 'undefined')
				return _this.afterTextChangedFunc(arguments); 
		}
	}));
};
with(nativeJsUi) {
	TextField.prototype = new View();
	Object.defineProperty(TextField.prototype, "text", {
		get: function () {
			return this.androidView.getText();
		}, set: function (t) {
			this.androidView.setText(t);
		}
	});
	Object.defineProperty(TextField.prototype, "onTextChanged", {
		get: function () {
			return this.onTextChangedFunc;
		}, set: function(func) {
			this.onTextChangedFunc = func;
		}
	});
	Object.defineProperty(TextField.prototype, "beforeTextChanged", {
		get: function() { return this.beforeTextChangedFunc; },
		set: function(func) { this.beforeTextChangedFunc = func; }
	});
	Object.defineProperty(TextField.prototype, "afterTextChanged", {
		get: function() { return this.afterTextChangedFunc; },
		set: function(func) { this.afterTextChangedFunc = func; }
	});
}

/* Label */
nativeJsUi.Label = function() {
	this.androidView = new Packages.android.widget.TextView(activity);
	this.androidView.setLayoutParams(new Packages.android.widget.LinearLayout.LayoutParams(
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
};
with(nativeJsUi) {
	Label.prototype = new View();
	Object.defineProperty(Label.prototype, "text", {
		get: function() {
			return this.androidView.getText();
		}, set: function(t) {
			this.androidView.setText(t);
		}
	});
}

/* Switch */
nativeJsUi.Switch = function() {
	this.androidView = new Packages.android.widget.Switch(activity);
	this.androidView.setLayoutParams(new Packages.android.widget.LinearLayout.LayoutParams(
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
};
with(nativeJsUi) {
	Switch.prototype = new View();
	Object.defineProperty(Switch.prototype, "textOn", {
		get: function() {
			return this.androidView.getTextOn();
		}, set: function (t) {
			this.androidView.setTextOn(t);
		}
	});
	Object.defineProperty(Switch.prototype, "textOff", {
		get: function() {
			return this.androidView.getTextOff();
		}, set: function(t) {
			this.androidView.setTextOff(t);
		}
	});
	Object.defineProperty(Switch.prototype, "onToggled", {
		get: function() {
			return this.onToggledFunc;
		}, set: function(func) {
			this.onToggledFunc = func;
			this.androidView.setOnCheckedChangeListener(func);
		}
	});
	Object.defineProperty(Switch.prototype, "state", {
		get: function() {
			return this.androidView.isChecked();
		}, set: function(s) {
			this.androidView.setChecked(s);
		}
	});
}

/* CheckBox */
with(nativeJsUi) {}

/* RadioButton */
with(nativeJsUi) {}

/* Stepper */
with(nativeJsUi) {}

/* Slider */
with(nativeJsUi) {}

/* SegmentedControl */
with(nativeJsUi) {}

/* PopupMenu */
with(nativeJsUi) {}

/* Picker */
with(nativeJsUi) {}

/* DatePicker */
with(nativeJsUi) {}

/* ImageView */
nativeJsUi.ImageView = function() {
	this.androidView = new Packages.android.widget.ImageView(activity);
	this.androidView.setLayoutParams(new Packages.android.widget.LinearLayout.LayoutParams(
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        Packages.android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
};
with(nativeJsUi) {
	ImageView.prototype = new View();
	Object.defineProperty(ImageView.prototype, "image", {
		set: function(img) {
			if (typeof img === 'number') {
				/* Resource ID */
				this.androidView.setImageResource(img);
			} else if (typeof img === 'string') {
				/* File */
				if (img.lastIndexOf('/',0) != 0)
					this.androidView.setImageDrawable(Packages.android.graphics.drawable.Drawable.createFromStream(
						activity.getAssets().open(img),
						img
						));
				else
					this.androidView.setImageDrawable(Packages.android.graphics.drawable.Drawable.createFromPath(img));
			}
		}
	});
}

/* ListView */
with(nativeJsUi) {}

/* GridView */
with(nativeJsUi) {}

/* NavigationBar */
with(nativeJsUi) {}

/* BarButton */
with(nativeJsUi) {}

/* TabBar */
with(nativeJsUi) {}

/* ToolBar */
with(nativeJsUi) {}

/* Menu */
with(nativeJsUi) {}

/* SearchField */
with(nativeJsUi) {}

/* ScrollView */
with(nativeJsUi) {}

/* PageControl */
with(nativeJsUi) {}

/* NavigationDrawer */
with(nativeJsUi) {}

/* WebView */
with(nativeJsUi) {}

/* RefreshControl */
with(nativeJsUi) {}

/* ProgressView */
with(nativeJsUi) {}

/* AlertView */
with(nativeJsUi) {}
