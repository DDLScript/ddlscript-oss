<template>
  <ddlscript-element-modal v-if="!is.closed">
		<ddlscript-element-panel title="Create New Script" hue="foreground">
			<div v-if="messages.error" data-hue="negative" style="text-align:center; padding: 1rem;">
				{{ messages.error }}
			</div>

			<div style="padding:0 1rem; width:32rem;">
				<!-- field: script title -->
				<ddlscript-element-formfield title="Script Title" for="script-name" >
					<ddlscript-element-input-textfield type='text' id='script-name' :value="new_script.name" maxlength="100" placeholder="... up to 100 characters" @updatevalue="new_script.name = $event" />
				</ddlscript-element-formfield>

				<!-- action buttons -->
				<div style="display:flex; justify-content:flex-end;padding-bottom:1rem;">
					<ddlscript-element-button hue="background" label="Cancel" :disabled="!canCancel" @click="onCancelClick" />
					<ddlscript-element-button hue="positive" label="Create Script" :disabled="!canSubmit" :busy="is.submitting" @click="onSubmitClick" />
				</div>
			</div>
		</ddlscript-element-panel>
  </ddlscript-element-modal>
</template>

<script>
import DDLScript from "../../api";

import ModalElement from "../../elements/modal.vue";
import PanelElement from "../../elements/panel.vue"
import FormFieldElement from "../../elements/formfield.vue";
import TextFieldInputElement from "../../elements/inputs/textfield.vue";
import ButtonElement from "../../elements/button.vue";

export default {
	name: "ddlscript-component-modal-createscript",

	components: {
		'ddlscript-element-modal': ModalElement,
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-formfield': FormFieldElement,
		'ddlscript-element-input-textfield': TextFieldInputElement,
		'ddlscript-element-button': ButtonElement,
	},

	props: {
		'project': { type:Object, required:true }
	},

	data: () => ({
		is: {
			submitting: false,
			closed: false,
			failed: false
		},

		messages: {
			error: ""
		},

		new_script: {
			name: ""
		}
	}),

	computed: {
		canSubmit() {
			return !this.is.submitting && this.new_script.name.trim().length > 3 && this.new_script.name.trim().length <= 100;
		},

		canCancel() {
			return !this.is.submitting;
		}
	},

	methods: {
		async onSubmitClick() {
			if (this.is.closed || !this.canSubmit) return;
			this.is.submitting = true;
			this.is.failed = false;
			this.messages.error = "";

			try {
				const resp = await DDLScript.api.projects.scripts.create(this.project.id, this.new_script);

				// redirect
				window.location = "/project/" + this.project.id + "/scripts/" + resp.id;
			} catch (err) {
				this.is.failed = true;
				this.is.submitting = false;
				this.messages.error = "Unable to create script.";
			}
		},

		onCancelClick() {
			this.is.closed = true;
		}
	}
}
</script>