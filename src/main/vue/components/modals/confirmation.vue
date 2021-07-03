<template>
  <ddlscript-element-modal v-if="!is.closed">
		<ddlscript-element-panel :title="title" :hue="hue">
			<div style="padding:0 1rem; width:32rem;">
				<!-- action buttons -->
				<div style="display:flex; justify-content:flex-end;padding-bottom:1rem;">
					<ddlscript-element-button hue="background" label="Cancel" @click="onCancelClick" />
					<ddlscript-element-button hue="positive" label="Confirm" @click="onConfirmClick" />
				</div>
			</div>
		</ddlscript-element-panel>
  </ddlscript-element-modal>
</template>

<script>
import ModalElement from "../../elements/modal.vue";
import PanelElement from "../../elements/panel.vue"
import ButtonElement from "../../elements/button.vue";

export default {
	name: "ddlscript-component-modal-confirmation",

	components: {
		'ddlscript-element-modal': ModalElement,
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-button': ButtonElement,
	},

	props: {
		'title': {type:String, default:"Are You Sure?"},
		'hue': {type:String, default:"foreground"},
	},

	data: () => ({
		is: {
			closed: false,
		},
	}),

	methods: {
		async onConfirmClick(event) {
			this.$emit("confirm", event);
			this.is.closed = true;
		},

		async onCancelClick(event) {
			this.$emit("cancel", event);
			this.is.closed = true;
		}
	},
}
</script>