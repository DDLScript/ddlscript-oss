<template>
	<component :is="href ? 'a' : 'span'" :href="href" class="ddlscript-element-accordionmenu-item">{{ label }}</component>
</template>

<script>
export default {
	name: 'ddlscript-element-accordionmenu-item',

	props: {
		label: { type:String, default:"" },
		href: { type:String, default:null }
	}
}
</script>