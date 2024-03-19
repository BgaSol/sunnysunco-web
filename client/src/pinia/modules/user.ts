import {defineStore} from 'pinia';
import {onMounted, ref} from 'vue';
import {Service, UserEntity} from "~/generated";
import {authorization} from "~/api/HttpRequest";

export const useUser = defineStore('user', () => {
    const user = ref<UserEntity | undefined>();

    const userToken = ref(localStorage.getItem(authorization));

    const getUser = async () => {
        if (userToken.value) {
            const {data} = await Service.getUserInfo();
            user.value = data;
        }
        return user.value;
    };

    const setToken = (token: string) => {
        userToken.value = token;
        localStorage.setItem(authorization, token);
        void getUser();
    };

    const logout = async (fun?: () => void) => {
        await logOut();
        setToken('');
        user.value = undefined;
        fun && fun();
    };

    onMounted(() => {
        void getUser();
    })

    return {user, userToken, setToken, logout, getUser};
});
const logOut = async (fun?: () => void) => {
    await Service.logout();
    localStorage.setItem(authorization, "");
    fun && fun();
}
