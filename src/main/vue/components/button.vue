<template>
	<button class='ddlscript-component-button' :data-hue="hue" :disabled="disabled || busy" v:on="$listeners">
		<ddlscript-component-busyspinner v-if="busy" size="tiny" style="margin:0 1rem;" />
		<span v-if="label && !busy">{{ label }}</span>
		<slot name='post-glyph'></slot>
	</button>
</template>

<script>
import BusySpinnerComponent from "./busyspinner.vue";

export default {
	name: "ddlscript-component-button"

	, components: {
		"ddlscript-component-busyspinner": BusySpinnerComponent
	}

	, props: {
		label: { type:String, default:null }
		, hue: { type:String, default:"neutral" }
		, busy: { type:Boolean, default:false }
		, disabled: { type:Boolean, default:false }
	}
}
</script>
