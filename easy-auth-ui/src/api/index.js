import request from '../util/request';
import config from '../config';

export default {
    // 登录
    login: function (_params) {
        return request.post(config.apiMappingUri + '/login', {..._params});
    },
    checkCaptchaOn: function () {
        return request.get(config.apiMappingUri + '/checkCaptchaOn');
    }
}