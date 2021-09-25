<template>
	<div class='ddlscript-layout-center' :data-size="size"><slot name="default"></slot></div>
</template>

<script>
export default {
	name: "ddlscript-layout-center",

	props: {
		size: { type:String, default:"compact" }
	}
}
</script>