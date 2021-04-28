<template>
	<view>
		<view>抢单大厅</view>
		<mytabbar :identity='2' :selected='0'></mytabbar>
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
