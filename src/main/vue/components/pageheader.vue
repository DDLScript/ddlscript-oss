<template>
	<div class='ddlscript-component-pageheader' data-hue="foreground">
		<div class='breadcrumbs'><a v-for="breadcrumb in breadcrumbs" :key="breadcrumb" :href="breadcrumb.href" >{{ breadcrumb.label }}</a></div>
		<h1 v-if="heading">{{ heading }}</h1>
		<div class='tabs'><a v-for="tab in tabs" :key="tab" :href="tab.href" :class="{ 'active': tab.active }" :data-hue="tab.active ? 'background' : null" >{{ tab.label }}</a></div>
	</div>
</template>

<script>

export default {
	name: "ddlscript-component-pageheader",

	props: {
		tabs: { type:Array, required:false, default: () => [] },
		breadcrumbs: { type:Array, required:false, default: () => [] },
		heading: { type:String, required:false }
	},
}
</script>