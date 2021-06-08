// Application Entrypoint.
import { createApp } from 'vue';
import router from './router'
import App from './app.vue';
import Auth from './auth.vue';
import DDLScript from './api';

DDLScript.get("/session")
    .then(() => {
        // create app
        const app = createApp(App);

        // vue-router
        app.use(router);

        // mount the application
        app.mount("#app");
    })
    .catch(() => {
        // not signed in
        // create app
        const app = createApp(Auth);

        // mount the application
        app.mount("#app");
    });
