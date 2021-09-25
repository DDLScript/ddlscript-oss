<template>
	<div class='ddlscript-component-pageheader' data-hue="foreground">

		<div class='tabs'><a v-for="tab in tabs" :key="tab" :href="tab.href" :class="{ 'active': tab.active }" :data-hue="tab.active ? 'background' : null" >{{ tab.label }}</a></div>
	</div>
</template>

<script>

export default {
	name: "ddlscript-component-pageheader",

	props: {
		tabs: { type:Array, required:false, default: () => [] }
	},
}
</script>