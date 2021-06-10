<template>
	<ddlscript-element-panel class='ddlscript-component-panel-projectlist' hue="foreground" title="Projects">
		<div v-if="is.loading" style='padding:4rem 0;text-align:center;'>
			<ddlscript-element-busyspinner />
		</div>

		<ul>
			<li v-for="item in projects" v-bind:key="item.timestamp_created">
				<span>{{ item.name }}</span>
			</li>
		</ul>
	</ddlscript-element-panel>
</template>

<script>
import PanelElement from "../../elements/panel.vue";
import BusySpinnerElement from "../../elements/busyspinner.vue";

import DDLScript from "../../api";

export default {
	name: 'ddlscript-component-panel-projectlist',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-busyspinner': BusySpinnerElement,
	},

	data: () => ({
		is: {
			loading: true,
			error: false
		},
		projects: []
	}),

	async mounted() {
		try {
			var response = await DDLScript.api.projects.list();
			this.projects = response.items;
		} catch (err) {
			this.is.error = true;
		} finally {
			this.is.loading = false;
		}
	}
};
</script>
