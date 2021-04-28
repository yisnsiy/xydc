<template>
	<view class="login-container">
		<view class="title">校园点餐</view>
		<view class="login-box">
			<label>用户名</label>
			<input v-model="userId" placeholder="请输入用户名"/>
			<label>密 码</label>
			<input v-model="password" password="true" placeholder="请输入密码"/>
			<button class="login-btn" @click="login()">立即登录</button>
		</view>
	</view>
</template>

<script>
	import API from '@/util/api.js';
	
	export default {
		components: {
			
		},
		data() {
			return {
				userId: '',
				password: '',
			}
		},
		methods: {
			login() {
				let userId = this.userId;
				uni.request({
					url: API.baseUrl + '/vertifyUser',
					method: 'POST',
					data: {
						userId: this.userId,
						password: this.password,
						location: 'wx',
					},
					success: res=>{
						uni.showToast({
							title: res.data.message,
							duration: 0.5 * 1000,
							position: 'center',
							icon: 'none'
						})
						if(res.data.code == 200) {
							//登录成功
							// uni.setStorageSync('identity', res.data.data);
							uni.request({
								url: API.baseUrl + "/wx/userDetail",
								data: {
									userId: userId
								},
								success: function (res){
									console.log("登录的人的信息: ",res.data.data);
									uni.setStorageSync('user', res.data.data);
								}
							})
							if(res.data.data == 1) {
								//用户
								uni.switchTab({
									url: "/pages/reserve/user/home",
									fail(err) {
										console.log("跳转失败", err);
									}
								})
							}else if(res.data.data == 2) {
								// 骑手
								uni.switchTab({
									url: "/pages/reserve/carrier/home",
									fail(err) {
										console.log("跳转失败", err);
									}
								})
							}
						}
						console.log(res)
					}
				});
				this.reset();
			},
			reset() {
				this.userId = "";
				this.password = "";
			}
		}
	}
</script>

<style>
	
/* 	page{
	  height: 100%;    /* 使用page的height可以使页面占全屏 */ 
	  /* background-color: #fafafa;
	 } */
	.login-container{
	  padding: 0 10%;
	  height: 100%;
	}
	.title{
	  font-size: large;
	  text-align: center;
	  padding-top: 10%;
	  font-weight: bold;
	}
	.login-box{
	  margin-top: 10%;
	  padding: 10% 5%;
	  background-color: white;
	  border-radius: 10px;
	  box-shadow: 0 4px 4px #888888;
	}
	.login-box>input{
	  margin: 5% 0 8% 0;
	  border-bottom: 1rpx solid lightgray;
	}
	.login-btn{
	  width: 100%!important;
	  background-color: #55aaff;
	  color: white;
	  font-weight: normal;
	}
	 
	.three-line{
	  margin: 8% 0;
	  text-align: center;
	  font-size: 12px;
	  color: gray;
	}
</style>
