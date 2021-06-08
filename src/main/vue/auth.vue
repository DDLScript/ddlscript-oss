<template>
	<!-- Logo -->
	<svg-logo />

	<!-- Loading -->
	<div v-if="is.loading" style="display:flex;justify-content:center;margin:4rem 2rem;">
		<ddlscript-component-busyspinner />
	</div>

	<!-- Sign In Form -->
	<div class='auth' v-if="!is.loading">
		<ddlscript-component-panel hue="foreground" title="Sign in to your account.">
			<div style="padding:0 1rem">
				<!-- field: username -->
				<ddlscript-component-formfield title="Username" for="username" >
					<ddlscript-component-input-textfield type='text' id='username' :value="username" placeholder="(eg your.name)" @updatevalue="username = $event" />
				</ddlscript-component-formfield>

				<!-- field: password -->
				<ddlscript-component-formfield title="Password" for="password" >
					<ddlscript-component-input-textfield type='password' id='password' :value="password" placeholder="(at least 8 characters)" @updatevalue="password = $event" />
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
import BusySpinnerComponent from "./components/busyspinner.vue";
import LogoSvg from "./svgs/logo.vue";

import DDLScript from "./api";

export default {
	name: 'auth',

	components: {
		'ddlscript-component-panel': PanelComponent,
		'ddlscript-component-formfield': FormFieldComponent,
		'ddlscript-component-input-textfield': TextFieldInputComponent,
		'ddlscript-component-button': ButtonComponent,
		"ddlscript-component-busyspinner": BusySpinnerComponent,
		'svg-logo': LogoSvg,
	},

	mounted: () => {
		console.log("MOUNTED");

		document.querySelector("html").setAttribute("data-hue", "primary");

	},

	computed: {
		canSubmit() {
			return !this.is.submitting && this.username.length >= 1 && this.password.length >= 8;
		}
	},

	data: () => ({
		is: {
			loading: false,
			submitting: false
		},
		username: "",
		password: ""
	}),

	methods: {
		async onSubmitClick() {
			if (!this.canSubmit) return;

			this.is.submitting = true;
			console.log(this.username + " " + this.password);

			try {
				var response = await DDLScript.api.session.post({
					username: this.username
					, password: this.password
				});

				console.log(response);
			} catch (err) {
				console.error(err);

				this.is.submitting = false;
			}
		},

		reloadPage() {
			window.location.reload();
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