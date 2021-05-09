<template>
	<view>
		<view class="padding-xs bg-white" style="min-height: 300rpx;">
			<view class="flex margin-xs padding-xs align-start"><!-- 上面展示的内容 -->
				<view class="fl margin-xs"> <!-- 左边的图片 -->
					<image :src="baseUrl + storeDetail.storePicture" style="width: 195rpx; height: 150rpx; border-radius: 10rpx;"></image>
				</view>
				<view class="fl margin-xs padding-xs"> <!-- 右边的内容 -->
					<view class="align-start text-xxl text-black">{{storeDetail.storeName}}</view>
					<view  class="align-end margin-top-sm">{{storeDetail.storeAddress}}</view>
				</view>
			</view> 
			<view class="flex align-center align-end padding-top-xs">
				<view class="fl margin-lr-xl padding-xs radius" @click="selectOne()" :class="select == 0 ? 'bg-default-color' : 'bg-white'">点菜</view>
				<view class="fl margin-lr-xl padding-xs radius" @click="selectTwo()" :class="select == 1?'bg-default-color':'bg-white'">商家</view>
				<view class="fl margin-lr-xl padding-xs radius" @click="selectThree()" :class="select == 2?'bg-default-color':'bg-white'">评价</view>
			</view> <!-- 下面那层开关 -->
		</view>
		
		<view class="VerticalBox" v-if="select == 0"><!-- 选菜界面 -->
			<scroll-view class="VerticalNav nav" scroll-y scroll-with-animation :scroll-top="verticalNavTop" style="height:calc(100vh - 400upx)">
				<view class="cu-item" :class="index==tabCur?'text-green cur':''" v-for="(item,index) in list" :key="index" @tap="TabSelect"
				 :data-id="index">
					Tab-{{item.name}}
				</view>
			</scroll-view>
			
			<scroll-view class="VerticalMain" scroll-y scroll-with-animation style="height:calc(100vh - 400upx)"
			 :scroll-into-view="'main-'+mainCur" @scroll="VerticalMain">
				<view class="padding-top padding-lr" v-for="(item,index) in list" :key="index" :id="'main-'+index">
					<view class="cu-bar solid-bottom bg-white">
						<view class="action">
							<text class="cuIcon-title text-green"></text> Tab-{{item.name}}
						</view>
					</view>
					<view class="cu-list menu-avatar">
						<view class="cu-item" v-for="product in item.products" :key="product.key" >
							<view class="cu-avatar round lg" :style="{backgroundImage: 'url(' + baseUrl + product.productPicture  + ')'}" @click="productDetail(product)"></view>
							<view class="content">
								<view class="text-grey"><text class="text-cut">{{product.productName}}</text></view>
								<view class="text-gray text-sm flex">
									<text class="text-cut">
										{{product.productInfo}}
									</text> 
								</view>
								
								<view class="text-red">{{product.price}} 元</view>
							</view>
							<view class="action flex" style="width: 110rpx;">
								<!-- <view class="text-grey text-xs">22:20</view> -->
								<view class="text-sm bg-default-color radius margin-top-xs" style="width: 40rpx;" @click="add(product)">+</view>
								<view class="cu-tag round sm"  :class="shoppingCart[product.productId].cnt > 0 ? 'bg-red' : 'bg-default-color' " :key="refresh">
									<!-- <input :value="shoppingCart[product.productId].cnt" style="width: 30rpx;"/> -->
									{{shoppingCart[product.productId].cnt}}
								</view>
								<view class="text-sm bg-default-color radius" style="width: 40rpx;" @click="sub(product)">-</view>
							</view>
						</view>
					</view>
					
				</view>
			</scroll-view>
			
			<view class="bg-white flex flex-direction padding-lr-xl round" style="position: fixed; bottom: 5rpx; width: 750rpx;">
				<view class="flex justify-between align-center">
					<view>
						<view class="align-start text-xl text-black">
							<text class="lg cuIcon-recharge">{{totalMoney}}</text>
							<!-- <view>99</view> -->
						</view>
						<view class="align-end text-xs">已加配送费{{deliveryCost}}元</view>
					</view>
					<view class="cu-btn bg-default-color margin-tb-xs lg round" @click="buy()">结账</view>
				</view>
				<!-- <button class="cu-btn bg-default-color margin-tb-xs lg round">结账</button> -->
			</view>
			
		</view>
		
		<view v-if="select == 1" class="bg-white"> <!-- 商家详情 -->
			<view class="cu-item text-xxl margin-xs padding-xs radius" style="width: 600rpx;">
				<text class="lg text-black cuIcon-phone"> {{storeDetail.storeTel}}</text>
			</view>
			<view class="margin-xs padding-xs radius">
				<view class="text-black text-xxl">许可证</view>
				<!-- <view  style="width: 710rpx; height: auto;" :style="{backgroundImage: 'url(' + baseUrl + storeDetail.licenses + ')'}"> -->
				<!-- </view> -->
				<image class="margin-xs padding-xs radius" :src="baseUrl + storeDetail.licenses"></image>
			</view>
			
			<view class="margin-xs padding-xs radius">
				<view class="text-black text-xxl">厨房</view>
				<!-- <view  style="width: 710rpx; height: auto;" :style="{backgroundImage: 'url(' + baseUrl + storeDetail.licenses + ')'}"> -->
				<!-- </view> -->
				<image class="margin-xs padding-xs radius" :src="baseUrl + storeDetail.kitchenPicture"></image>
			</view>
		</view>
		
		<view v-if="select == 2"> <!-- 评价详情 -->
			<view class="bg-white margin-sm padding-sm" style="border-radius: 30rpx;" v-for="(item, index) in evaleateList" :key="index">
				<view class="flex justify-between text-center" style="width: 100%;">
					<view class="margin-xs text-xxl default-color">{{item.storeScore==-1?'3':item.storeScore}}</view>
					<view class="margin-xs">{{item.showAccomplish}}</view>
				</view>
				<view class="fl margin-xs text-black" style="width: 100%;">{{item.evaluateContent==null?'这家伙很懒，什么都没留下':item.evaluateContent}}</view>
				<view  class="margin-xs text-grey" style="width: 100%;">{{item.products}}</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	
	// import cucustom from '@/colorui/components/cu-custom.vue';
	import API from '@/util/api.js';
	
	export default {
		data() {
			return {
				storeDetail: '', //这个店铺的所有信息
				baseUrl: API.baseUrl,
				deliveryCost: API.deliveryCost, //配送费
				select: 2, //当前是在那个页面，
				tagToId: [], // 标签映射到id
				shoppingCart: [], //购物车
				refresh: -1, //刷新数量栏的key
				totalMoney: 0, //总金额
				total: 0, //买的餐品总数量，用于检测是否买了
				evaleateList: [], //评价列表
				
				list: [],
				tabCur: 0,
				mainCur: 0,
				verticalNavTop: 0,
				load: true
			}
		},
		onLoad(option) {
			uni.showLoading({
				title: '加载中...',
				mask: true
			});
			// 拿到店铺的数据
			var storeList = uni.getStorageSync("storeList");
			this.storeDetail = storeList[option.index];
			this.select = 0; //初始进入选菜页面
			console.log('product data is :', this.storeDetail);
			
			/*将餐品数据排列成以下形式 
			 list = [
				 [0]: {
					 name: 'classify1'
					 id: 0,
					 products: [
					 	存放分类名为‘classify1’的所有餐品
					 ]
				 },
				 [1]: {
					 name: 'classify2',
					 id: 1,
					 products: [
						 存放分类名为‘classify2’的所有餐品
					 ]
				},
			]
			*/
			let pos = 0;
			for(let index in this.storeDetail.productList) { 
				let product = this.storeDetail.productList[index];
				let tag = product.tag;
				if(this.tagToId[tag] == null) {
					this.tagToId[tag] = pos;
					this.list[pos] = {};
					this.list[pos].products = [];
					this.list[pos].name = tag;
					this.list[pos].id = pos;
					pos = pos + 1;
				}
				product.key = 1000 + product.productId;
				this.shoppingCart[product.productId] = {};
				this.shoppingCart[product.productId].cnt = 0;
				this.list[this.tagToId[tag]].products.push(product); //tag对应的
			}
			console.log('list data is :', this.list);
			this.listCur = this.list[0];
			
			// 拿到历史订单信息，加载评价内容
			uni.request({
				url: API.baseUrl +  '/order/listJson',
				data: {
					storeId: this.storeDetail.storeId,
					storeScore: -2, //店铺评分大于0的订单
					accomplish: -2, //同上
				},
				success: (res) => {
					if(res.data.code == 0) {
						this.evaleateList = res.data.data;
						console.log('history: ', this.evaleateList);
						for(var index in this.evaleateList) {
							this.switchDate(index);
						}
					}
				}
			});
			
		},
		onReady() {
			uni.hideLoading()
		},
		methods: {
			selectOne() {
				console.log('order product');
				this.select = 0;
			},
			selectTwo() {
				console.log("store detail");
				this.select = 1;
			},
			selectThree() {
				console.log("evaluate");
				this.select = 2;
			},
			productDetail(product) {
				console.log(product.productName);
				uni.setStorageSync("productDetail", product);
				uni.navigateTo({
					url: '/pages/reserve/store/productDetail',
				})
			},
			add(product) {
				let productId = product.productId;
				console.log(product.productName, "+1");
				this.shoppingCart[productId].cnt = this.shoppingCart[productId].cnt + 1;
				if(this.shoppingCart[productId].cnt == 1)
					this.shoppingCart[productId].product = product;
				this.refresh = -3 - this.refresh; //变化key，用于动态刷新数量框
				if(this.total == 0) this.totalMoney = this.totalMoney + API.deliveryCost;
				this.total = this.total + 1;
				this.totalMoney = this.totalMoney + product.price;
				
			},
			sub(product) {
				let productId = product.productId;
				console.log(product.productName, "-1");
				let val = this.shoppingCart[productId].cnt;
				this.shoppingCart[productId].cnt = Math.max(this.shoppingCart[productId].cnt - 1, 0);
				this.refresh = -3 - this.refresh;
				if(val != this.shoppingCart[productId].cnt) {
					this.totalMoney = this.totalMoney - product.price;
					this.total = this.total - 1;
					if(this.total == 0) this.totalMoney = 0;
				}
			},
			buy() {
				if(this.total > 0) {
					uni.setStorageSync("shoppingCart", this.shoppingCart);
					uni.setStorageSync("storeId", this.storeDetail.storeId);
					uni.setStorageSync("auto", this.storeDetail.auto);
					uni.navigateTo({
						url: '/pages/reserve/store/pay'
					})
				}
			},
			TabSelect(e) {
				console.log(e);
				this.tabCur = e.currentTarget.dataset.id;
				this.mainCur = e.currentTarget.dataset.id;
				this.verticalNavTop = (e.currentTarget.dataset.id - 1) * 50
			},
			VerticalMain(e) {
				// #ifdef MP-ALIPAY
				   return false  //支付宝小程序暂时不支持双向联动 
				// #endif
				let that = this;
				let tabHeight = 0;
				if (this.load) {
					for (let i = 0; i < this.list.length; i++) {
						let view = uni.createSelectorQuery().select("#main-" + this.list[i].id);
						view.fields({
							size: true
						}, data => {
							this.list[i].top = tabHeight;
							tabHeight = tabHeight + data.height;
							this.list[i].bottom = tabHeight;
						}).exec();
					}
					this.load = false
				}
				let scrollTop = e.detail.scrollTop + 10;
				for (let i = 0; i < this.list.length; i++) {
					if (scrollTop > this.list[i].top && scrollTop < this.list[i].bottom) {
						this.verticalNavTop = (this.list[i].id - 1) * 50
						this.tabCur = this.list[i].id
						// console.log(scrollTop)
						return false
					}
				}
			},
			getLocalDate(nS) {
				if(nS == '' || nS == 0) return '';
				var mydate = new Date(parseInt(nS));
				return mydate.getFullYear() + '-' + (mydate.getMonth() + 1) + '-' + mydate.getDate();
			},
			switchDate(index) {
				this.evaleateList[index].showAccomplish = this.getLocalDate(this.evaleateList[index].accomplish);
			},
		},
	}
	
</script>

<style lang="scss">
	.bg-default-color {
		background-color: $default-color;
		color: #FFFFFF;
	}
	.default-color {
		color: $default-color;
	}
	.VerticalNav.nav {
		width: 200upx;
		white-space: initial;
	}
	.VerticalNav.nav .cu-item {
		width: 100%;
		text-align: center;
		background-color: #fff;
		margin: 0;
		border: none;
		height: 50px;
		position: relative;
	}
 
	.VerticalNav.nav .cu-item.cur {
		background-color: #f1f1f1;
	}
 
	.VerticalNav.nav .cu-item.cur::after {
		content: "";
		width: 8upx;
		height: 30upx;
		border-radius: 10upx 0 0 10upx;
		position: absolute;
		background-color: currentColor;
		top: 0;
		right: 0upx;
		bottom: 0;
		margin: auto;
	}
 
	.VerticalBox {
		display: flex;
	}
 
	.VerticalMain {
		background-color: #f1f1f1;
		flex: 1;
	}
</style>
