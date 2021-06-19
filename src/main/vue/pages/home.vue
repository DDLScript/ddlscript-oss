<template>
	<ddlscript-layout-threecolumn>
		<template v-slot:nav>
			<ddlscript-component-panel-projectlist />
		</template>

		<template v-slot:aside>
			<ddlscript-element-panel hue="foreground" title="Your Open Merge Requests">
				<div style='padding:4rem 0;text-align:center;'>
					<ddlscript-element-busyspinner />
				</div>
			</ddlscript-element-panel>
		</template>

		<ddlscript-element-panel hue="foreground" title="Merge Requests Awaiting Your Review">
			<div style='padding:4rem 0;text-align:center;'>
				<ddlscript-element-busyspinner />
			</div>
		</ddlscript-element-panel>
	</ddlscript-layout-threecolumn>
</template>

<script>
import PanelElement from "../elements/panel.vue";
import BusySpinnerElement from "../elements/busyspinner.vue";

import ThreeColumnLayout from "../layouts/threecolumn.vue";

import ProjectListPanelComponent from "../components/panels/projectlist.vue";

export default {
	name: 'ddlscript-page-home',

	components: {
		'ddlscript-element-panel': PanelElement,
		'ddlscript-element-busyspinner': BusySpinnerElement,

		'ddlscript-layout-threecolumn': ThreeColumnLayout,

		'ddlscript-component-panel-projectlist': ProjectListPanelComponent,
	}

}
</script>