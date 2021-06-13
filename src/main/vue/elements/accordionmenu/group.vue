<template>
	<ddlscript-element-accordionmenu-item :label="label" @click="toggle" />
	<div v-if="is.expanded" class="ddlscript-element-accordionmenu-group">
		<slot name="default"></slot>
	</div>
</template>

<script>
import AccordionMenuItemElement from "./item.vue";

export default {
	name: 'ddlscript-element-accordionmenu-group',

	components: {
		'ddlscript-element-accordionmenu-item': AccordionMenuItemElement
	},

	props: {
		label: { type:String, default:"" }
	},

	data: () => ({
		is: {
			expanded: false
		}
	}),

	methods: {
		toggle() {
			this.is.expanded = !this.is.expanded;
		}
	}
}
</script>