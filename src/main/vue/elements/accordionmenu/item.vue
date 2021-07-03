<template>
	<component :is="href ? 'a' : 'span'" :href="href" class="ddlscript-element-accordionmenu-item" :data-active="active" :data-hue="active ? 'primary' : null">{{ label }}</component>
</template>

<script>
export default {
	name: 'ddlscript-element-accordionmenu-item',

	props: {
		label: { type:String, default:"" },
		href: { type:String, default:null },
		active: { type:Boolean, default:false }
	}
}
</script>