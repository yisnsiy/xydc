<template>
	<view>
		<view class="flex text-center align-center text-xxl text-black padding-xs" style="width: 100%;">
			<view class="fl margin-lr-sm radius" @click="switchOne()" style="width: 50%;" :class="select == 0?'bg-default-color':''">当前订单</view>
			<view class="fr margin-lr-sm radius" @click="switchTwo()" style="width: 50%;" :class="select == 1?'bg-default-color':''">历史订单</view>
		</view>
			<!-- <view style="width: 100%;">
				<button @click="BasicsSteps()">下一步</button>
			</view> -->
			<!-- <view class="cu-item" :class="index>basics?'':'text-blue'" v-for="(item,index) in basicsList" :key="index">
				"13:20"<text class="num" :data-index="index + 1"></text> {{item.name}}
			</view> -->
		<view v-if="select == 0">
			<view>
				<scroll-view scroll-y scroll-with-animation style="height:calc(100vh - 180rpx)">
					<view class="bg-white padding margin-top-xs" v-for="(item, index) in currentOrder" :key="item.orderId" v-if="item.accomplish==0">
						<view class="cu-steps border1 margin-xs padding-xs">
							<view class="cu-item" :class="item.carrierOrder == 0?'':'text-blue'">
								{{item.showCarrierOrder}}<text class="num" :data-index="1"></text> 接单
							</view>
							<view class="cu-item" :class="item.arriveStore == 0?'':'text-blue'" @click="as(index)">
								{{item.showArriveStore}}<text class="num" :data-index="2"></text> 到店
							</view>
							<view class="cu-item" :class="item.getProducts == 0?'':'text-blue'" @click="gp(index)">
								{{item.showGetProducts}}<text class="num" :data-index="3"></text> 取货
							</view>
							<view class="cu-item" :class="item.accomplish == 0?'':'text-blue'" @click="a(index)">
								{{item.showAccomplish}}<text class="num" :data-index="4"></text> 送达
							</view>
						</view>
						<view class="justify-center text-center" @click="orderDetail(item)">
							取餐码：
							<text class="text-xxl text-red">{{item.takeCode}}</text>
						</view>
					</view>
				</scroll-view>
			</view>
		</view>
		<view v-if="select == 1">
			<view>
				<scroll-view scroll-y scroll-with-animation style="height:calc(100vh - 180rpx)">
					<view style="width: 750rpx;" v-for="(item, index) in historyOrder" :key="item.orderId">
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
								<view class="text-xxl text-black">
									<view>{{item.showAccomplish}}</view>
								</view>
							</view>
						</view>
					</view>
				</scroll-view>
			</view>
		</view>
		
		<mytabbar :identity='2' :selected='1'></mytabbar>
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
				select: 0,
				currentOrder: [],
				historyOrder: [],
				user: ''
			}
		},
		onLoad() {
			let user = uni.getStorageSync("user");
			// 加载当前正在进行的订单的数据
			this.user = user;
			this.pulldata();
			// 加载已完成的订单的数据
			uni.request({
				url: API.baseUrl +  '/order/listJson',
				data: {
					carrierOrder: -2, //此处表示为carrierOrder > 0
					carrierId: user.userId,
					accomplish: -2, //同上
				},
				success: (res) => {
					if(res.data.code == 0) {
						this.historyOrder = res.data.data;
						console.log('history: ', this.historyOrder);
						for(var index in this.historyOrder) {
							this.switchDate(2, index);
						}
					}
				}
			});
			
		},
		onPullDownRefresh() {
			this.pulldata();
		},
		methods: {
			switchOne() {
				this.select = 0;
			},
			switchTwo() {
				this.select = 1;
			},
			getLocalTime(nS) {
				if(nS == '' || nS == 0) return '';
				var mydate = new Date(parseInt(nS));
				return mydate.getHours() + ':' + mydate.getMinutes() + ':' + mydate.getSeconds();
			},
			getLocalDate(nS) {
				if(nS == '' || nS == 0) return '';
				var mydate = new Date(parseInt(nS));
				return mydate.getFullYear() + '-' + (mydate.getMonth() + 1) + '-' + mydate.getDate();
			},
			switchDate(flag, index) {
				if(flag == 1) {
					this.currentOrder[index].showCarrierOrder = this.getLocalTime(this.currentOrder[index].carrierOrder);
					this.currentOrder[index].showArriveStore = this.getLocalTime(this.currentOrder[index].arriveStore);
					this.currentOrder[index].showGetProducts = this.getLocalTime(this.currentOrder[index].getProducts);
					this.currentOrder[index].showAccomplish = this.getLocalTime(this.currentOrder[index].accomplish);
				}else if(flag == 2) {
					this.historyOrder[index].showCarrierOrder = this.getLocalTime(this.historyOrder[index].carrierOrder);
					this.historyOrder[index].showArriveStoreOrder = this.getLocalTime(this.historyOrder[index].arriveStore);
					this.historyOrder[index].showGetProducts = this.getLocalTime(this.historyOrder[index].getProducts);
					this.historyOrder[index].showAccomplish = this.getLocalDate(this.historyOrder[index].accomplish) + ' ' + this.getLocalTime(this.historyOrder[index].accomplish);
				}
			},
			as(index) { //到店函数
				uni.showModal({
				    title: '提示',
				    content: '是否到店',
				    success: res=> {
				        if (res.confirm) {
							let now = new Date().getTime();
							uni.request({
								url: API.baseUrl + '/order/updateOrder',
								method: 'POST',
								data: {
									orderId: this.currentOrder[index].orderId,
									arriveStore: now,
								},
								success: res=> {
									uni.showToast({
										title: res.data.message,
										duration: 1 * 1000,
										position: 'center',
										icon: 'none'
									})
									if(res.data.code == 200) {
										// uni.navigateBack()
										this.currentOrder[index].arriveStore = now;
										this.switchDate(1, index);
									}
								}
								
							})
				        } else if (res.cancel) {
				            console.log('用户点击取消');
				        }
				    }
				});
			},
			gp(index) { //取餐函数
				uni.showModal({
				    title: '提示',
				    content: '是否取餐完成',
				    success: res=> {
				        if (res.confirm) {
							if(this.currentOrder[index].arriveStore == null || this.currentOrder[index].arriveStore == '') { //先完成前置条件
								uni.showToast({
									title: '请先上传到店时间',
									duration: 1 * 1000,
									position: 'center',
									icon: 'none'
								})
							}else {
								let now = new Date().getTime();
								uni.request({
									url: API.baseUrl + '/order/updateOrder',
									method: 'POST',
									data: {
										orderId: this.currentOrder[index].orderId,
										getProducts: now,
									},
									success: res=> {
										uni.showToast({
											title: res.data.message,
											duration: 1 * 1000,
											position: 'center',
											icon: 'none'
										})
										if(res.data.code == 200) {
											// uni.navigateBack()
											this.currentOrder[index].getProducts = now;
											this.switchDate(1, index);
										}
									}
									
								})
							}
							
						} else if (res.cancel) {
				            console.log('用户点击取消');
				        }
				    }
				});
			},
			a(index) { //完成函数
				uni.showModal({
				    title: '提示',
				    content: '是否完成订单',
				    success: res=> {
				        if (res.confirm) {
							if(this.currentOrder[index].getProducts == null || this.currentOrder[index].getProducts == '') { //先完成前置条件
								uni.showToast({
									title: '请先上传取货时间',
									duration: 1 * 1000,
									position: 'center',
									icon: 'none'
								})
							}else {
								let now = new Date().getTime();
								uni.request({
									url: API.baseUrl + '/order/updateOrder',
									method: 'POST',
									data: {
										orderId: this.currentOrder[index].orderId,
										accomplish: now,
									},
									success: res=> {
										uni.showToast({
											title: res.data.message,
											duration: 1 * 1000,
											position: 'center',
											icon: 'none'
										})
										if(res.data.code == 200) {
											// uni.navigateBack()
											this.currentOrder[index].accomplish = now;
											this.switchDate(1, index);
										}
									}
									
								})
							}
							
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
			pulldata(){
				uni.request({
					url: API.baseUrl +  '/order/listJson',
					data: {
						carrierOrder: -2, //此处表示为carrierOrder > 0
						carrierId: this.user.userId,
						accomplish: -1, //同上
					},
					success: (res) => {
						if(res.data.code == 0) {
							this.currentOrder = res.data.data;
							console.log('current:', this.currentOrder);
							for(var index in this.currentOrder) {
								this.switchDate(1, index);
							}
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
	.border1 {
		border-width: 0px 0px 1px 0px;
		border-style: solid;
		border-color: #cccccc;
	}
</style>
