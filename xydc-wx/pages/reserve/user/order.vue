<template>
	<view>
		<view>用户订单</view>
		<mytabbar :identity='1' :selected='1'></mytabbar>
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
