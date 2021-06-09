<template>
	<!-- Logo -->
	<svg-logo />

	<!-- Sign In Form -->
	<div class='auth'>
		<ddlscript-component-panel hue="foreground" title="Sign in to your account.">

			<div v-if="messages.error" data-hue="negative" style="text-align:center; padding: 1rem;">
				{{ messages.error }}
			</div>

			<div style="padding:0 1rem">
				<!-- field: username -->
				<ddlscript-component-formfield title="Username" for="username" >
					<ddlscript-component-input-textfield type='text' id='username' :value="credentials.username" placeholder="(eg your.name)" @updatevalue="credentials.username = $event" />
				</ddlscript-component-formfield>

				<!-- field: password -->
				<ddlscript-component-formfield title="Password" for="password" >
					<ddlscript-component-input-textfield type='password' id='password' :value="credentials.password" placeholder="(at least 8 characters)" @updatevalue="credentials.password = $event" />
				</ddlscript-component-formfield>

				<!-- button: submit -->
				<div style="display:flex; justify-content:flex-end;margin-bottom:1rem;">
					<ddlscript-component-button hue="positive" label="Sign In" :disabled="!canSubmit" :busy="is.submitting" @click="onSubmitClick" />
				</div>
			</div>
		</ddlscript-component-panel>
	</div>

	<!-- Copyright -->
	<div style='padding:4rem 1rem;opacity:0.666;text-align:center;'>
		<small>Copyright &copy; 2017-{{ new Date().getFullYear() }} DDL Script.</small>
	</div>
</template>

<script>
import PanelComponent from "./components/panel.vue";
import FormFieldComponent from "./components/formfield.vue";
import TextFieldInputComponent from "./components/inputs/textfield.vue";
import ButtonComponent from "./components/button.vue";
import LogoSvg from "./svgs/logo.vue";

import DDLScript from "./api";

export default {
	name: 'auth',

	components: {
		'ddlscript-component-panel': PanelComponent,
		'ddlscript-component-formfield': FormFieldComponent,
		'ddlscript-component-input-textfield': TextFieldInputComponent,
		'ddlscript-component-button': ButtonComponent,
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
		}
	}
};
</script>

<style lang="scss" scoped>
	.auth {
		max-width: 32rem;
		margin: 0 auto;
		padding: 2rem;
	}

	svg {
		height: 3rem;
		display: block;
		margin: 6rem auto 0rem;
	}
</style>