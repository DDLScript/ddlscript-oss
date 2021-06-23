<template>
	<ddlscript-layout-center class="ddlscript-element-emptystate">
		<span class="ddlscript-element-emptystate-title">{{ title }}</span>
		<div class="ddlscript-element-emptystate-content"><slot name="default"></slot></div>
	</ddlscript-layout-center>
</template>

<script>
import CenterLayout from "../layouts/center.vue";

export default {
	name: "ddlscript-element-emptystate",

	components: {
		'ddlscript-layout-center': CenterLayout
	},

	props: {
		title: { type:String, default:null }
	}
}
</script>