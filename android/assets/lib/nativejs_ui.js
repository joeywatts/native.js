function LinearContainer(horiz) {
	var container = new Container();
	container.horizontal = horiz;
	container.onLayout = function(l, t, r, b) {
		var children = container.children;
		var childCount = children.length;
		for (var i = 0; i < childCount; i++) {
			var child = children[i];
			var width = child.width;
			var height = child.height;
			nativeJs.log(width + ' ' + height);
			if ("horizontalSize" in child &&
				child.horizontalSize === View.fill_container) {
				width = r - l;
			}
			if ("verticalSize" in child &&
				child.verticalSize === View.fill_container) {
				height = b - t;
			}
			if (container.horizontal) {
				var top = t;
				if ("verticalAlignment" in child) {
					if (child.verticalAlignment === View.center) {
						top = (b - t - height) / 2;
					} else if (child.verticalAlignment === View.bottom) {
						top = b - height;
					}
				}
				child.layout(l, top, l + width, top + height);
				l += width;
			} else {
				var left = l;
				if ("horizontalAlignment" in child) {
					if (child.horizontalAlignment === View.center) {
						left = (r - l - width) / 2;
					} else if (child.horizontalAlignment === View.right) {
						left = r - width;
					}
				}
				child.layout(left, t, left + width, t + height);
				t += height;
			}
		}
	};
	container.onMeasure = function() {
		var children = container.children;
		var childCount = children.length;
		var width = 0;
		var height = 0;
		for (var i = 0; i < childCount; i++) {
			var child = children[i];
			/*if (("verticalSize" in child &&
					child.verticalSize === View.fill_container) ||
				("horizontalSize" in child &&
					child.horizontalSize === View.fill_container)) {
				continue;
			}*/
			if (container.horizontal) {
				//if (!("verticalSize" in child &&
				//	child.verticalSize === View.fill_container)) {
					height = Math.max(height, child.height);
					//}
				//if (!("horizontalSize" in child &&
				//	child.horizontalSize === View.fill_container)) {
					width += child.width;
					//}
			} else {
				//if (!("verticalSize" in child &&
				//	child.verticalSize === View.fill_container)) {
					height += child.height;
					//}
				//if (!("horizontalSize" in child &&
				//	child.horizontalSize === View.fill_container)) {
					width = Math.max(width, child.width);
					//}
			}
		}
		nativeJs.log('width: ' + width + ' height: ' + height);
		return [width, height];
	};
	return container;
}
