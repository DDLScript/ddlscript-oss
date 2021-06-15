<template>
	<!-- Logo -->
	<svg-logo />

	<!-- Sign In Form -->
	<div class='auth'>
		<ddlscript-element-panel hue="foreground" elevation="raised" title="Sign in to your account.">

			<div v-if="messages.error" data-hue="negative" style="text-align:center; padding: 1rem;">
				{{ messages.error }}
			</div>

			<div style="padding:0 1rem">
				<!-- field: username -->
				<ddlscript-element-formfield title="Username" for="username" >
					<ddlscript-element-input-textfield type='text' id='username' :value="credentials.username" :disabled="is.submitting" placeholder="(eg your.name)" @updatevalue="credentials.username = $event" @onkeyup="onUsernameKeyUp" />
				</ddlscript-element-formfield>

				<!-- field: password -->
				<ddlscript-element-formfield title="Password" for="password" >
					<ddlscript-element-input-textfield type='password' id='password' :value="credentials.password" :disabled="is.submitting" placeholder="(at least 8 characters)" @updatevalue="credentials.password = $event" @onkeyup="onPasswordKeyUp" />
				</ddlscript-element-formfield>

				<!-- button: submit -->
				<div style="display:flex; justify-content:flex-end;padding-bottom:1rem;">
					<ddlscript-element-button hue="positive" label="Sign In" :disabled="!canSubmit" :busy="is.submitting" @click="onSubmitClick" />
				</div>
			</div>
		</ddlscript-element-panel>
	</div>

	<!-- Copyright -->
	<div style='padding:4rem 1rem;opacity:0.666;text-align:center;'>
		<small>Copyright &copy; 2017-{{ new Date().getFullYear() }} DDL Script.</small>
	</div>
</template>

<script>
import PanelElement from "./elements/panel.vue";
import FormFieldElement from "./elements/formfield.vue";
import TextFieldInputElement from "./elements/inputs/textfield.vue";
import ButtonElement from "./elements/button.vue";
import LogoSvg from "./svgs/logo.vue";

import DDLScript from "./api";

export default {
	name: 'auth',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-formfield': FormFieldElement,
		'ddlscript-element-input-textfield': TextFieldInputElement,
		'ddlscript-element-button': ButtonElement,
		'svg-logo': LogoSvg,
	},

	mounted: () => {
		document.querySelector("html")
				.setAttribute("data-hue", "primary");

	},

	computed: {
		canSubmit() {
			return !this.is.submitting && this.credentials.username.length >= 1 && this.credentials.password.length >= 8;
		}
	},

	data: () => ({
		is: {
			submitting: false,
			failed: true
		},
		messages: {
			error: ""
		},
		credentials: {
			username: "",
			password: ""
		}
	}),

	methods: {
		async onSubmitClick() {
			if (!this.canSubmit) return;

			this.is.submitting = true;
			this.messages.error = "";

			try {
				// start a new session
				await DDLScript.api.session.post(this.credentials);

				// reload the page
				window.location.reload();
			} catch (err) {
				// faild to create a new session
				this.is.submitting = false;
				this.messages.error = "Failed To Sign In";
			}
		},

		async onUsernameKeyUp(event) {
			(13 == event.keyCode) && document.querySelector("#password").focus();
		},

		async onPasswordKeyUp(event) {
			(13 == event.keyCode) && this.onSubmitClick();
		}
	}
};
</script>

<style lang="scss" scoped>
	.auth {
		max-width: 36rem;
		margin: 0 auto;
		padding: 2rem;
	}

	svg {
		height: 3rem;
		display: block;
		margin: 6rem auto 0rem;
	}
</style>