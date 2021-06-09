// Application Entrypoint.
import { createApp } from 'vue';
import router from './router'
import App from './app.vue';
import Auth from './auth.vue';
import DDLScript from './api';

DDLScript.api.session.get()
    .then(json => {
        // create app
        const app = createApp(App);

        // store session data
        app.config.globalProperties.$session = json;

        // vue-router
        app.use(router);

        // mount the application
        app.mount("#app");
    })
    .catch(err => {
        console.log(err);
        // not signed in
        // create app
        const app = createApp(Auth);

        // mount the application
        app.mount("#app");
    });
