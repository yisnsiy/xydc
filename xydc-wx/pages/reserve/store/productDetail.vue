<template>
	<view>
		<image :src="baseUrl + productDetail.productPicture" style="width: 100%; height: 230px;"></image>
		<view class="grid margin-sm padding-sm bg-white radius">
			<view class="flex justify-between align-center" style="width: 100%;">
				<view class="margin-xs text-center text-black text-xxl">{{productDetail.productName}}</view>
				<view class="flex justify-between">
					<view class="margin-xs padding-xs bg-default-color text-sm radius">
						{{productDetail.price}} 元
					</view>
					<view class="margin-xs padding-xs bg-default-color text-sm radius">
						重量: {{productDetail.weight}}g
					</view>
				</view>
			</view>
			<view class="margin-xs">简介:{{productDetail.productInfo}}</view>
		</view>
		
		<view class="charts-box">
		    <qiunDataCharts type="column" :chartData="chartData" />
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
				productDetail: '',
				baseUrl: API.baseUrl,
				
				column: '',
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
			this.productDetail = uni.getStorageSync("productDetail");
			console.log("product's info is :", this.productDetail);
			this.chartData.series[0].data = [this.productDetail.calorie, this.productDetail.carbs,
										this.productDetail.fat, this.productDetail.protein];
			console.log(this.chartData.series[0]);
			// this.chartData.series.data.push(this.productDetail.calorie);
			// this.chartData.series.data.push(this.productDetail.carbs);
			// this.chartData.series.data.push(this.productDetail.fat);
			// this.chartData.series.data.push(this.productDetail.protein);
		},
		methods: {
			toJSON() {
				return this;
			}
		}
	}
</script>

<style lang="scss">
	.bg-default-color {
		background-color: $default-color;
		color: #FFFFFF;
	}
	.charts-box {
		width: 100%;
		height: 400rpx;
		display: block;
	}
</style>
