<template>
	<div class='ddlscript-element-popover' v-if="is.containerVisible" v-click-away="onClickAway"><slot name="default" /></div>
</template>

<script>

export default {
	name: 'ddlscript-element-popover',

	props: {
		target: { type:String, default:null },
		trigger: { type:String, default:"click" },
		position: { type:String, default:"bottom-end" },
	},

	data: () => ({
		is: {
			containerVisible: false
		}
	}),

	methods: {
		onTrigger(evt) {
			this.is.containerVisible = true;
			this.$nextTick(() => {
				const elSelf = this.$el;
				const elTarget = evt.target;

				var offsetLeftSelf = 0;
				var offsetTopSelf = 0;
				var parent = elSelf;
				while (this.is.containerVisible && parent) {
					offsetLeftSelf += parent.offsetLeft;
					offsetTopSelf += parent.offsetTop;
					parent = parent.offsetParent;
				}

				var offsetLeftTarget = 0;
				var offsetTopTarget = 0;
				parent = elTarget;
				while (parent) {
					offsetLeftTarget += parent.offsetLeft;
					offsetTopTarget += parent.offsetTop;
					parent = parent.offsetParent;
				}

				var position = this.position.split("-");
				var p0 = "bottom";
				var p1 = "center";
				if (["top", "bottom", "left", "right"].includes(position[0])) {
					p0 = position[0];
					if (position.length > 1) {
						if (["start", "end", "center"].includes(position[1])) {
							p1 = position[1];
						}
					}
				}

				this.$el.style.left = this.calcLeftPosition(offsetLeftSelf, elSelf.offsetWidth, offsetLeftTarget, elTarget.offsetWidth, p0, p1) + "px";
				this.$el.style.top = this.calcTopPosition(offsetTopSelf, elSelf.offsetHeight, offsetTopTarget, elTarget.offsetHeight, p0, p1) + "px";
			});

		},

		calcLeftPosition(selfLeft, selfWidth, targetLeft, targetWidth, position, placement) {
			var offset = targetLeft - selfLeft;

			if (["top", "bottom"].includes(position)) {

				if ("center" == placement) {
					offset += (targetWidth - selfWidth) / 2;
				}

				if ("end" == placement) {
					offset += (targetWidth - selfWidth);
				}
			}

			if ("left" == position) {
				offset -= selfWidth;
			}

			if ("right" == position) {
				offset += targetWidth;
			}

			return offset;
		},

		calcTopPosition(selfTop, selfHeight, targetTop, targetHeight, position, placement) {
			var offset = targetTop - selfTop;

			if (["left", "right"].includes(position)) {

				if ("center" == placement) {
					offset += (targetHeight - selfHeight) / 2;
				}

				if ("end" == placement) {
					offset += (targetHeight - selfHeight);
				}
			}

			if ("top" == position) {
				offset -= selfHeight;
			}

			if ("bottom" == position) {
				offset += targetHeight;
			}

			return offset;
		},

		onClickAway() {
			this.is.containerVisible && (this.is.containerVisible = false);
		}
	},

	mounted() {
		var elTarget = document.querySelector(this.target);
		if (!elTarget) {
			console.warn("No Target Element");
			return;
		}

		elTarget.addEventListener(this.trigger, this.onTrigger);
	}
}
</script>
