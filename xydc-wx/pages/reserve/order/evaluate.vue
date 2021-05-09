<template>
	<view>
		<view class="bg-white margin-xs padding-xs radius text-center">
			<view class="text-xxl solids-bottom margin-xs padding-xs" style="color: #55AAFF;">店铺评分</view>
			<view class="flex text-xxl text-center justify-center margin-top" style="font-size:80rpx;">
				<view class="cu-item padding-lr-xs" @click="setStoreScore(index)" v-for="index in 5" :key = index>
					<text class="lg text-gray cuIcon-favorfill" :class="storeScore > index ? 'default-color':''"></text>
				</view>
			</view>
		</view>
		<view class="bg-white margin-xs padding-xs radius text-center margin-top-sm">
			<view class="text-xxl solids-bottom margin-xs padding-xs" style="color: #55AAFF;">骑手评分</view>
			<view class="flex text-xxl text-center justify-center margin-top" style="font-size:80rpx;">
				<view class="cu-item padding-lr-xs" @click="setCarrierScore(index)" v-for="index in 5" :key = index>
					<text class="lg text-gray cuIcon-favorfill" :class="carrierScore > index ? 'default-color':''"></text>
				</view>
			</view>
		</view>
		<view class="cu-form-group align-start margin-top-sm">
			<view class="title">评语</view>
			<textarea @input="textareaInput" placeholder="评语输入框"></textarea>
		</view>
		<view class="padding flex flex-direction" style="position: fixed; bottom: 5rpx; width: 750rpx;" @click="sumbitEvaluate()">
			<button class="cu-btn bg-default-color margin-tb-sm lg round" form-type="submit">提交评价</button>
		</view>
	</view>
</template>

<script>
	import API from '@/util/api.js';
	
	export default {
		data() {
			return {
				orderId: '',
				storeScore: 3,
				carrierScore: 3,
				evaluateContent: '',
			}
		},
		onLoad(option) {
			this.orderId = option.orderId;
		},
		methods: {
			setStoreScore(index) {
				console.log(index);
				this.storeScore = index + 1;
			},
			setCarrierScore(index) {
				console.log(index);
				this.carrierScore = index + 1;
			},
			textareaInput(e) {
				this.evaluateContent = e.detail.value
			},
			sumbitEvaluate() {
				uni.request({
					url: API.baseUrl + '/order/updateOrder',
					method: 'POST',
					data: {
						orderId: this.orderId,
						storeScore: this.storeScore,
						carrierScore: this.carrierScore,
						evaluateContent: this.evaluateContent,
					},
					success: (res) => {
						if(res.data.code == 200) {
							uni.showToast({
								title: '提交评价成功',
								duration: 1 * 1000,
								position: 'center',
								icon: 'none'
							})
						}
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
	.default-color {
		color: $default-color;
	}
</style>
