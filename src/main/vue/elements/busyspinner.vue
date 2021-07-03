<template>
	<div class='ddlscript-element-busyspinner' :data-size="this.size"></div>
</template>
<script>
export default {
	name: "ddlscript-element-busyspinner"

	, props: {
		"size": {type:String, default:""}
	}
}
</script>