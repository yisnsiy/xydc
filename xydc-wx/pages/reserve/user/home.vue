<template>
	<view>
		<view class="cu-bar search bg-white">
			<view class="search-form round">
				<text class="cuIcon-search"></text>
				<input @focus="InputFocus" @blur="InputBlur" :adjust-position="false" type="text" placeholder="搜索店铺, 食物"
									 confirm-type="search" v-model="searchContent"></input>
			</view>
			<view class="action">
				<button class="cu-btn bg-default_color shadow-blur round" @click="search()">搜索</button>
			</view>	
		</view>
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;" @click="inStore(index)" v-for="(item, index) in storeList" :key="item.storeId">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + item.storePicture" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">{{item.storeName}}</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">{{item.score}}</view> <!-- 评分 -->
				</view>
			</view>
		</view>
		
		<mytabbar :identity='1' :selected='0'></mytabbar>
	</view>
	
</template>

<script>
	import mytabbar from "@/components/mytabbar/mytabbar.vue";
	import API from '@/util/api.js';
	
	export default {
		components: {
			mytabbar
		},
		data() {
			return {
				searchContent: '',
				baseUrl: API.baseUrl,
				InputBottom: 0,
				
				storeList: [],
			}
		},
		onLoad() {
		},
		onShow() {
			this.getStore();
		},
		methods: {
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			InputBlur(e) {
				this.InputBottom = 0
			},
			search() {
				console.log('for search this content is : ',this.searchContent);
				uni.setStorageSync("storeRefreshTime", 0);
				this.getStore(this.searchContent);
			},
			getStore(searchContent) {
				var nowTime = (new Date()).getTime();
				var preStoreRefreshTime = uni.getStorageSync("storeRefreshTime");
				console.log(preStoreRefreshTime)
				if(preStoreRefreshTime == null || preStoreRefreshTime == 0) {
					preStoreRefreshTime = 0;
				}
				console.log(nowTime, preStoreRefreshTime, API.storeRefreshInterval);
				if(nowTime - preStoreRefreshTime > API.storeRefreshInterval) {  //达到刷新时间间隔了，重新从数据库拿数据
					console.log('get data from database');
					uni.request({
						url: API.baseUrl + '/wx/findStore',
						data: {
							storeName: searchContent || ''
						},
						success:res=> {
							console.log("store\'s content is :", res.data.data);
							if(res.data.code == 200) {
								uni.setStorageSync("storeList", res.data.data);
								uni.setStorageSync("storeRefreshTime",  nowTime); //设置存放数据的时间
								console.log('tiem is : ', nowTime);
								this.storeList = res.data.data;
							}
						}
					})
				}else {
					console.log('get data from memory');
					this.storeList = uni.getStorageSync("storeList");
				}
			},
			inStore(index) {
				uni.navigateTo({
					url: '/pages/reserve/store/storeDetail?index='+index,
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
