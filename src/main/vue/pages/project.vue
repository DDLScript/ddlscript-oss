<template>
	<ddlscript-component-pageheader
		v-if="!is.loading && !is.error"
		:heading="project.name"
		:tabs="navigationTabs"
		:breadcrumbs="breadcrumbs"
		/>

	<!-- Loading project details -->
	<div v-if="is.loading" style='padding:4rem 0;text-align:center;'>
		<ddlscript-element-busyspinner />
	</div>

	<!-- failed to load a project -->
	<div v-if="!is.loading && is.error">Unable to load project.</div>

	<!-- project successfully loaded -->
	<router-view v-if="!is.loading && !is.error" :project="project" />
</template>

<script>
import DDLScript from "../api";

import BusySpinnerElement from "../elements/busyspinner.vue";
import PageHeaderComponent from "../components/pageheader.vue";

export default {
	name: 'ddlscript-page-home',

	components: {
		'ddlscript-element-busyspinner': BusySpinnerElement,
		'ddlscript-component-pageheader': PageHeaderComponent,
	},

	data: () => ({
		is: {
			loading: true,
			error: false
		},

		project: null
	}),

	methods: {
		isTabActive(href) {
			return window.location.pathname.startsWith(href);
		}
	},

	computed: {

		breadcrumbs() {
			const items = [];

			items.push({
				label: "Dashboard",
				href: "/"
			});

			return items;
		},

		navigationTabs() {
			const items = [];

			items.push({
				label: "Scripts"
				, href: '/projects/' + this.project.id + '/scripts'
				, active: this.isTabActive('/projects/' + this.project.id + '/scripts') || (window.location.pathname == '/projects/' + this.project.id)
			});

			items.push({
				label: "Merge Requests"
				, href: '/projects/' + this.project.id + '/mergerequests'
				, active: this.isTabActive('/projects/' + this.project.id + '/mergerequests')
			});

			items.push({
				label: "Mainline"
				, href: '/projects/' + this.project.id + '/mainline'
				, active: this.isTabActive('/projects/' + this.project.id + '/mainline')
			});

			if ((this.project.permissions && this.project.permissions.includes("MANAGE_PROJECT_SETTINGS")) || this.$session.systemPermissions.includes("DELETE_PROJECTS")) {
				items.push({
					label: "Settings"
					, href: '/projects/' + this.project.id + '/settings'
				, active: this.isTabActive('/projects/' + this.project.id + '/settings')
				});
			}

			return items;
		}
	},

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