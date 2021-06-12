<template>
  <ddlscript-element-modal v-if="!is.closed">
		<ddlscript-element-panel title="Create New Project" hue="foreground">
			<div style="padding:0 1rem; width:32rem;">
				<!-- field: project title -->
				<ddlscript-element-formfield title="Project Title" for="project-title" >
					<ddlscript-element-input-textfield type='text' id='project-title' :value="new_project.title" maxlength="100" placeholder="... up to 100 characters" @updatevalue="new_project.title = $event" />
				</ddlscript-element-formfield>

				<!-- action buttons -->
				<div style="display:flex; justify-content:flex-end;padding-bottom:1rem;">
					<ddlscript-element-button hue="background" label="Cancel" @click="onCancelClick" />
					<ddlscript-element-button hue="positive" label="Create Project" :disabled="!canSubmit" :busy="is.submitting" @click="onSubmitClick" />
				</div>
			</div>
		</ddlscript-element-panel>
  </ddlscript-element-modal>
</template>

<script>
import ModalElement from "../../elements/modal.vue";
import PanelElement from "../../elements/panel.vue"
import FormFieldElement from "../../elements/formfield.vue";
import TextFieldInputElement from "../../elements/inputs/textfield.vue";
import ButtonElement from "../../elements/button.vue";

export default {
	name: "ddlscript-component-modal-createproject",

	components: {
		'ddlscript-element-modal': ModalElement,
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-formfield': FormFieldElement,
		'ddlscript-element-input-textfield': TextFieldInputElement,
		'ddlscript-element-button': ButtonElement,
	},

	data: () => ({
		is: {
			submitting: false,
			closed: false
		},

		new_project: {
			title: ""
		}
	}),

	computed: {
		canSubmit() {
			return this.new_project.title.trim().length > 3 && this.new_project.title.trim().length <= 100;
		}
	},

	methods: {
		onSubmitClick() {
			if (this.is.closed || !this.canSubmit) return;

			console.log("New Project:", this.new_project);
			this.onCancelClick();
		},

		onCancelClick() {
			this.is.closed = true;
		}
	}
}
</script>