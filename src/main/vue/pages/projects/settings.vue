<template>
	<ddlscript-layout-center size="normal" style="padding-top:2rem;">
		<ddlscript-element-panel v-if="canEditSettings" hue="foreground" title="Settings">
			<template v-slot:actions>
				<ddlscript-element-button label="Save Changes" hue="positive" :disabled="!canSubmit" @click="onSaveClicked" />
			</template>

			<div v-if="messages.error" data-hue="negative" style="text-align:center; padding: 1rem;">
				{{ messages.error }}
			</div>

			<div style="padding:0 1rem 1rem">
				<!-- field: project name -->
				<ddlscript-element-formfield title="Project Name" for="project_name" >
					<ddlscript-element-input-textfield type='text' id='project_name' :value="updated_project.name" :disabled="is.submitting" maxlength="100" placeholder="... up to 100 characters ..." @updatevalue="updated_project.name = $event" @onkeyup="onProjectTitleKeyUp" />
				</ddlscript-element-formfield>
			</div>

		</ddlscript-element-panel>

		<ddlscript-element-panel v-if="canDelete" hue="foreground" title="Delete Project" style="margin-top:1rem;">
			<template v-slot:actions>
				<ddlscript-element-button label="Delete Project" hue="negative" :disabled="is.deleting" @click="onDeleteClicked" />
			</template>

			<p data-hue="negative" style="padding:1rem">This action will permanently delete the project and all it's related data. This action is permanent cannot be reveresed.</p>
		</ddlscript-element-panel>
	</ddlscript-layout-center>
</template>

<script>
import DDLScript from "../../api";

import CenterLayout from "../../layouts/center.vue";

import PanelElement from "../../elements/panel.vue";
import ButtonElement from "../../elements/button.vue";
import FormFieldElement from "../../elements/formfield.vue";
import TextFieldInputElement from "../../elements/inputs/textfield.vue";

export default {
	name: 'ddlscript-page-project-settings',

	components: {
		'ddlscript-layout-center': CenterLayout,
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-button': ButtonElement,
		'ddlscript-element-formfield': FormFieldElement,
		'ddlscript-element-input-textfield': TextFieldInputElement,
	},

	props: {
		project: { type:Object, required:true }
	},

	data: () => ({
		is: {
			submitting: false,
			deleting: false
		},

		messages: {
			error: null
		},

		updated_project: {
			name: "",
		},
	}),

	computed: {
		canSubmit() {
			return !this.is.submitting && this.updated_project.name.length >= 1 && this.updated_project.name.length <= 100;
		},

		canDelete() {
			return this.$session.systemPermissions.includes("DELETE_PROJECTS");
		},

		canEditSettings() {
			return this.project.permissions && this.project.permissions.includes("MANAGE_PROJECT_SETTINGS");
		}
	},

	methods: {
		async onSaveClicked() {
			if (!this.canSubmit) return;
			this.messages.error = null;
			this.is.submitting = true;

			try {
				await DDLScript.api.projects.update(this.project.id, this.updated_project);
				window.location.reload();
			} catch (err) {
				this.is.submitting = false;
				this.messages.error = "Unable to save project settings.";
			}
		},

		async onProjectTitleKeyUp(event) {
			(13 == event.keyCode) && this.onSaveClicked();
		},

		async onDeleteClicked() {
			this.is.deleting = true;
			try {
				await DDLScript.api.projects.delete(this.project.id);
				window.location = "/";
			} catch {
				this.is.deleting = false;
			}
		},
	},

	mounted() {
		this.updated_project.name = this.project.name;
	}
}
</script>