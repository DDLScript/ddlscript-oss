<template>
	<ddlscript-element-globalheader />
	<router-view />
	<footer style='padding:2rem 1rem 4rem;opacity:0.666;text-align:center;'>
		<small>Copyright &copy; 2017-{{ new Date().getFullYear() }} DDL Script.<br />DDL Script is open source and can be forked on <a href='https://github.com/ddlscript/ddlscript-oss' target='_blank' style='text-decoration:underline;'>GitHub</a></small>
	</footer>
</template>

<script>
import GlobalHeaderElement from "./elements/globalheader.vue";

export default {
	name: 'app',

	components: {
		'ddlscript-element-globalheader': GlobalHeaderElement,
	}
};
</script>
