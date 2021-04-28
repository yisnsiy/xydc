<template>
	<view class="tab-bar">
		<view v-for="(item,index) in list" :key="index" class="tab-bar-item" @click="switchTab(item, index)">
			<image class="tab_img" :lazy-load="true" :src="selected === index ? item.selectedIconPath : item.iconPath"></image>
			<view class="tab_text" :style="{color: selected === index ? selectedColor : color}">{{item.text}}</view>
		</view>
	</view>
</template>
 
 
 
<script>
	export default {
		props: {
			selected: { // 当前选中的tab index
				type: Number,
				default: 0
			},
			identity: { // 当前角色
				type: Number,
				default: 1
			}
 
		},
		data() {
			return {
				color: "#8F8F94",
				selectedColor: "#333333",
				list: [{
					"text": "首页",
					"iconPath": "/static/tabbar/indexpage.png",
					"selectedIconPath": "/static/tabbar/indexpage_fill.png"
				},{
					"text": "订单",
					"iconPath": "/static/tabbar/list.png",
					"selectedIconPath": "/static/tabbar/list-on.png"
				},{
					"text": "我的",
					"iconPath": "/static/tabbar/me.png",
					"selectedIconPath": "/static/tabbar/me_fill.png"
				}],
				
				tab_bar_list:[{ //前3个事用户的导航栏，后3个是骑手的导航栏跳转地址
						"pagePath":"/pages/reserve/user/home",
					},{
						"pagePath":"/pages/reserve/user/order",
					},{
						"pagePath":"/pages/reserve/user/info",
					},{
						"pagePath":"/pages/reserve/carrier/home",
					},{
						"pagePath":"/pages/reserve/carrier/order",
					},{
						"pagePath":"/pages/reserve/user/info",
					}
				]
			}
		},
		methods: {
			switchTab(item, index) {
				let url = this.tab_bar_list[(this.identity - 1) * 3 + index].pagePath;
				console.log(url);
				uni.switchTab({
					url: url,
					fail(err) {
						console.log("跳转失败", err);
					}
				})
			}
		}
	}
</script>
 
<style lang="scss">
	.tab-bar {
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		height: 100rpx;
		background: white;
		display: flex;
		justify-content: center;
		align-items: center;
		padding-bottom: env(safe-area-inset-bottom); // 适配iphoneX的底部
 
		.tab-bar-item {
			flex: 1;
			text-align: center;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
 
			.tab_img {
				width: 37rpx;
				height: 41rpx;
			}
 
			.tab_text {
				font-size: 20rpx;
				margin-top: 9rpx;
			}
		}
	}
</style>