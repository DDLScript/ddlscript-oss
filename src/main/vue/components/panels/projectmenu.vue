<template>
	<ddlscript-element-panel v-if="hasProject" class='ddlscript-component-panel-projectmenu' hue="foreground" :title="project.name">

		<ddlscript-element-accordionmenu-item label="Scripts" :href="'/project/' + project.id + '/scripts'" :active="true" />
		<ddlscript-element-accordionmenu-item label="Merge Requests" :href="'/project/' + project.id + '/mergerequests'" />
		<ddlscript-element-accordionmenu-item label="Mainline" :href="'/project/' + project.id + '/mainline'" />

		<hr v-if="showSettingsGroup" />

		<ddlscript-element-accordionmenu-item v-if="showSettingsGroup" label="Settings" :href="'/project/' + project.id + '/settings'" />

	</ddlscript-element-panel>
</template>

<script>
import PanelElement from "../../elements/panel.vue";
import AccordionMenuItem from "../../elements/accordionmenu/item.vue";

export default {
	name: 'ddlscript-component-panel-projectmenu',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-accordionmenu-item': AccordionMenuItem,
	},

	props: {
		project: { type:Object, default:null }
	},

	computed: {
		hasProject() {
			return this.project;
		},

		showSettingsGroup() {
			return this.project && this.project.permissions && this.project.permissions.includes("MANAGE_PROJECT_SETTINGS");
		}
	},
};
</script>
