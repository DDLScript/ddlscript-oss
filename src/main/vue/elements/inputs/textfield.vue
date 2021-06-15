<template>
	<input class="ddlscript-element-input-textfield"
			data-hue="foreground"
			:type="type"
			:value="value"
			:placeholder="placeholder"
			:maxlength="maxlength"
			:disabled="disabled ? 'disabled' : null"
			v:on="$listeners"
			@keyup="$emit('onkeyup', $event)"
			@input="$emit('updatevalue', $event.target.value)" />
</template>

<script>
module.exports = {
	name: "ddlscript-element-input-textfield"

	, props: {
		type: { type:String, default:"text" }
		, value: { type:String, default:"" }
		, placeholder: { type:String, default:null }
		, maxlength: { type:String, default:null }
		, disabled: { type:Boolean, default:false }
	}
}
</script>
