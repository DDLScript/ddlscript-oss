<template>
	<ddlscript-element-panel class='ddlscript-component-panel-projectlist' hue="foreground" title="Projects">
		<div v-if="is.loading" style='padding:4rem 0;text-align:center;'>
			<ddlscript-element-busyspinner />
		</div>
	</ddlscript-element-panel>
</template>

<script>
import PanelElement from "../../elements/panel.vue";
import BusySpinnerElement from "../../elements/busyspinner.vue";

export default {
	name: 'ddlscript-component-panel-projectlist',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-busyspinner': BusySpinnerElement,
	},

	data: () => ({
		is: {
			loading: true
		}
	}),

	mounted() {

	}
};
</script>
