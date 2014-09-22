function LinearContainer(horiz) {
	var container = new Container();
	container.horizontal = horiz;
	container.onLayout = function(l, t, r, b) {
		var children = container.children;
		var childCount = children.length;

		var containerWidth = r - l - container.paddingLeft - container.paddingRight;
		var containerHeight = b - t - container.paddingTop - container.paddingBottom;

		var top = container.paddingTop;
		var left = container.paddingLeft;
		for (var i = 0; i < childCount; i++) {
			var child = children[i];
			var width = child.width;
			var height = child.height;
			if (container.horizontal) {
				top = container.paddingTop;
			} else {
				left = container.paddingLeft;
			}
			if ("horizontalSize" in child &&
				child.horizontalSize === View.fill_container) {
				width = containerWidth - left + container.paddingLeft;
			}
			if ("verticalSize" in child &&
				child.verticalSize === View.fill_container) {
				height = containerHeight - top + container.paddingTop;
			}
			if (container.horizontal) {
				if ("verticalAlignment" in child) {
					if (child.verticalAlignment === View.center) {
						top = (containerHeight + container.paddingTop + container.paddingBottom - height) / 2;
					} else if (child.verticalAlignment === View.bottom) {
						top = containerHeight - height + container.paddingTop;
					}
				}
				child.layout(left, top, left + width, top + height);
				left += width;
			} else {
				if ("horizontalAlignment" in child) {
					if (child.horizontalAlignment === View.center) {
						left = (containerWidth + container.paddingLeft + container.paddingRight - width) / 2;
					} else if (child.horizontalAlignment === View.right) {
						left = container.paddingLeft + containerWidth - width;
					}
				}
				child.layout(left, top, left + width, top + height);
				top += height;
			}
		}
	};
	container.onMeasure = function() {
		var children = container.children;
		var childCount = children.length;
		var width = 0;
		var height = 0;
		if (container.horizontal) {
			width += container.paddingLeft + container.paddingRight;
		} else {
			height += container.paddingTop + container.paddingBottom;
		}
		for (var i = 0; i < childCount; i++) {
			var child = children[i];
			if (container.horizontal) {
				height = Math.max(height, child.height) + container.paddingTop + container.paddingBottom;
				width += child.width;
			} else {
				height += child.height;
				width = Math.max(width, child.width) + container.paddingLeft + container.paddingRight;
			}
		}
		return [width, height];
	};
	return container;
}
