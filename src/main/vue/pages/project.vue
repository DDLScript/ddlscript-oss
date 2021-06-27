<template>
	<!-- Loading project details -->
	<div v-if="is.loading" style='padding:4rem 0;text-align:center;'>
		<ddlscript-element-busyspinner />
	</div>

	<!-- failed to load a project -->
	<div v-if="!is.loading && is.error">Unable to load project.</div>

	<!-- project successfully loaded -->
	<ddlscript-layout-threecolumn v-if="!is.loading && !is.error">
		<template v-slot:nav>
			<ddlscript-component-panel-projectmenu :project="project" />
		</template>

		<router-view :project="project" />
	</ddlscript-layout-threecolumn>
</template>

<script>
import DDLScript from "../api";

import BusySpinnerElement from "../elements/busyspinner.vue";

import ThreeColumnLayout from "../layouts/threecolumn.vue";

import ProjectMenuPanelComponent from "../components/panels/projectmenu.vue";

export default {
	name: 'ddlscript-page-home',

	components: {
		'ddlscript-element-busyspinner': BusySpinnerElement,

		'ddlscript-layout-threecolumn': ThreeColumnLayout,

		'ddlscript-component-panel-projectmenu': ProjectMenuPanelComponent,
	},

	data: () => ({
		is: {
			loading: true,
			error: false
		},

		project: null
	}),

	async mounted() {
		console.log(this.$route.params);
		try {
			this.project = await DDLScript.api.projects.get(this.$route.params.project);
		} catch (err) {
			console.log(err);
			this.is.error = true;
		}

		this.is.loading = false;
	},

}
</script>