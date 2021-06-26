<template>
	<div class='ddlscript-component-scriptlistitem'>
		<a class='id-link' :href="'/projects/' + project.id + '/scripts/' + script.id">WS-{{ script.id }}</a>
		<div>
			<a :href="'/projects/' + project.id + '/scripts/' + script.id">{{ script.title }}</a><br />
			<span>{{ script.created_by.name }}</span> &bull; <span>Created {{ formatCreationDate(script.timestamp_created) }} ago</span>
		</div>
	</div>
</template>

<script>
import { formatDistanceToNow } from 'date-fns';

export default {
	name: "ddlscript-component-scriptlistitem",

	props: {
		project: { type:Object, required:true },
		script:  { type:Object, required:true }
	},

	methods: {
		formatCreationDate(date_string) {
			return formatDistanceToNow(
				new Date(date_string)
			)
		}
	}
}
</script>
