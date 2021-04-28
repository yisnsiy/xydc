<template>
	<view>
		<form>
			<view class="cu-form-group margin-top margin-left margin-right">
				<view class="title">姓名</view>
				<input name="input" placeholder="姓名" v-model="address.realName"></input>
			</view>
			<view class="cu-form-group margin-top margin-left margin-right">
				<view class="title">电话</view>
				<input name="input" type="number" placeholder="电话" v-model="address.tel"></input>
			</view>
			<view class="cu-form-group margin-top margin-left margin-right">
				<view class="title">地址</view>
				<input name="input"  placeholder="地址" v-model="address.content"></input>
			</view>
			
			<view class="padding flex flex-direction">
				<button class="cu-btn bg-default_color margin-tb-sm lg round" @click="addAddress()">新增地址</button>
			</view>
			
		</form>
	</view>
</template>

<script>
	import API from '@/util/api.js';
	
	export default {
		data() {
			return{
				address: {
					userId: '',
					realName: '',
					tel: '',
					content: '',
				},
			}
		},
		onShow() {
			var user = uni.getStorageSync("user");
			this.address.userId = user.userId;
		},
		onLoad() {
		},
		methods: {
			addAddress() {
				var re = /^[0-9]*$/;
				var isVaild = false;
				console.log('new address is:', this.address);
				if (re.test(this.address.tel)) isVaild = true;
				if(isVaild || this.address.content  == null || this.address.tel == null || this.address.realName == null) {
					let address = this.address;
					uni.request({ //更新后台数据
						method: 'POST',
						data: {
							userId: address.userId,
							realName: address.realName,
							tel: address.tel,
							content: address.content,
						},
						url: API.baseUrl + "/address/add",
						success(res) {
							uni.showToast({
								title: res.data.message,
								icon: 'none',
							})
						}
					})
				}else {
					uni.showToast({
						title: '输入数据不合法',
						icon: 'none'
					})
				}
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
