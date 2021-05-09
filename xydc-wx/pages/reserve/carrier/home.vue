<template>
	<view>
		<!-- <view class="flex flex-direction" style="width: 750rpx;" @click="addAddress()">
			<button class="cu-btn bg-default-color margin-tb-sm lg round" form-type="submit">添加地址</button>
		</view> -->
		<scroll-view scroll-y scroll-with-animation style="height:calc(100vh - 200rpx)" class="margin-top-xs" > <!-- 100rpx才是正常的 -->
			<view style="width: 750rpx;" v-for="(item, index) in orderList" :key="item.orderId">
				<view class="flex align-center bg-white margin-xs margin-lf-xl padding-xs justify-between" style="border-radius: 15rpx">
					<view style="width: 80%;" @click="orderDetail(item)">
						<view class="flex padding-xs">
							<view class="flex padding-xs" style="width:40%">
								<view class="bg-default-color radius margin-lr-xs" style="width: 70rpx;">店铺:</view>
								<text class="text-sm text-cut">{{item.store.storeName}}</text>
							</view>
							<view class="flex padding-xs text-cut">
								<view class="bg-default-color radius margin-lr-xs" style="width: 140rpx;">店铺地址: </view>
								<view class="text-sm">{{item.store.storeAddress}}</view>
							</view>
						</view>
						<view class="flex padding-lf-xs margin-top-xs">
							<view class="flex padding-xs text-cut" style="height: 54rpx;">
								<view class="bg-default-color radius margin-lr-sm" style="width: 70rpx;">餐品: </view>
								<view class="text-sm">{{item.products}}</view>
							</view>
						</view>
						<view class="flex padding-lf-xs margin-top-xs">
							<view class="flex padding-xs" style="width:40%">
								<view class="bg-default-color radius margin-lr-xs" style="width: 70rpx;">用户:</view>
								<view class="text-sm">{{item.address.realName}}</view>
							</view>
							<view class="flex padding-xs text-cut">
								<view class="bg-default-color radius margin-lr-xs" style="width: 140rpx;">用户地址: </view>
								<view class="text-sm">{{item.address.content}}</view>
							</view>
						</view>
					</view>
					<view class="padding-sm radius">
						<view class="text-xxl text-black" @click="acceptOrder(item.orderId)">
							<text class="lg text-red cuIcon-deliver"></text>
						</view>
					</view>
				</view>
			</view>
		</scroll-view>
		
		<mytabbar :identity='2' :selected='0'></mytabbar>
	</view>
</template>

<script>
	import API from '@/util/api.js';
	import mytabbar from "@/components/mytabbar/mytabbar.vue";
	
	export default {
		components: {
			mytabbar
		},
		data() {
			return {
				orderList: []
			}
		},
		onLoad() {
			this.pulldata();
		},
		onPullDownRefresh() {
			this.pulldata();
		},
		methods: {
			acceptOrder(orderId) {
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
			},
			orderDetail(order) {
				uni.setStorageSync("order", order);
				uni.navigateTo({
					url: '/pages/reserve/order/detail',
				})
				console.log(order);
			},
			pulldata() {
				uni.request({
					url: API.baseUrl +  '/order/listJson',
					data: {
						carrierOrder: -1, //此处表示为carrierOrder = 0
						storeOrder: -2, //得先店铺接单了
						accomplish: -1, //同上
					},
					success: (res) => {
						if(res.data.code == 0) {
							this.orderList = res.data.data;
							console.log(this.orderList);
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
