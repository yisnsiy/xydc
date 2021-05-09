<template>
	<view>
		<view class="flex text-center align-center text-xxl text-black padding-xs" style="width: 100%;">
			<view class="fl margin-lr-sm radius" @click="switchOne()" style="width: 50%;" :class="select == 0?'bg-default-color':''">结算信息</view>
			<view class="fr margin-lr-sm radius" @click="switchTwo()" style="width: 50%;" :class="select == 1?'bg-default-color':''">营养信息</view>
		</view>
		<view v-if="select == 0" >
			<view class="bg-white margin-lr-sm" style="border-radius: 20rpx; min-height: 275rpx;">
				<view class="text-black text-xxl margin-xs padding-xs text-center border1" style="color: #55AAFF;">地址信息</view>
				<view class="padding-xs margin-xs">
					<view class="align-center flex justify-between">
						<view class="radius padding-xs"> <!-- 左方块 -->
							<view class="flex solid-bottom padding-xs align-start align-center"> <!-- 上面那一层 -->
								<view class="radius fl padding-xs">{{selectAddress.realName}}</view> <!-- 姓名 -->
								<view class="radius fl padding-xs">{{selectAddress.tel}}</view> <!-- 电话 -->
							</view>
							<view class="flex solid-bottom padding-sm align-end text-lg text-black">{{selectAddress.content}}</view> <!-- 下面那一层,用户放地址 -->
						</view>
						<view class="radius padding-xs margin-lr-xs text-xl" @tap="showModal">
							<text class="text-black cuIcon-refresharrow"></text>
						</view>
					</view>
				</view>
				<!-- 地址单选框 -->
				<view class="cu-modal" :class="modalName=='RadioModal'?'show':''" @tap="hideModal">
					<view class="cu-dialog" @tap.stop="">
						<radio-group class="block" @change="RadioChange">
							<view class="cu-list menu text-left">
								<view class="cu-item" v-for="(item,index) in list" :key="item.addressId">
									<label class="flex justify-between align-center flex-sub">
										 <view class="align-center flex">
											<view class="radius padding-xs"> <!-- 左方块 -->
												<view class="flex solid-bottom padding-xs align-start align-center"> <!-- 上面那一层 -->
													<view class="radius fl padding-xs">{{item.realName}}</view> <!-- 姓名 -->
													<view class="radius fl padding-xs">{{item.tel}}</view> <!-- 电话 -->
												</view>
												<view class="flex solid-bottom padding-sm align-end text-lg text-black">{{item.content}}</view> <!-- 下面那一层,用户放地址 -->
											</view>
										 </view>
										 <view class="radius padding-xs margin-lr-xs">
											<radio class="round" :class="radio=='radio' + index?'checked':''" :checked="selectAddress.addressId== item.addressId?true:false"
											 :value="'radio' + index"></radio>
											<!-- <text class="text-red cuIcon-delete"></text> -->
										</view>
										
									</label>
								</view>
							</view>
						</radio-group>
					</view>
				</view>
			</view>
			
			<view class="bg-white margin-lr-sm" style="border-radius: 20rpx;">
				<scroll-view scroll-y scroll-with-animation style="height:calc(100vh - 480upx)">
					<view class="text-black text-xxl margin-xs padding-xs text-center border1" style="color: #55AAFF;">选购餐品</view>
					<view class="cu-list menu-avatar">
						<view class="cu-item" v-for="(food,index) in shoppingCart" :key="food.product.productId" >
							<view class="cu-avatar round lg" :style="{backgroundImage: 'url(' + baseUrl + food.product.productPicture  + ')'}"></view>
							<view class="content">
								<view class="text-grey"><text class="text-cut">{{food.product.productName}}</text></view>
								<!-- <view class="text-gray text-sm flex">
									<text class="text-cut">
										{{index}}
									</text> 
								</view> -->
								
								<view class="text-red">{{food.product.price}} 元</view>
							</view>
							<!-- 右侧的按钮 -->
							<view class="action flex ">
								<view class="cu-tag round text-xxl text-black bg-white">
									x {{food.cnt}}
								</view>
							</view>
						</view>
					</view>
					<view class="cu-item flex justify-between padding-xs margin-xs margin-lr-sm">
						<view class="content">
							<view class="text-xxl text-black"><text>配送费{{deliveryCost}}元</text></view>
						</view>
						<!-- 右侧的按钮 -->
						<view class="action flex ">
							<view class="cu-tag round text-xxl text-black bg-white">
								x 1
							</view>
						</view>
					</view>
				</scroll-view>
			</view>
			
			<view class="bg-white flex flex-direction padding-lr-xl round" style="position: fixed; bottom: 5rpx; width: 750rpx;">
				<view class="flex justify-between align-center">
					<view>
						<view class="align-start text-xl text-black">
							<text class="lg cuIcon-recharge">{{totalMoney}}</text>
							<!-- <view>99</view> -->
						</view>
						<view class="align-end text-xs">预计送达时间: {{likeLyDeliveryTimeStr}}</view>
					</view>
					<view class="cu-btn bg-default-color margin-tb-xs lg round" @click="buy()">付款</view>
				</view>
				<!-- <button class="cu-btn bg-default-color margin-tb-xs lg round">结账</button> -->
			</view>
		
		</view>
		
		<view v-if="select == 1" >
			<view class="charts-box">
			    <qiunDataCharts type="column" :chartData="chartData" />
			</view>
		</view>
		
	</view>
</template>

<script>
	import API from '@/util/api.js';
	import qiunDataCharts from '@/uni_modules/qiun-data-charts/components/qiun-data-charts/qiun-data-charts.vue';
	
	export default {
		components: {
			qiunDataCharts,
		},
		data() {
			return {
				shoppingCart: [],
				deliveryCost: API.deliveryCost,
				totalMoney: API.deliveryCost,
				list: [],
				selectAddress: '',
				modalName: '',
				baseUrl: API.baseUrl,
				currentTime: '',
				likeLyDeliveryTimeStr: '',
				select: 0,
				
				chartData:{
				  categories: ["卡路里", "碳水化合物", "脂肪", "蛋白质"],
				  series: [{
				    name: "营养值",
				    data: []
				  }]
				}
			}
		},
		onLoad() {
			// 加载购物车里面的所有数据
			let shoppingCart = uni.getStorageSync("shoppingCart");
			let pos = 0;
			for(let index in shoppingCart) {
				if(shoppingCart[index] != null && shoppingCart[index].cnt > 0) {
					this.shoppingCart[pos ++] = shoppingCart[index];
					// this.shoppingCart['product' + index] = shoppingCart[index];
					this.totalMoney += shoppingCart[index].product.price * shoppingCart[index].cnt;
				}
			}
			console.log('shopping cart is :', this.shoppingCart);
			
			
			// 获取所有的地址
			let user = uni.getStorageSync("user");
			uni.request({
				url: API.baseUrl + '/wx/findAddress',
				data: {
					userId : user.userId,
				},
				success:res=> {
					if(res.data.code == 200) {
						console.log('receive data: ', res.data.data);
						this.list = res.data.data;
						for(let key in this.list) {
							if(this.list[key].def == true) {
								this.selectAddress = this.list[key];
								console.log(this.selectAddress);
								break;
							}
						}
					}
				},
			})
			
			//装载购物车的营养信息
			this.chartData.series[0].data = [0, 0, 0, 0];
			for(let i = 0; i < pos; ++ i) {
				this.chartData.series[0].data[0] += this.shoppingCart[i].product.calorie * this.shoppingCart[i].cnt;
				this.chartData.series[0].data[1] += this.shoppingCart[i].product.carbs * this.shoppingCart[i].cnt;
				this.chartData.series[0].data[2] += this.shoppingCart[i].product.fat * this.shoppingCart[i].cnt;
				this.chartData.series[0].data[3] += this.shoppingCart[i].product.protein * this.shoppingCart[i].cnt;
			}
			for(let i = 0; i < 4; ++ i) {
				this.chartData.series[0].data[i] = this.chartData.series[0].data[i].toFixed(2);
			}
			
		},
		onShow() {
			let dt = new Date(); //获得时间格式类似于这样 Sun May 02 2021 20:11:58 GMT+0800
			this.currentTime = dt.getTime(); //获得时间格式的时间戳 就是13位数
			let likeLyDeliveryTime = this.currentTime + API.delayTime; //顺延时间后的时间戳
			let dateTime = new Date(likeLyDeliveryTime); //利用时间戳反推回去时间格式
			this.likeLyDeliveryTimeStr = dateTime.getHours() + ':' + dateTime.getMinutes();
		},
		methods: {
			showModal() {
				// console.log(e);
				// this.modalName = e.currentTarget.dataset.target
				this.modalName = 'RadioModal';
			},
			hideModal(e) {
				this.modalName = null
			},
			RadioChange(evt) {
				let value = evt.target.value;
				value = value.substr(5);
				value = value * 1; //转化为数字
				this.selectAddress = this.list[value];
			},
			buy() {
				uni.showModal({
				    title: '支付订单',
				    content: '是否确认支付',
				    success: res=> {
				        if (res.confirm) {
							let user = uni.getStorageSync("user");
							let storeId = uni.getStorageSync("storeId");
							let auto = uni.getStorageSync("auto");
							console.log(auto);
							let products = "";
							let storeOrder = 0;
							if(auto == true) {
								storeOrder = this.currentTime;
							}
							for(let index in this.shoppingCart) {
								products = products + this.shoppingCart[index].product.productName + "x" + this.shoppingCart[index].cnt + ';';
							}
				            console.log('用户点击确定');
							uni.request({
								url: API.baseUrl + '/wx/submitOrder',
								method: 'POST',
								data: {
									userId: user.userId,
									storeId: storeId,
									totalMoney: this.totalMoney,
									products: products,
									userOrder: this.currentTime,
									storeOrder: storeOrder,
									addressId: this.selectAddress.addressId,
								},
								success: res=> {
									uni.showToast({
										title: res.data.message,
										duration: 1 * 1000,
										position: 'center',
										icon: 'none'
									})
									if(res.data.code == 200) {
										// 给餐品增加次数
										for(let index in this.shoppingCart) {
											this.addSellNum(this.shoppingCart[index].product.productId, this.shoppingCart[index].cnt);
										}
									}
								}
							})
				        } else if (res.cancel) {
				            console.log('用户点击取消');
				        }
				    }
				});
			},
			switchOne() {
				this.select = 0;
			},
			switchTwo() {
				this.select = 1;
			},
			toJSON() {
				return this;
			},
			addSellNum(id, cnt) {
				console.log(id, cnt);
				uni.request({
					url: API.baseUrl + '/wx/addSellNum',
					method: 'POST',
					data: {
						productId: id,
						cnt: cnt,
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
	.border1 {
		border-width: 0px 0px 1px 0px;
		border-style: solid;
		border-color: #cccccc;
	}
	.charts-box {
		width: 100%;
		height: 400rpx;
		display: block;
	}
</style>