<template>
	<ddlscript-element-panel class='ddlscript-component-panel-projectlist' hue="foreground" title="Projects">
		<template v-slot:actions>
			<ddlscript-element-button v-if="canCreateProjects" label="Create Project" hue="primary" @click="onCreateProjectClicked" />
		</template>

		<div v-if="is.loading" style='padding:4rem 0;text-align:center;'>
			<ddlscript-element-busyspinner />
		</div>

		<ddlscript-element-accordionmenu-item v-for="item in projects" v-bind:key="item.timestamp_created" :label="item.name" :href="'/projects/' + item.id" />

	</ddlscript-element-panel>
</template>

<script>
import { mount } from 'mount-vue-component'

import PanelElement from "../../elements/panel.vue";
import BusySpinnerElement from "../../elements/busyspinner.vue";
import ButtonElement from "../../elements/button.vue";
import AccordionMenuItem from "../../elements/accordionmenu/item.vue";

import CreateProjectModal from "../../components/modals/createproject.vue";

import DDLScript from "../../api";

export default {
	name: 'ddlscript-component-panel-projectlist',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-busyspinner': BusySpinnerElement,
		'ddlscript-element-button': ButtonElement,
		'ddlscript-element-accordionmenu-item': AccordionMenuItem,
	},

	data: () => ({
		is: {
			loading: true,
			error: false
		},
		projects: []
	}),

	computed: {
		canCreateProjects() {
			return this.$session.systemPermissions.includes("CREATE_PROJECTS");
		}
	},

	methods: {
		onCreateProjectClicked() {
			mount(Object.assign({}, CreateProjectModal), {
				element: document.querySelector("body")
			});
		}
	},

	async mounted() {
		try {
			var response = await DDLScript.api.projects.list();
			this.projects = response.items;
			this.projects.sort((a, b) => ('' + a.name.toLowerCase()).localeCompare('' + b.name.toLowerCase()));
		} catch (err) {
			this.is.error = true;
		} finally {
			this.is.loading = false;
		}
	}
};
</script>
