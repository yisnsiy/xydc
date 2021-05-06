<template>
	<view class="align-center">
		<view class="padding-xs margin-xs" v-for="item in list " :key="item.addressId" :class="item.def == true ? 'bg-default-color' : 'bg-white'">
			<view class="align-center flex justify-between">
				<view class="radius padding-xs" @click="setDefault(item.addressId)"> <!-- 左方块 -->
					<view class="flex solid-bottom padding-xs align-start align-center"> <!-- 上面那一层 -->
						<view class="radius fl padding-xs">{{item.realName}}</view> <!-- 姓名 -->
						<view class="radius fl padding-xs">{{item.tel}}</view> <!-- 电话 -->
					</view>
					<view class="flex solid-bottom padding-sm align-end text-lg text-black">{{item.content}}</view> <!-- 下面那一层,用户放地址 -->
				</view>
				<view class="radius padding-xs margin-lr-xs text-xl" @click="deleteAddress(item.addressId)">
					<text class="text-red cuIcon-delete"></text>
				</view>
			</view>
		</view>
		
		<!-- <view class="bg-white padding-xs margin-xs">
			<view class="align-center flex justify-between">
				<view class="radius padding-xs">
					<view class="flex solid-bottom padding-xs align-start align-center">
						<view class="radius fl padding-xs">易升</view>
						<view class="radius fl padding-xs">15074768987</view>
					</view>
					<view class="flex solid-bottom padding-sm align-end text-lg text-black">致远居2447</view> 
				</view>
				<view class="radius padding-xs margin-lr-xs text-xl">
					<text class="text-red cuIcon-delete"></text>
				</view>
				
			</view>
		</view> -->

		<view class="padding flex flex-direction" style="position: fixed; bottom: 5rpx; width: 750rpx;" @click="addAddress()">
			<button class="cu-btn bg-default-color margin-tb-sm lg round" form-type="submit">添加地址</button>
		</view>
		
	</view>
</template>

<script>
	import API from '@/util/api.js';
	
	export default {
		data() {
			return {
				address : {
					addressId: '',
					userId: '',
					content: '',
					tel: '',
					realName: '',
					def: '',
				},
				list: []
			}
		},
		onLoad() {
			var user = uni.getStorageSync("user");
			var that = this;
			uni.request({
				url: API.baseUrl + '/wx/findAddress',
				data: {
					userId : user.userId,
				},
				success(res) {
					if(res.data.code == 200) {
						console.log('receive data: ', res.data.data);
						that.list = res.data.data;
					}
				},
			})
		},
		onShow(){
			var user = uni.getStorageSync("user");
			var userId = user.userId;
			
			// 访问数据库，拿到该用户所有的地址，并展示出来
		},
		methods: {
			addAddress() {
				uni.navigateTo({
					url: '/pages/reserve/address/addAddress',
				})
			},
			deleteAddress(addressId) {
				// console.log(addressId);
				uni.request({
					url: API.baseUrl + '/address/delete',
					data: {
						addressId: addressId,
					},
					success(res) {
						uni.showToast({
							title: res.data.message,
							icon: 'none'
						});
					}
				})
			},
			setDefault(addressId) {
				uni.showModal({
					title: '提示',
					content: "是否确认将点击的地址设置为默认地址",
					success(res) {
						if(res.confirm) {
							uni.request({
								url: API.baseUrl + '/address/setDefaultAddress',
								data: {
									addressId: addressId,
								},
								success(res) {
									if(res.data.code == 200) { //设置成功就重新加载该页面，以刷新页面
										uni.redirectTo({
											url: '/pages/reserve/address/addresss',
										})
									}
									
								}
							})
							
						}
					},
					fail(err) {
						console.log(err);
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.bg-default-color {
		background-color: $default-color;
		color: #FFFFFF;
	}
</style>
