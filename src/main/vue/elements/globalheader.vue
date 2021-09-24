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
		<span class='account' style="display:block;padding:0.5rem 1rem;margin-right:1rem;cursor:pointer;">{{ $session.created_by.name }}</span>

		<ddlscript-element-popover target=".ddlscript-element-globalheader > .account" trigger="click" position="bottom-end">
			<ddlscript-element-panel :title="$session.created_by.username" hue="foreground" elevation="above" style="min-width:16rem;">
				<ddlscript-element-accordionmenu-item label="Sign Out" @click="onSignOutClicked" />
			</ddlscript-element-panel>
		</ddlscript-element-popover>
	</header>
</template>

<script>
import LogoSvg from "../svgs/logo.vue";
import PopOverElement from "../elements/popover.vue";
import PanelElement from "../elements/panel.vue"
import AccordionMenuItem from "../elements/accordionmenu/item.vue";


import DDLScript from "../api";

export default {
	name: "ddlscript-element-globalheader",

	components: {
		'svg-logo': LogoSvg,
		'ddlscript-element-popover': PopOverElement,
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-accordionmenu-item': AccordionMenuItem,
	},

	methods: {
		async onSignOutClicked() {
			try {
				await DDLScript.api.session.delete();
				window.location.reload();
			} catch (err) {
				console.log(err);
				alert("Unable to sign out");
			}
		}
	}
}
</script>