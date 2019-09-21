<template>

    <div class="m-container">
        <div class="m-login">
            <!-- :rules="loginRules"-->
            <div class="m-login__title">
                用户登录
            </div>
            <el-form :model="loginForm" status-icon ref="loginForm">
                <el-form-item prop="userName">
                    <el-input placeholder="请输入用户名" v-model="loginForm.userName">
                        <template slot="prepend"><i class="el-icon-user"/></template>
                    </el-input>
                </el-form-item>

                <el-form-item prop="pwd">
                    <el-input placeholder="请输入密码" type="password" v-model="loginForm.pwd" autocomplete="off">
                        <template slot="prepend"><i class="el-icon-lock"/></template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="captcha">
                    <el-input v-model="loginForm.captcha"
                              placeholder="请输入验证码"></el-input>
                    <el-button type="primary">获取验证码
                    </el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"
                               @click="doLogin('loginForm')">登录
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                loginForm: {
                    userName: '',
                    pwd: '',
                    captcha: ''
                }
            }
        },
        methods: {
            doLogin() {
                alert(111);
            }
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

        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;


        &__title {
            font-weight: bold;
            text-align: center;
        }
    }
</style>