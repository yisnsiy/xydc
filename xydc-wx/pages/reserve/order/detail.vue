<template>
	<view>
		<view class="cu-form-group margin-top-xs">
			<view class="title">店铺名</view>
			<input name="input" v-model="order.store.storeName" disabled></input>
		</view>
		<view class="cu-form-group" v-if="user.userId == order.carrierId || user.userId == order.userId"> <!-- 已接单的骑手或者用户本身才能看 -->
			<view class="title">店铺电话</view>
			<input name="input" v-model="order.store.storeTel" disabled></input>
		</view>
		<view class="cu-form-group align-start">
			<view class="title">店铺地址</view>
			<textarea maxlength="-1" v-model="order.store.storeAddress"></textarea>
		</view>
		<view class="cu-form-group" v-if="order.storeScore != -1 && user.userId == order.userId"> <!-- 已评价，且只能本人查看 -->
			<view class="title">店铺评分</view>
			<input name="input" v-model="order.storeScore" disabled></input>
		</view>
		<view class="cu-form-group margin-top-xs">
			<view class="title">用户名</view>
			<input name="input" v-model="order.address.realName" disabled></input>
		</view>
		<view class="cu-form-group" v-if="user.userId == order.carrierId || user.userId == order.userId"> <!-- 已接单的骑手或者用户本身才能看 -->
			<view class="title">用户电话</view>
			<input name="input" v-model="order.address.tel" disabled></input>
		</view>
		<view class="cu-form-group align-start">
			<view class="title">用户地址</view>
			<textarea maxlength="-1" v-model="order.address.content"></textarea>
		</view>
		<view class="cu-form-group margin-top-xs" v-if="user.userId == order.userId"> <!-- 只有用户可看 -->
			<view class="title">骑手名</view>
			<input name="input" v-model="order.carrier.nickname" disabled></input>
		</view>
		<view class="cu-form-group" v-if="user.userId == order.userId"> <!-- 只有用户可看 -->
			<view class="title">骑手电话</view>
			<input name="input" v-model="order.carrier.tel" disabled></input>
		</view>
		<view class="cu-form-group" v-if="order.carrierScore != -1">
			<view class="title">骑手评分</view>
			<input name="input" v-model="order.carrierScore" disabled></input>
		</view>
		<view class="cu-form-group align-start margin-top-xs">
			<view class="title">餐品</view>
			<textarea maxlength="-1" v-model="showProduct"></textarea>
		</view>
		<view class="cu-form-group align-start" v-if="order.storeScore != -1">
			<view class="title">评语</view>
			<textarea maxlength="-1" v-model="order.evaluateContent"></textarea>
		</view>
		<view class="cu-form-group" v-if="user.userId == order.userId"> <!-- 只有用户可看 -->
			<view class="title">总金额</view>
			<input name="input" v-model="order.totalMoney" disabled></input>
		</view>
		<!-- 只有在订单未接的情况下骑手可看 -->
		<view class="padding flex flex-direction" style="position: fixed; bottom: 5rpx; width: 750rpx;" @click="acceptOrder()" v-if="(order.carrierId == null || order.carrierOrder == 0) && user.identity == 2">
			<button class="cu-btn bg-default-color margin-tb-sm lg round" form-type="submit">接单</button>
		</view>
	</view>
</template>

<script>
	import API from '@/util/api.js';
	
	export default {
		data() {
			return {
				order: [],
				showProduct: '',
				user: [],
			}
		},
		onLoad() {
			this.user = uni.getStorageSync("user");
			console.log(this.user);
			this.order = uni.getStorageSync("order");
			if(this.order == null) {
				uni.navigateBack();
			}
			this.showProduct = this.order.products.replace(/;/g, "\n");
		},
		methods: {
			acceptOrder() {
				let orderId = this.order.orderId;
				uni.showModal({
				    title: '抢单',
				    content: '是否确认抢单',
				    success: res=> {
				        if (res.confirm) {
				            console.log('用户点击确定');
							let user = uni.getStorageSync("user");
							let time = new Date().getTime();
							uni.request({
								url: API.baseUrl + '/wx/carrierAcceptOrder',
								method: 'POST',
								data: {
									orderId: orderId,
									carrierId: user.userId,
									carrierOrder: time,
								},
								success: (res) => {
									uni.showToast({
										title: res.data.message,
										duration: 1 * 1000,
										position: 'center',
										icon: 'none'
									});
								}
							});
				        } else if (res.cancel) {
				            console.log('用户点击取消');
				        }
				    }
				});
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
