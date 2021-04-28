<template>
	<view>
		<view>骑手历史订单</view>
		<mytabbar :identity='2' :selected='1'></mytabbar>
	</view>
</template>

<script>
	import mytabbar from "@/components/mytabbar/mytabbar.vue";
	
	export default {
		components: {
			mytabbar
		},
	}
</script>

<style>
</style>
