<template>
	<ddlscript-element-panel v-if="hasProject" class='ddlscript-component-panel-projectmenu' hue="foreground" :title="project.name">

		<ddlscript-element-accordionmenu-item label="Scripts" :href="'/projects/' + project.id + '/scripts'" :active="true" />
		<ddlscript-element-accordionmenu-item label="Merge Requests" :href="'/projects/' + project.id + '/mergerequests'" />
		<ddlscript-element-accordionmenu-item label="Mainline" :href="'/projects/' + project.id + '/mainline'" />

		<hr v-if="showSettingsGroup" />

		<ddlscript-element-accordionmenu-group label="Settings" v-if="showSettingsGroup">
			<ddlscript-element-accordionmenu-item v-for="item in settingsItems" :key="item.label" :label="item.label" :href="item.href" />
		</ddlscript-element-accordionmenu-group>

	</ddlscript-element-panel>
</template>

<script>
import PanelElement from "../../elements/panel.vue";
import AccordionMenuItem from "../../elements/accordionmenu/item.vue";
import AccordionMenuGroup from "../../elements/accordionmenu/group.vue";

export default {
	name: 'ddlscript-component-panel-projectmenu',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-accordionmenu-item': AccordionMenuItem,
		'ddlscript-element-accordionmenu-group': AccordionMenuGroup,
	},

	props: {
		project: { type:Object, default:null }
	},

	computed: {
		hasProject() {
			return this.project;
		},

		showSettingsGroup() {
			return this.project && this.settingsItems.length > 0;
		},

		settingsItems() {
			var items = [];

			if ((this.project.permissions && this.project.permissions.includes("MANAGE_PROJECT_SETTINGS")) || this.$session.systemPermissions.includes("DELETE_PROJECTS")) {
				items.push({
					"label": "Project Details"
					, "href": '/projects/' + this.project.id + '/settings'
				});
			}

			if ((this.project.permissions && this.project.permissions.includes("MANAGE_PROJECT_SETTINGS"))) {
				items.push({
					"label": "Script Templates"
					, "href": '/projects/' + this.project.id + '/templates'
				});
			}

			return items;
		}
	},
};
</script>
