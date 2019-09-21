<template>

    <div class="m-container">
        <div class="m-login">
            <div class="m-login__title">
                用户登录
            </div>
            <el-form :model="loginForm" :rules="loginRules" ref="loginForm">
                <el-form-item prop="userName">
                    <el-input placeholder="请输入用户名" v-model="loginForm.userName">
                        <template slot="prepend"><i class="el-icon-user"/></template>
                    </el-input>
                </el-form-item>

                <el-form-item prop="pwd">
                    <el-input placeholder="请输入密码" type="password" v-model="loginForm.pwd" autocomplete="off"
                              show-password="true">
                        <template slot="prepend"><i class="el-icon-lock"/></template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="captcha" v-show="showCaptcha">
                    <el-input v-model="loginForm.captcha" class="m-login__catpcha-input"
                              placeholder="请输入验证码">
                        <template slot="suffix">
                            <img class="m-login__captcha-img" :src="computedCaptchaUri" @click="refreshCaptcha"
                                 alt="刷新验证码"/>
                        </template>
                    </el-input>

                </el-form-item>
                <el-form-item>
                    <el-button type="primary" class="m-login__btn" :loading="loginLoading"
                               @click="doLogin()">登录
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import api from '../api';


    export default {
        name: "Login",
        data() {

            let captchaValidator = (rule, value, callback) => {
                if (this.showCaptcha && !value) {
                    return callback(new Error('验证码不允许为空!'));
                }
                callback();
            }

            return {
                showCaptcha: false,
                captchaUri: '',
                loginLoading: false,
                loginForm: {
                    userName: '',
                    pwd: '',
                    captcha: ''
                },
                loginRules: {
                    userName: {required: true, message: '用户名不允许为空!'},
                    pwd: {required: true, message: '密码不允许为空!'},
                    captcha: [
                        {validator: captchaValidator}
                    ]
                }
            }
        },
        methods: {
            doLogin() {
                this.loginLoading = true;
                this.$refs["loginForm"].validate((valid) => {
                    if (!valid) {
                        this.loginLoading = false;

                        return false;
                    }
                    api.login({...this.loginForm}).then((data) => {
                        this.loginLoading = false;

                        if (data.code != 200) {
                            this.$message.error(data.msg);

                            this.showCaptcha = data.showCaptcha;
                            if (this.captchaUri) {
                                this.captchaUri = "";
                            }
                            this.captchaUri = data.captchaUri;
                        } else {
                            window.location.href = data.successRedirectUri;
                        }
                    });
                });
            },
            /**
             * 刷新验证码
             */
            refreshCaptcha(e) {
                let el = e.target;
                el.src = this.generateCaptchaUri();
            },
            generateCaptchaUri() {
                return "/api" + this.captchaUri + "?t=" + (+new Date());
            }

        },
        computed: {
            computedCaptchaUri: function () {
                return this.generateCaptchaUri();
            }
        },
        mounted() {
            // 检测是否需要验证码
            api.checkCaptchaOn().then((data) => {
                this.showCaptcha = data.showCaptcha;
                this.captchaUri = data.captchaUri;
            });
        }
    }

</script>

<style scoped lang="scss">

    .m-container {
        position: relative;
        width: 100%;
        height: 100vh;
    }

    .m-login {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);

        width: 350px;
        padding: 50px;

        border-radius: 10px;
        border: 1px solid #ccc;

        &__title {
            padding-bottom: 40px;

            font-weight: bold;
            font-size: 25px;
            text-align: center;
        }

        &__catpcha-input {

        }

        &__captcha-img {
            width: 120px;
            height: 30px;
            padding: 5px 0;

            cursor: pointer;
        }

        &__btn {
            width: 100%;
        }
    }
</style>