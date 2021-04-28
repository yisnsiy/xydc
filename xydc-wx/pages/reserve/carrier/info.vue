<template>
	<view>
		<view>骑手个人信息</view>
		<mytabbar :identity='2' :selected='2'></mytabbar>
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
