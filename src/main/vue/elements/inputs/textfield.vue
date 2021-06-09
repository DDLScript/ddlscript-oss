<template>
	<input class="ddlscript-element-input-textfield" data-hue="foreground" :type="type" :value="value" :placeholder="placeholder" v:on="$listeners" @input="$emit('updatevalue', $event.target.value)" />
</template>

<script>
module.exports = {
	name: "ddlscript-element-input-textfield"

	, props: {
		type: { type:String, default:"text" }
		, value: { type:String, default:"" }
		, placeholder: { type:String, default:null }
	}
}
</script>
