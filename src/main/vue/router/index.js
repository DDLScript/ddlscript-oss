import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '../pages/home.vue';
import ProjectPage from '../pages/project.vue';

const routes = [
	{	path: '/', component: HomePage	},
	{	path: '/projects/:id', component: ProjectPage, children: []	},
];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
});

export default router;