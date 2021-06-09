<template>
	<button class='ddlscript-element-button' :data-hue="hue" :disabled="disabled || busy" v:on="$listeners">
		<ddlscript-element-busyspinner v-if="busy" size="tiny" style="margin:0 1rem;" />
		<span v-if="label && !busy">{{ label }}</span>
		<slot name='post-glyph'></slot>
	</button>
</template>

<script>
import BusySpinnerElement from "./busyspinner.vue";

export default {
	name: "ddlscript-element-button"

	, components: {
		"ddlscript-element-busyspinner": BusySpinnerElement
	}

	, props: {
		label: { type:String, default:null }
		, hue: { type:String, default:"neutral" }
		, busy: { type:Boolean, default:false }
		, disabled: { type:Boolean, default:false }
	}
}
</script>
