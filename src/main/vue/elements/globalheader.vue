<template>
	<header class='ddlscript-element-globalheader' data-hue="primary">
		<!-- Logo -->
		<a class='logo' href="/">
			<svg-logo />
		</a>

		<!-- Links -->
		<div class='menu'>

		</div>

		<!-- User -->
		<div class='account'>
			{{ $session.user.username }}
		</div>
	</header>
</template>

<script>
import LogoSvg from "../svgs/logo.vue";

export default {
	name: "ddlscript-element-globalheader",

	components: {
		'svg-logo': LogoSvg,
	},

}
</script>