<template>
	<div class="ddlscript-element-modal"><slot name="default"></slot></div>
</template>

<script>
export default {
	name: 'ddlscript-element-panel',

	props: {
		hue: { type:String, default:null },
		title: { type:String, default:null },
	},

	computed: {
		isTitleVisible() {
			return this.title ? true : false;
		}
	}
};
</script>
