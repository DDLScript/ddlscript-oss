<template>
	<ddlscript-element-panel hue="foreground" title="Scripts">
		<template v-slot:actions>
			<select v-model="filter.author" @change="onFilterChanged" style="width:15rem;margin-right:0.25rem;">
				<option value="ALL">Everyone's Requests</option>
				<option value="MINE">My Own Requests</option>
			</select>
			<select v-model="filter.status" @change="onFilterChanged" style="width:10rem;margin-right:0.25rem;">
				<option value="OPEN">Open</option>
				<option value="MERGED">Merged</option>
				<option value="ALL">Any</option>
			</select>
			<ddlscript-element-button v-if="canCreateScripts" label="Create Script" hue="primary" @click="onCreateScriptClicked" />
		</template>

		<div v-if="is.loading" style='padding:4rem 0;text-align:center;'>
			<ddlscript-element-busyspinner />
		</div>

		<ddlscript-element-emptystate v-if="!is.loading && !scripts.length" title="No Scripts Found">
			<span>Refine your filters and try again.</span>
		</ddlscript-element-emptystate>

		<ddlscript-component-scriptlistitem v-for="script in scripts" :key="script.id" :project="project" :script="script" />
	</ddlscript-element-panel>
</template>

<script>
import DDLScript from "../../api";
import { mount } from 'mount-vue-component'

import PanelElement from "../../elements/panel.vue";
import BusySpinnerElement from "../../elements/busyspinner.vue";
import ButtonElement from "../../elements/button.vue";
import EmptyStateElement from "../../elements/emptystate.vue";

import CreateScriptModal from "../../components/modals/createscript.vue";
import ScriptListItemComponent from "../../components/scriptlistitem.vue";

export default {
	name: 'ddlscript-page-project-scripts',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-busyspinner': BusySpinnerElement,
		'ddlscript-element-button': ButtonElement,
		'ddlscript-element-emptystate': EmptyStateElement,
		'ddlscript-component-scriptlistitem': ScriptListItemComponent
	},

	props: {
		project: { type:Object, required:true }
	},

	data: () => ({
		is: {
			loading: true,
			error: false
		},

		filter: {
			author: "ALL",
			status: "OPEN"
		},

		scripts: []
	}),

	computed: {
		canCreateScripts() {
			return this.project.permissions.includes("MODIFY_SCRIPTS");
		}
	},

	methods: {
		onCreateScriptClicked() {
			this.canCreateScripts && mount(Object.assign({}, CreateScriptModal), {
				element: document.querySelector("body"),
				props: {
					project: this.project
				}
			});
		},

		onFilterChanged() {
			console.log(this.filter);
			this.loadScripts();
		},

		async loadScripts() {
			this.is.loading = true;
			try {

				var _scripts = await DDLScript.api.projects.scripts.list(this.project.id);
				this.scripts = _scripts.items;
				this.scripts.sort((a, b) => ('' + b.timestamp_created.toLowerCase()).localeCompare('' + a.timestamp_created.toLowerCase()));
				console.log(_scripts);
			} catch (err) {
				console.log(err);
				this.is.error = true;
			}

			this.is.loading = false;
		}
	},

	async mounted() {
		console.log("SCRIPTS:", this.$route.params);
		console.log("SCRIPTS:", this.project);
		this.loadScripts();
	}
}
</script>