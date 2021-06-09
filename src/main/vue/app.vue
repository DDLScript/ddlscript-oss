<template>
	<ddlscript-element-globalheader />
	<ddlscript-layout-threecolumn>
		<template v-slot:nav>
			<ddlscript-component-panel-projectlist />
		</template>

		<template v-slot:aside>
			<ddlscript-element-panel hue="foreground" title="Your Open Merge Requests">
				<div style='padding:4rem 0;text-align:center;'>
					<ddlscript-element-busyspinner />
				</div>
			</ddlscript-element-panel>
		</template>

		<ddlscript-element-panel hue="foreground" title="Merge Requests Awaiting Your Review">
			<div style='padding:4rem 0;text-align:center;'>
				<ddlscript-element-busyspinner />
			</div>
		</ddlscript-element-panel>
	</ddlscript-layout-threecolumn>
	<div style='padding:2rem 1rem 4rem;opacity:0.666;text-align:center;'>
		<small>Copyright &copy; 2017-{{ new Date().getFullYear() }} DDL Script.</small>
	</div>
</template>

<script>
import GlobalHeaderElement from "./elements/globalheader.vue";
import PanelElement from "./elements/panel.vue";
import BusySpinnerElement from "./elements/busyspinner.vue";

import ThreeColumnLayout from "./layouts/threecolumn.vue";

import ProjectListPanelComponent from "./components/panels/projectlist.vue";

export default {
	name: 'app',

	components: {
		'ddlscript-element-globalheader': GlobalHeaderElement,
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-busyspinner': BusySpinnerElement,

		'ddlscript-layout-threecolumn': ThreeColumnLayout,

		'ddlscript-component-panel-projectlist': ProjectListPanelComponent,
	},

	mounted() {
		console.log("SESSION:", this.$session, this.$session.user);

	},
};
</script>
