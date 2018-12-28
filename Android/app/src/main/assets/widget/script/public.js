var statusBarfixHeight = 0;

function $(id) {

	return document.getElementById(id);
}
// 打开 window
function GotoWin(url, name) {
	if (!url) {
		return;
	}
	if (!name) {
		name = url;
	}
	api.openWin({
		name: name,
		url: url
	});
}
// window + frame 窗口结构中，打开 content 区域所在的frame
function openContent(url, fname, frect) {
	if (!url) {
		return;
	}
	var fn = fname ? fname : 'content_frm';
	var fr = {}; // frame所在的rect区域
	if (frect) {
		fr = frect;
	} else {
		// header 高度为 api.css 样式中声明的 44px 加上沉浸式效果的高度
		var headerH = 44 + statusBarfixHeight;
		var footerH = 30; // footer 高度为 api.css 样式中声明的 30px
		fr.marginTop = headerH;
		fr.marginBottom = footerH;
	}
	api.openFrame({
		name: fn,
		url: url,
		bounces: true,
		rect: fr
	});
}
function curtime() {
	return new Date().getTime();
}

function fixStatusBar() {
	var el = $('header');
	if (!el) {
		return;
	}
	var sysType = api.systemType;
	var ver = api.systemVersion;
	if (sysType == 'ios') {
		var num = parseInt(ver, 10);
		if (num >= 7) {
			el.style.paddingTop = '20px';
			statusBarfixHeight = 20;
		}
	} else if (sysType == 'android') {
		//var num = parseFloat(ver);
		//if(num >= 4.4){
		//    el.style.paddingTop = '25px';
		//    statusBarfixHeight = 25;
		//}
	}
};