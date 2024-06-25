import {defineStore} from 'pinia';
import {computed, onMounted, ref, watch} from 'vue';
import {DepartmentEntity, Service, UserEntity} from "~/generated";
import {authorization, getImageUrl, getMyDepartmentLogoUrl} from "~/api/HttpRequest";
import logoJpg from "~/assets/logo.jpg"

export const useUser = defineStore('user', () => {
    // 用户信息
    const user = ref<UserEntity | undefined>();

    // 用户token
    const userToken = ref(localStorage.getItem(authorization));

    // 默认部门
    const defaultDepartment = ref<DepartmentEntity>({});
    // 获取部门信息
    const getDepartment = async () => {
        return Service.getMyDepartment().then((res) => {
            defaultDepartment.value = res.data as DepartmentEntity;
        });
    };

    // 部门logo和名称
    const departmentLogoAndName = computed(() => {
        const result = {
            isShowLogo: false,
            isShowLogoName: false,
            logoUrl: '',
            logoName: ''
        }
        if (user.value?.department?.icon?.id || defaultDepartment.value.icon?.id) {
            result.isShowLogo = true;
            result.logoUrl = getMyDepartmentLogoUrl()
        } else {
            result.isShowLogo = false;
        }
        if (user.value?.department?.name) {
            result.isShowLogoName = true;
            result.logoName = user.value?.department?.name;
        } else if (defaultDepartment.value.name) {
            result.isShowLogoName = true;
            result.logoName = defaultDepartment.value.name
        } else {
            result.isShowLogoName = false;
        }
        return result;
    })

    watch([departmentLogoAndName, user], () => {
        let url;
        if (user.value?.department?.icon?.id) {
            url = getImageUrl(user.value.department.icon.id)
        } else if (departmentLogoAndName.value.isShowLogo) {
            url = getMyDepartmentLogoUrl()
        } else {
            url = logoJpg
        }
        changeFavicon(url)
    })

    // 获取用户信息
    const getUser = async () => {
        if (userToken.value) {
            const {data} = await Service.getUserInfo();
            user.value = data;
        }
        return user.value;
    };

    // 设置token
    const setToken = (token: string) => {
        userToken.value = token;
        localStorage.setItem(authorization, token);
        void getUser();
    };

    // 退出登录
    const logout = async (fun?: () => void) => {
        await logOut();
        setToken('');
        user.value = undefined;
        fun && fun();
    };

    onMounted(() => {
        void getUser();
    })

    return {user, userToken, setToken, logout, getUser, departmentLogoAndName, defaultDepartment, getDepartment};
});

/**
 * 退出登录
 *
 * @param fun 退出登录后的回调
 */
const logOut = async (fun?: () => void) => {
    await Service.logout();
    localStorage.setItem(authorization, "");
    fun && fun();
}

const changeFavicon = (href: string) => {
    const favicon = document.getElementById("favicon") as HTMLLinkElement;
    if (favicon) {
        favicon.href = href;
    } else {
        document.head.innerHTML += `<link id="favicon" rel="shortcut icon" href="${href}">`
    }
}