import request from '../util/request';

export default {
    // 登录
    login: function (_params) {
        return request.post('/api/login', {..._params});
    },
    checkCaptchaOn: function () {
        return request.get('/api/checkCaptchaOn');
    }


}