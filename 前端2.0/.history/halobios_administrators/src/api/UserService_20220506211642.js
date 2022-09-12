import request from './axios' // 使用实例

export default {
    // 分类
    // login(user_tel, user_pwd) {
    //     return request({
    //         url: `user/login`,
    //         method: 'GET',
    //         params: {
    //             user_tel: user_tel,
    //             user_pwd: user_pwd,
    //         }
    //     })
    // },
    // register(user_name, user_tel, user_pwd) {
    //     return request({
    //         url: `user/register`,
    //         method: 'GET',
    //         params: {
    //             user_name: user_name,
    //             user_tel: user_tel,
    //             user_pwd: user_pwd,
    //         }
    //     })
    // },
    // info(user_id) {
    //     return request({
    //         url: `user/info`,
    //         method: 'GET',
    //         params: {
    //             user_id: user_id,
    //         }
    //     })
    // },
    // updateuser(user_name, user_pwd, user_sex, user_id) {
    //     return request({
    //         url: `user/updateuser`,
    //         method: 'GET',
    //         params: {
    //             user_name: user_name,
    //             user_pwd: user_pwd,
    //             user_sex: user_sex,
    //             user_id: user_id
    //         }
    //     })
    // },
    infoList() {
        return request({
            url: `user/infoList`,
            method: 'GET',
        })
    },
    deleteUser(user_id) {
        return request({
            url: `user/deleteUser`,
            method: 'GET',
            params: {
                user_id:user_id
            }
        })
    },
}