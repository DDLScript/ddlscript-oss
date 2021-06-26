import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '../pages/home.vue';
import ProjectPage from '../pages/project.vue';
import ScriptsProjectPage from '../pages/projects/scripts.vue';
import SettingsProjectPage from '../pages/projects/settings.vue';

const routes = [
	{	path: '/', component: HomePage	},
	{	path: '/projects/:id', component: ProjectPage, children: [
		{	path: '', component: ScriptsProjectPage	},
		{	path: 'scripts', component: ScriptsProjectPage	},
		{	path: 'settings', component: SettingsProjectPage	},
	]	},
];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
});

export default router;