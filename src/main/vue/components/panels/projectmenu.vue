<template>
	<ddlscript-element-panel v-if="hasProject" class='ddlscript-component-panel-projectmenu' hue="foreground" :title="project.name">

		<ddlscript-element-accordionmenu-item label="Scripts" :href="'/projects/' + project.id + '/scripts'" :active="true" />
		<ddlscript-element-accordionmenu-item label="Merge Requests" :href="'/projects/' + project.id + '/mergerequests'" />
		<ddlscript-element-accordionmenu-item label="Mainline" :href="'/projects/' + project.id + '/mainline'" />

		<hr v-if="showSettingsGroup" />

		<ddlscript-element-accordionmenu-group label="Settings">
			<ddlscript-element-accordionmenu-item v-if="showSettingsGroup" label="Project Details" :href="'/projects/' + project.id + '/settings'" />
			<ddlscript-element-accordionmenu-item v-if="showSettingsGroup" label="Script Templates" :href="'/projects/' + project.id + '/templates'" />
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
			return this.project && this.project.permissions && this.project.permissions.includes("MANAGE_PROJECT_SETTINGS");
		}
	},
};
</script>
