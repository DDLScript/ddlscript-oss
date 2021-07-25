<template>
	<ddlscript-element-panel class='ddlscript-component-panel-projectlist' hue="foreground" title="Script Templates">
		<template v-slot:actions>
			<ddlscript-element-button label="Save Changes" hue="positive" :disabled="!canSubmit" @click="onSaveClicked" />
		</template>

		<p style="padding:1rem;">Script templates allow you to add predefined snippets of code around each script and/or statement of a script within your project. Once a script is merged into the mainline a snapshot of the template is recorded against the merger to ensure no breaking changes are introduced to past scripts when the templates get updated.</p>

		<div hue="foreground" title="Before Entire Script" style="margin-top:1rem;">
			<p style="padding:1rem;"><strong>Before Entire Script</strong></p>
			<ace-editor
					v-model:value="updated_templates.before_all"
					@init="editorInit"
					lang="sql"
					theme="cobalt"
					:minLines="4"
					:maxLines="999999"
					style="height:10rem;padding-top:1rem;padding-bottom:1rem;" />
		</div>

		<div hue="foreground" title="Before Each Statement" style="margin-top:1rem;">
			<p style="padding:1rem;"><strong>Before Each Statement</strong></p>
			<ace-editor
					v-model:value="updated_templates.before_each"
					@init="editorInit"
					lang="sql"
					theme="cobalt"
					:minLines="4"
					:maxLines="999999"
					style="height:10rem;padding-top:1rem;padding-bottom:1rem;" />
		</div>

		<div hue="foreground" title="After Each Statement" style="margin-top:1rem;">
			<p style="padding:1rem;"><strong>After Each Statement</strong></p>
			<ace-editor
					v-model:value="updated_templates.after_each"
					@init="editorInit"
					lang="sql"
					theme="cobalt"
					:minLines="4"
					:maxLines="999999"
					style="height:10rem;padding-top:1rem;padding-bottom:1rem;" />
		</div>

		<div hue="foreground" title="After Entire Script" style="margin-top:1rem;">
			<p style="padding:1rem;"><strong>After Entire Script</strong></p>
			<ace-editor
					v-model:value="updated_templates.after_all"
					@init="editorInit"
					lang="sql"
					theme="cobalt"
					:minLines="4"
					:maxLines="999999"
					style="height:10rem;padding-top:1rem;padding-bottom:1rem;" />
		</div>

	</ddlscript-element-panel>
</template>

<script>
import DDLScript from "../../api";

import PanelElement from "../../elements/panel.vue";
import ButtonElement from "../../elements/button.vue";

import { VAceEditor } from 'vue3-ace-editor';
import 'ace-builds/src-noconflict/mode-sql';
import 'ace-builds/src-noconflict/theme-cobalt';

export default {
	name: 'ddlscript-page-project-settings',

	components: {
		'ace-editor': VAceEditor,
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-button': ButtonElement,
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

		updated_templates: {
			before_all: "",
			before_each: "",
			after_each: "",
			after_all: "",
		},
	}),

	computed: {
		canSubmit() {
			return !this.is.submitting;
		},
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

		async editorInit() {
			console.log("EDITOR_INIT")
		},
	},

	mounted() {
		this.project.templates.forEach(template => {
			template.applied_to_each_statement && template.applied_after && (this.updated_templates.after_each = template.query);
			template.applied_to_each_statement && !template.applied_after && (this.updated_templates.before_each = template.query);
			!template.applied_to_each_statement && !template.applied_after && (this.updated_templates.before_all = template.query);
			!template.applied_to_each_statement && template.applied_after && (this.updated_templates.after_all = template.query);
		});
	}
}
</script>