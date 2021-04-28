<template>
	<view>
		<form @submit="updateInfo">
			<view class="cu-form-group margin-top margin-left margin-right">
				<view class="title">密码</view>
				<input name="input" placeholder="密码" v-model="user.password"></input>
			</view>
			<view class="cu-form-group margin-top margin-left margin-right">
				<view class="title">昵称</view>
				<input name="input" placeholder="昵称" v-model="user.nickname"></input>
			</view>
			<view class="cu-form-group margin-top margin-left margin-right">
				<view class="title">电话</view>
				<input name="input" type="number" placeholder="电话" v-model="user.tel"></input>
			</view>
			<!-- <view class="cu-form-group margin-top">
				<view class="title">性别</view>
				<input name="input" placeholder="密码"></input>
			</view> -->
			<view class="cu-form-group margin-top margin-left margin-right">
				<view class="title">性别</view>
				<picker @change="sexChange" :range="gender" :value="user.sex">
					<view class="picker">{{gender[user.sex]}}</view>
				</picker>
			</view>
			
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-default_color margin-tb-sm lg round" form-type="submit">修改信息</button>
			</view>
			
		</form>
		<mytabbar :identity="user.identity" :selected="3"></mytabbar>
	</view>
</template>

<script>
	import mytabbar from '@/components/mytabbar/mytabbar.vue';
	import API from '@/util/api.js';
	
	export default {
		components: {
			mytabbar
		},
		data() {
			return{
				user: {
					userId: '',
					password: '',
					nickname: '',
					identity: '',
					sex: '',
					tel: ''
				},
				gender: ['女', '男']
			}
		},
		onShow() {
			var user = uni.getStorageSync("user");
			this.user = user;
		},
		onLoad() {
		},
		methods: {
			sexChange(e) {
				this.user.sex = e.detail.value
			},
			updateInfo(e) {
				var re = /^[0-9]+.?[0-9]*$/;
				var isVaild = false;
				if (re.test(this.user.tel))  isVaild = true;
				if(isVaild) {
					console.log(this.user)
					let user = this.user;
					uni.request({ //更新后台数据
						method: 'POST',
						data: {
							userId: user.userId,
							password: user.password,
							nickname: user.nickname,
							sex: user.sex,
							identity: user.identity,
							tel: user.tel
						},
						url: API.baseUrl + "/wx/updateUser",
						success(res) {
							if(res.data.code == 200) {
								uni.setStorageSync("user", user); // 更新内存
							}
							uni.showToast({
								title: res.data.message,
								icon: 'none',
							})
						}
					})
					
					
					
				}else {
					uni.showToast({
						title: '电话格式不合法',
						icon: 'none'
					})
				}
				console.log('e :', e);
				console.log('user :', this.user);
			}
		}
	}
</script>

<style lang="scss">
	.bg-default_color {
		background-color: $default-color;
		color: #FFFFFF;
	}
</style>
