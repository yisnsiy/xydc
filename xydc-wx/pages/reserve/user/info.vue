<template>
	<view class="bg-white">
		<view class="padding" style="height: 207.407px;">
			<view class="profile">
				<text class="avatar-text">{{nickname}}</text>
			</view>
		</view>
		
		<view class="cu-list menu">
			<view class="cu-item" @click="userInfo()">
				<view class="content">
					<text class="cuIcon-profile text-grey">
						
					</text>
					<text class="text-grey">个人信息</text>
				</view>
				
			</view>
			<view class="cu-item" @click="addressManage()">
				<view class="content">
					<text class="cuIcon-location text-grey">
						
					</text>
					<text class="text-grey">地址管理</text>
				</view>
				
			</view>
			<view class="cu-item" @click="logout()">
				<view class="content">
					<text class="cuIcon-exit text-grey">
						
					</text>
					<text class="text-grey">退出</text>
				</view>
				
			</view>
		</view>
		<mytabbar :identity="identity" :selected='2'></mytabbar>
	</view>
</template>

<script>
	import mytabbar from "@/components/mytabbar/mytabbar.vue";
	
	export default {
		components: {
			mytabbar
		},
		data() {
			return {
				identity: 1,
				nickname:'',
			};
		},
		onLoad() {
		},
		onShow(){
			try{
				var user = uni.getStorageSync('user');
				this.identity = user.identity;
				this.nickname = user.nickname;
			} catch(e) {
				console.log(e);
			}
		},
		methods: {
			userInfo() {
				console.log("跳转到个人页面");
				uni.navigateTo({
					url: '/pages/reserve/user/userInfo',
					fail(err) {
						console.log(err);
					}
				})
			},
			logout() {
				uni.reLaunch({
					url: '/pages/index/index',
				})
			},
			addressManage() {
				uni.navigateTo({
					url: '/pages/reserve/address/addresss',
				})
			}
		}
	}
	
	
</script>

<style>
	.profile {
		margin: 40rpx 175rpx 40rpx 175rpx;
		background-image: linear-gradient(45deg, #0081ff, #28499d);
		color: #ffffff;
			
		/* bg-gradual-blue */
		width: 300rpx;
		height: 300rpx;
		font-size: 2em;
		display: inline-flex;
		text-align: center;
		
		justify-content: center;
		align-items: center;
		position: relative;
		border-radius: 5000rpx;
	}
</style>
