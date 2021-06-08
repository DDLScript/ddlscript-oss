<template>
	<div
		class="ddlscript-component-panel"
		:data-hue="hue"
		>
		<div v-if="isTitleVisible" class='ddlscript-component-panel-title'>
			<span>{{ title }}</span>
		</div>
		<slot name="default"></slot>
	</div>
</template>

<script>
export default {
	name: 'ddlscript-component-panel',

	props: {
		hue: { type:String, default:null },
		title: { type:String, default:null }
	},

	computed: {
		isTitleVisible() {
			return this.title ? true : false;
		}
	}
};
</script>
