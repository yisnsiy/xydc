<template>
	<view>
		<view class="flex text-center align-center text-xxl text-black padding-xs" style="width: 100%;">
			<view class="fl margin-lr-sm" @click="switchOne()" style="width: 50%;">当前订单</view>
			<view class="fr margin-lr-sm" @click="switchTwo()" style="width: 50%;">历史订单</view>
		</view>
		
		<view v-if="select == 0">
			<view style="width: 100%;">
				<button @click="BasicsSteps()">下一步</button>
			</view>
			<view>
				<view class="bg-white padding margin-top-xs">
					<view class="cu-steps">
						<view class="cu-item" :class="index>basics?'':'text-blue'" v-for="(item,index) in basicsList" :key="index">
							"13:20"<text class="num" :data-index="index + 1"></text> {{item.name}}
						</view>
					</view>
				</view>
			</view>
		</view>
		<view v-if="select == 1" class="text-red">历史订单</view>
		
		<mytabbar :identity='1' :selected='1'></mytabbar>
	</view>
</template>

<script>
	import mytabbar from "@/components/mytabbar/mytabbar.vue";
	
	export default {
		components: {
			mytabbar
		},
		data() {
			return{
				select: 0,
				basicsList: [{cuIcon: 'usefullfill',name: '下单'}, 
							{cuIcon: 'radioboxfill',name: '店家已接单'}, 
							{cuIcon: 'roundclosefill',name: '已取货'},
							{cuIcon: 'roundcheckfill',name: '已完成'
				}],
				basics: 0
			}
		},
		methods: {
			switchOne() {
				this.select = 0;
			},
			switchTwo() {
				this.select = 1;
			},
			BasicsSteps() {
				this.basics= this.basics == this.basicsList.length - 1 ? 0 : this.basics + 1				
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
		background-color: #FFFFFF;
	}
</style>
