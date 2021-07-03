<template>
	<div class='ddlscript-layout-threecolumn'>
		<div class='nav'><slot name='nav'></slot></div>
		<div class='main'>
			<div class='default'><slot name='default'></slot></div>
			<div class='aside'><slot name='aside'></slot></div>
		</div>
	</div>
</template>

<script>
export default {
	name: "ddlscript-layout-threecolumn",


}
</script>