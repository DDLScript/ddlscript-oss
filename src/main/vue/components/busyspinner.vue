<template>
	<div class='ddlscript-component-busyspinner' :data-size="this.size"></div>
</template>
<script>
export default {
	name: "ddlscript-component-busyspinner"

	, props: {
		"size": {type:String, default:""}
	}
}
</script>