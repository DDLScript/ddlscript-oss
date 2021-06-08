<template>
	<fieldset class='ddlscript-component-formfield'>
		<!-- FIELD LABEL -->
		<legend v-if="title.length">
			<label :for="this.for">{{ title }}</label>
		</legend>

		<!-- FIELD CONTENT -->
		<slot></slot>
	</fieldset>
</template>

<script>
export default {

	name: "ddlscript-component-formfield"

	, props: {
		title: { type:String, default: "" }
		, for: { type:String, default: "" }
	}

}
</script>