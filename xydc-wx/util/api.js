// const baseUrl = 'http://117.173.223.66:8080' //本机外网

const baseUrl = 'http://192.168.0.109:8080' //局域网

// const baseUrl = 'http://127.0.0.1:8080'

const storeRefreshInterval = 2 * 1000 // 店铺的刷新时间间隔为10秒

const deliveryCost = 1.5 //配送费

const delayTime = 30 * 60 * 1000 //提交订单时间往后顺延30分钟就是最久送达时间

export default {
	baseUrl,
	storeRefreshInterval,
	deliveryCost,
	delayTime,
}