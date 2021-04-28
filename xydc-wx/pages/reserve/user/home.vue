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
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;" @click="inStore()">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + imgUrl" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">铭记包子铺</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">5.0</view> <!-- 评分 -->
				</view>
			</view>
		</view>
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + imgUrl" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">铭记包子铺</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">5.0</view> <!-- 评分 -->
				</view>
			</view>
		</view>
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + imgUrl" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">铭记包子铺</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">5.0</view> <!-- 评分 -->
				</view>
			</view>
		</view>
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + imgUrl" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">铭记包子铺</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">5.0</view> <!-- 评分 -->
				</view>
			</view>
		</view>
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + imgUrl" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">铭记包子铺</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">5.0</view> <!-- 评分 -->
				</view>
			</view>
		</view>
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + imgUrl" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">铭记包子铺</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">5.0</view> <!-- 评分 -->
				</view>
			</view>
		</view>
		
		<view class="flex bg-white solid-bottom margin-sm padding-xs" style="border-radius: 30rpx;">
			<view class="margin-xs padding-xs radius"> <!-- 图片 -->
				<image :src="baseUrl + imgUrl" style="width: 220rpx; height: 160rpx; border-radius: 10rpx;"></image>
			</view>
			<view> <!-- 整个右侧 -->
				<view class="margin-xs padding-xs radius text-xxl text-black">铭记包子铺</view> <!-- 店铺名 -->
				<view class="flex  margin-xs padding-xs radius margin-top-xxl align-center">
					<view class="cu-item fl text-xxl padding-xs"> <!-- 图标 -->
						<text class="cuIcon-favorfill" style="color: #55AAFF;"></text>
					</view>
					<view  class="fl padding-xs radius text-xl text-black">5.0</view> <!-- 评分 -->
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
				imgUrl: '/images/store/20210423205243d2a1625f-6d30-4554-8ffc-199232cc195a.jpg',
				baseUrl: API.baseUrl,
			}
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
