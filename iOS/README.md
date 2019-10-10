/*
Title: agoraRtc
Description: Agora RTC SDK for APICloud
*/

<p style="color: #ccc; margin-bottom: 30px;">来自于：Agora</p>

<ul id="tab" class="clearfix">
	<li class="active"><a href="#method-content">Method</a></li>
</ul>
<div id="method-content">

<div class="outline">
[init](#1)

[destroy](#2)

[getSdkVersion](#3)

[setParameters](#4)

[setLogFile](#5)

[setLogFilter](#6)

[joinChannel](#7)

[leaveChannel](#8)

[setChannelProfile](#9)

[setClientRole](#10)

[renewToken](#11)

[enableVideo](#12)

[disableVideo](#13)

[enableLocalVideo](#14)

[setVideoProfile](#15)

[switchCamera](#16)

[startPreview](#17)

[stopPreview](#18)

[setupLocalVideo](#19)

[setupRemoteVideo](#20)

[muteLocalVideoStream](#21)

[muteAllRemoteVideoStreams](#22)

[muteRemoteVideoStream](#23)

[enableAudio](#24)

[disableAudio](#25)

[pauseAudio](#26)

[resumeAudio](#27)

[muteLocalAudioStream](#28)

[muteAllRemoteAudioStreams](#29)

[muteRemoteAudioStream](#30)

[joinChannelSuccessListener](#31)

[leaveChannelListener](#32)

[firstLocalVideoFrameListener](#33)

[remoteUserJoinedListener](#34)

[remoteUserOfflineListener](#35)

[firstRemoteVideoDecodedListener](#36)

[requestTokenListener](#37)

[errorListener](#38)

[warningListener](#39)

</div>

#**概述**

Agora RTC SDK，是 Agora 采用自主研发的音视频编解码，为实时互动通信及直播类应用量身打造而成的 SDK。 依托 Agora 全球部署的 SD-RTN 及端到端技术，Agora RTC SDK 具有高品质、低延迟、抗丢包等特点。

###使用 agoraRtc 基本流程说明：

1.在 Agora 网站（ https://www.agora.io ）注册帐号，并创建应用，获取 App ID

2.在 [config.xml](/APICloud/技术专题/app-config-manual) 中配置 agoraRtc feature，填写 appId 参数

3.前端调用 agoraRtc 模块方法，初始化和加入频道。

**建议使用此模块之前先配置 config.xml 文件的 Feature，方法如下**

	名称：agoraRtc
	参数：appId
	描述：配置 agoraRtc 应用信息
```js
	<feature name="agoraRtc">
		<param name="appId" value="123456789" />
	</feature>
```


#**init**<div id="1"></div>

初始化 Agora RTC 服务

init({params})

##params

appId：

- 类型：字符串
- 默认值：无
- 描述：（可选项）Agora 为应用程序开发者签发的 App ID, 不设置时使用 config.xml 中的配置

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.init({appId:'appId'});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**destroy**<div id="2"></div>

该方法释放 Agora SDK 使用的所有资源。有些应用程序只在用户需要时才进行语音通话，不需要时则将资源释放出来用于其他操作，该方法对这类程序可能比较有用。 只要调用了 destroy(), 用户将无法再使用和回调该 SDK 内的其它方法。如需再次使用通信功能，必须重新初始化。 

destroy()

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.destroy();
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**getSdkVersion**<div id="3"></div>

该方法返回 SDK 版本号字符串

getSdkVersion(callback(ret))

##callback(ret)

ret：

- 类型：字符串
- 描述：Agora RTC SDK 版本号

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.getSdkVersion(function(ret){
    api.alert({msg:'Agora RTC SDK Version: ' + ret});
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setParameters**<div id="4"></div>

设置特有参数

setParameters({params}, callback(ret))

##params

params：

- 类型：字符串
- 默认值：无
- 描述：将特有参数组装为 JSON 字符串

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.setParameters({
    params:''
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setLogFile**<div id="5"></div>

设置 SDK 的输出 log 文件。SDK 运行时产生的所有 log 将写入该文件。应用程序必须保证指定的目录存在而且可写。

setLogFile({params}, callback(ret))

##params

path：

- 类型：字符串
- 默认值：无
- 描述：将特有参数组装为 JSON 字符串

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.setLogFile({
    path:'cache://agoraRtc.log'
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setLogFilter**<div id="6"></div>

设置 SDK 的输出日志过滤器。不同的过滤器可以用或组合。

setLogFilter({params}, callback(ret))

##params

filter：

- 类型：数字
- 默认值：0x000f，INFO | WARNING | ERROR | FATAL
- 取值范围：
    * 1: INFO
    * 2: WARNING
    * 4: ERROR
    * 8: FATAL
    * 0x800: DEBUG

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.setLogFilter({
    filter:0x080f
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**joinChannel**<div id="7"></div>

该方法让用户加入通话频道，在同一个频道内的用户可以互相通话，多个用户加入同一个频道，可以群聊。 使用不同 App ID 的应用程序是不能互通的。如果已在通话中，用户必须调用 leaveChannel() 退出当前通话，才能进入下一个频道。 

joinChannel({params}, callback(ret))

##params

token：

- 类型：字符串
- 默认值：空
- 描述：（可选项）
    * 安全要求不高: 将值设为 null
    * 安全要求高: 将值设置为 Token 值。 如果你已经启用了 App Certificate, 请务必使用 Token。 

channel：

- 类型：字符串
- 默认值：无
- 描述：标识通话的频道名，长度在64字节以内的字符串。以下为支持的字符集范围（共89个字符）: a-z,A-Z,0-9,space,! #$%&,()+, -,:;<=.#$%&,()+,-,:;<=.,>?@[],^_,{|},~

uid：

- 类型：数字
- 默认值：0
- 描述：（可选项）用户ID，32位无符号整数。建议设置范围：1到(2^32-1)，并保证唯一性。如果不指定（即设为0），SDK 会自动分配一个，并在 joinChannelSuccessListener 回调方法中返回。

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.joinChannel({
    channel:'test'
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**leaveChannel**<div id="8"></div>

离开频道，即挂断或退出通话。当调用 joinChannel() API 方法后，必须调用 leaveChannel() 结束通话，否则无法开始下一次通话。不管当前是否在通话中，都可以调用 leaveChannel()，没有副作用。该方法会把会话相关的所有资源释放掉。该方法是异步操作，调用返回时并没有真正退出频道。在真正退出频道后，SDK 会触发 leaveChannelListener 回调。

leaveChannel(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.leaveChannel(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setChannelProfile**<div id="9"></div>

该方法用于设置频道模式(Profile)。Agora RtcEngine 需知道应用程序的使用场景(例如通信模式或直播模式), 从而使用不同的优化手段。

setChannelProfile({params}, callback(ret))

##params

profile：

- 类型：数字
- 默认值：0, Communication
- 取值范围：
    * 0: Communication, 用于常见的一对一或群聊，频道中的任何用户可以自由说话
    * 1: LiveBroadcasting, 直播模式有主播和观众两种用户角色，可以通过调用 setClientRole 设置。主播可收发语音和视频，但观众只能收，不能发
    * 2: Game, 频道内任何用户可自由发言。该模式下默认使用低功耗低码率的编解码器

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc. setChannelProfile({
    profile: 1
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setClientRole**<div id="10"></div>

直播模式下在加入频道前，用户需要通过本方法设置观众(默认)或主播模式。在加入频道后，用户可以通过本方法切换用户模式。

setClientRole({params}, callback(ret))

##params

role：

- 类型：数字
- 默认值：2, 观众
- 取值范围：
    * 1: 主播
    * 2: 观众

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc. setClientRole({
    role: 1
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**renewToken**<div id="11"></div>

该方法用于更新 Token。如果启用了 Token 机制，过一段时间后使用的 Token 会失效。 当 errorListener 回调报告 ERR_TOKEN_EXPIRED(109) ，或收到 requestTokenListener 回调时，应用程序应重新获取 Token，然后调用该 API 更新 Token，否则 SDK 无法和服务器建立连接。

renewToken({params}, callback(ret))

##params

token：

- 类型：字符串
- 默认值：无
- 描述：更新的 Token

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.renewToken({
    token:''
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**enableVideo**<div id="12"></div>

该方法用于打开视频模式。可以在加入频道前或者通话中调用，在加入频道前调用，则自动开启视频模式，在通话中调用则由音频模式切换为视频模式。调用 disableVideo() 方法可关闭视频模式。

enableVideo(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.enableVideo(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**disableVideo**<div id="13"></div>

该方法用于关闭视频。可以在加入频道前或者通话中调用，在加入频道前调用，则自动开启纯音频模式，在通话中调用则由视频模式切换为纯音频频模式。 调用 enableVideo() 方法可开启视频模式。

disableVideo(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.disableVideo(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**enableLocalVideo**<div id="14"></div>

该方法用于打开或关闭本地视频采集和渲染。

enableLocalVideo({params}, callback(ret))

##params

enabled：

- 类型：布尔值
- 默认值：true
- 描述：是否打开本地视频

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.enableLocalVideo({
    enabled:true
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setVideoProfile**<div id="15"></div>

该方法设置视频编码属性（Profile），如分辨率、帧率、码率等。 当设备的摄像头不支持指定的分辨率时，SDK 会自动选择一个合适的摄像头分辨率，但是编码分辨率仍然用 setVideoProfile() 指定的。

setVideoProfile({params}, callback(ret))

##params

width：

- 类型：数字
- 默认值：无
- 描述：视频分辨率宽度

height：

- 类型：数字
- 默认值：无
- 描述：视频分辨率高度

frameRate：

- 类型：数字
- 默认值：无
- 描述：视频帧率

bitrate：

- 类型：数字
- 默认值：无
- 描述：视频码率

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.setVideoProfile({
    width: 360,
    height: 360,
    frameRate: 15,
    bitrate: 800
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**switchCamera**<div id="16"></div>

该方法用于在前置/后置摄像头间切换。

switchCamera(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.switchCamera(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**startPreview**<div id="17"></div>

该方法用于启动本地视频预览。在开启预览前，必须先调用 setupLocalVideo() 设置预览窗口及属性，且必须调用 enableVideo() 开启视频功能。 如果在调用 joinChannel() 进入频道之前调用了 startPreview() 启动本地视频预览，在调用 leaveChannel() 退出频道之后本地预览仍然处于启动状态，如需要关闭本地预览，需要调用 stopPreview()。

startPreview(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.startPreview(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**stopPreview**<div id="18"></div>

该方法用于停止本地视频预览。

stopPreview(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.stopPreview(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setupLocalVideo**<div id="19"></div>

该方法设置本地视频显示信息。应用程序通过调用此接口绑定本地视频流的显示视窗，并设置视频显示模式。在应用程序开发中，通常在初始化后调用该方法进行本地视频设置，然后再加入频道。退出频道后，绑定仍然有效，如果需要解除绑定，可以不设置 rect 调用 setupLocalVideo() 。

setupLocalVideo({params}, callback(ret))

##params

rect：

- 类型：JSON 对象
- 默认值：无
- 描述：（可选项）视频区域的位置及尺寸
- 内部字段：

```js
{
    x: 0, //（可选项）数字类型；视频区域左上角的 x 坐标（相对于所属的 Window 或 Frame）；默认：0
    y: 0, //（可选项）数字类型；视频区域左上角的 y 坐标（相对于所属的 Window 或 Frame）；默认：0
    w: 360, // 数字类型；视频区域的宽度
    h: 640 // 数字类型；视频区域的高度
}
```

fixedOn：

- 类型：字符串
- 默认值：模块依附于当前 window
- 描述：（可选项）模块视图添加到指定 frame 的名字（只指 frame，传 window 无效）

fixed：

- 类型：布尔值
- 默认值：true （不随之滚动）
- 描述：（可选项）模块是否随所属 window 或 frame 滚动

renderMode：

- 类型：数字
- 默认值：1
- 描述：（可选项）视频显示模式
- 取值范围：
    * 1: Hidden, 如果视频尺寸与显示视窗尺寸不一致，则视频流会按照显示视窗的比例进行周边裁剪或图像拉伸后填满视窗。
    * 2: Fit, 如果视频尺寸与显示视窗尺寸不一致，在保持长宽比的前提下，将视频进行缩放后填满视窗。
    * 3: Adaptive, 如果自己和对方都是竖屏，或者如果自己和对方都是横屏使用 Hidden；如果对方和自己一个竖屏一个横屏，则使用 Fit。

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.setupLocalVideo({
    rect:{ x: 0, y: 0, w: 360, h: 640 },
    fixed: false,
    renderMode: 1
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**setupRemoteVideo**<div id="20"></div>

该方法绑定远程用户和显示视图，即设定 uid 指定的用户用哪个视图显示。调用该接口时需要指定远程视频的 uid，一般可以在进频道前提前设置好。

如果应用程序不能事先知道对方的 uid，可以在 APP 收到 remoteUserJoinedListener 事件时设置。如果启用了视频录制功能，视频录制服务会做为一个哑客户端加入频道，因此其他客户端也会收到它的 remoteUserJoinedListener 事件，APP 不应给它绑定视图（因为它不会发送视频流），如果 APP 不能识别哑客户端，可以在 firstRemoteVideoDecodedListener 事件时再绑定视图。解除某个用户的绑定视图可以不设置 rect。退出频道后，SDK 会把远程用户的绑定关系清除掉。

setupRemoteVideo({params}, callback(ret))

##params

uid：

- 类型：数字
- 默认值：无
- 描述：远程用户ID，32位无符号整数。

rect：

- 类型：JSON 对象
- 默认值：无
- 描述：（可选项）视频区域的位置及尺寸
- 内部字段：

```js
{
    x: 0, //（可选项）数字类型；视频区域左上角的 x 坐标（相对于所属的 Window 或 Frame）；默认：0
    y: 0, //（可选项）数字类型；视频区域左上角的 y 坐标（相对于所属的 Window 或 Frame）；默认：0
    w: 360, // 数字类型；视频区域的宽度
    h: 640 // 数字类型；视频区域的高度
}
```

fixedOn：

- 类型：字符串
- 默认值：模块依附于当前 window
- 描述：（可选项）模块视图添加到指定 frame 的名字（只指 frame，传 window 无效）

fixed：

- 类型：布尔值
- 默认值：true （不随之滚动）
- 描述：（可选项）模块是否随所属 window 或 frame 滚动

renderMode：

- 类型：数字
- 默认值：1
- 描述：（可选项）视频显示模式
- 取值范围：
    * 1: Hidden, 如果视频尺寸与显示视窗尺寸不一致，则视频流会按照显示视窗的比例进行周边裁剪或图像拉伸后填满视窗。
    * 2: Fit, 如果视频尺寸与显示视窗尺寸不一致，在保持长宽比的前提下，将视频进行缩放后填满视窗。
    * 3: Adaptive, 如果自己和对方都是竖屏，或者如果自己和对方都是横屏使用 Hidden；如果对方和自己一个竖屏一个横屏，则使用 Fit。

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.setupRemoteVideo({
    uid: 1
    rect:{ x: 0, y: 0, w: 360, h: 640 },
    fixed: false,
    renderMode: 1
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**muteLocalVideoStream**<div id="21"></div>

暂停/恢复发送本地视频流。注意：该方法不影响本地视频流获取，没有禁用摄像头。

muteLocalVideoStream({params}, callback(ret))

##params

mute：

- 类型：布尔值
- 默认值：true
- 取值范围：
    * true: 不发送本地视频流。
    * false: 发送本地视频流

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.muteLocalVideoStream({
    mute: true
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**muteAllRemoteVideoStreams**<div id="22"></div>

暂停/恢复播放所有人的视频流。

muteAllRemoteVideoStreams({params}, callback(ret))

##params

mute：

- 类型：布尔值
- 默认值：true
- 取值范围：
    * true: 停止接收和播放所有远端视频流
    * false: 允许接收和播放所有远端视频流

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.muteAllRemoteVideoStreams({
    mute: true
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**muteRemoteVideoStream**<div id="23"></div>

暂停/恢复播放指定的远端视频流。如果之前有调用过 muteAllRemoteVideoStreams(true) 对所有远端视频进行静音，在调用本 API 之前请确保你已调用 muteAllRemoteVideoStreams(false) 。 muteAllRemoteVideoStreams 是全局控制，muteRemoteVideoStream 是精细控制。

muteRemoteVideoStream({params}, callback(ret))

##params

uid：

- 类型：数字
- 默认值：无
- 描述：远程用户ID，32位无符号整数。

mute：

- 类型：布尔值
- 默认值：true
- 取值范围：
    * true: 停止接收和播放指定用户的视频流
    * false: 允许接收和播放指定用户的视频流

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.muteRemoteVideoStream({
    uid: 1
    mute: true
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**enableAudio**<div id="24"></div>

打开音频(默认为开启状态)。

enableAudio(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.enableAudio(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**disableAudio**<div id="25"></div>

关闭音频。

disableAudio(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.disableAudio(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**pauseAudio**<div id="26"></div>

暂停频道内音频。

pauseAudio(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.pauseAudio(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**resumeAudio**<div id="27"></div>

恢复频道内音频。

resumeAudio(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.resumeAudio(function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**muteLocalAudioStream**<div id="28"></div>

静音/取消静音。该方法用于允许/禁止往网络发送本地音频流。

muteLocalAudioStream({params}, callback(ret))

##params

mute：

- 类型：布尔值
- 默认值：true
- 取值范围：
    * true: 麦克风静音
    * false: 取消静音

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.muteLocalAudioStream({
    mute:true
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**muteAllRemoteAudioStreams**<div id="29"></div>

暂停/恢复播放远端用户的音频流，即对所有远端用户进行静音与否。

muteAllRemoteAudioStreams({params}, callback(ret))

##params

mute：

- 类型：布尔值
- 默认值：true
- 取值范围：
    * true: 停止接收和播放所有远端音频流
    * false: 允许接收和播放所有远端音频流

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.muteAllRemoteAudioStreams({
    mute: 1
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**muteRemoteAudioStream**<div id="30"></div>

静音/取消静音指定远端用户。如果之前有调用过 muteAllRemoteAudioStreams(true) 对所有远端音频进行静音，在调用本 API 之前请确保你已调用 muteAllRemoteAudioStreams(false) 。 muteAllRemoteAudioStreams 是全局控制，muteRemoteAudioStream 是精细控制。

muteRemoteAudioStream({params}, callback(ret))

##params

uid：

- 类型：数字
- 默认值：无
- 描述：远程用户ID，32位无符号整数。

mute：

- 类型：布尔值
- 默认值：true
- 取值范围：
    * true: 停止接收和播放指定用户的音频流
    * false: 允许接收和播放指定用户的音频流

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	code: 0 // 返回的状态码，0为调用成功，否则为调用失败
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.muteRemoteAudioStream({
    uid: 1,
    mute: true
}, function(ret) {
	if (ret.code == 0) {
		// success
	}
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**joinChannelSuccessListener**<div id="31"></div>

该回调方法表示该客户端成功加入了指定的频道。

joinChannelSuccessListener(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	channel: 'test' // 字符串类型；频道名
	uid: 1 // 数字类型；用户 ID。如果 joinChannel 时指定了 uid，则此处返回该 ID；否则使用 Agora 服务器自动分配的 ID
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.joinChannelSuccessListener(function(ret) {
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**leaveChannelListener**<div id="32"></div>

当用户调用 leaveChannel 离开频道后，SDK 会触发该回调。

leaveChannelListener(callback())

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.leaveChannelListener(function() {
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**firstLocalVideoFrameListener**<div id="33"></div>

提示第一帧本地视频画面已经显示在屏幕上。

firstLocalVideoFrameListener(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	width: 360 // 数字类型；视频宽度。
	height: 640 // 数字类型；视频高度。
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.firstLocalVideoFrameListener(function(ret) {
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**remoteUserJoinedListener**<div id="34"></div>

提示有用户加入了频道。如果该客户端加入频道时已经有用户在频道中，SDK也会向应用程序上报这些已在频道中的用户。

remoteUserJoinedListener(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	uid: 1 // 数字类型；远程用户 ID。
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.remoteUserJoinedListener(function(ret) {
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**remoteUserOfflineListener**<div id="35"></div>

提示有用户离开了频道（或掉线）。SDK 判断用户离开频道（或掉线）的依据是超时: 在一定时间内（15 秒）没有收到对方的任何数据包，判定为对方掉线。 在网络较差的情况下，可能会有误报。建议可靠的掉线检测应该由信令来做。

remoteUserOfflineListener(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	uid: 1 // 数字类型；远程用户 ID。
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.remoteUserOfflineListener(function(ret) {
    //agoraRtc.setupRemoteVideo({uid:ret.uid});
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**firstRemoteVideoDecodedListener**<div id="36"></div>

提示已收到第一帧远程视频流并解码。

firstRemoteVideoDecodedListener(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	uid: 1 // 数字类型；远程用户 ID。
	width: 360 // 数字类型；视频宽度。
	height: 640 // 数字类型；视频高度。
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.firstRemoteVideoDecodedListener(function(ret) {
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**requestTokenListener**<div id="37"></div>

在调用 joinChannel 时如果指定了 Token，由于 Token 具有一定的时效，在通话过程中SDK可能由于网络原因和服务器失去连接，重连时可能需要新的 Token。 该回调通知APP需要生成新的 Token，并需调用 renewToken() 为 SDK 指定新的 Token。

requestTokenListener(callback())

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.requestTokenListener(function() {
    //agoraRtc.renewToken({token:'new token'});
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**errorListener**<div id="38"></div>

该回调方法表示 SDK 运行时出现了（网络或媒体相关的）错误。通常情况下，SDK 上报的错误意味着 SDK 无法自动恢复，需要应用程序干预或提示用户。比如启动通话失败时，SDK 会上报 StartCall(1002) 错误，应用程序可以提示用户启动通话失败，并调用 leaveChannel() 退出频道。

errorListener(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	errorCode: 0 // 数字类型；错误代码
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.errorListener(function(ret) {
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本

#**warningListener**<div id="39"></div>

该回调方法表示 SDK 运行时出现了（网络或媒体相关的）警告。通常情况下，SDK 上报的警告信息应用程序可以忽略，SDK 会自动恢复。比如和服务器失去连接时，SDK 可能会上报 OpenChannelTimeout(106) 警告，同时自动尝试重连。

warningListener(callback(ret))

##callback(ret)

ret：

- 类型：JSON 对象

内部字段：

```js
{
	warningCode: 0 // 数字类型；警告代码
}
```

##示例代码

```js
var agoraRtc = api.require('agoraRtc');
agoraRtc.warningListener(function(ret) {
});
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本

</div>

<div id="const-content">

<div class="outline">
[错误码](#c1)

[警告码](#c2)
</div>

#**错误码**<div id="c1"></div>

错误代码，数字类型

##取值范围

```js
1           // ERR_FAILED，一般性的错误（没有明确归类的错误原因）
2           // ERR_INVALID_ARGUMENT，API 调用了无效的参数。例如指定的频道名含有非法字符
3           // ERR_NOT_READY，RTC 初始化失败。处理方法：检查音频设备状态，检查代码完整性，尝试重新初始化
4           // ERR_NOT_SUPPORTED，RTC 当前状态禁止此操作，因此不能进行此操作
6           // ERR_BUFFER_TOO_SMALL，传入的缓冲区大小不足以存放返回的数据
7           // ERR_NOT_INITIALIZED，SDK 尚未初始化，就调用其 API
10          // ERR_TIMEDOUT，API 调用超时。有些 API 调用需要 SDK 返回结果，如果 SDK 处理事件过程，会出现此错误
17          // ERR_JOIN_CHANNEL_REJECTED，加入频道被拒绝。一般有以下原因：
            // 用户已进入频道，再次调用加入频道的 API，例如 joinChannel，会返回此错误。停止调用该 API 即可
            // 用户在做 Echo 测试时尝试加入频道。等待 Echo test 结束后再加入频道即可
18          // ERR_LEAVE_CHANNEL_REJECTED，离开频道失败。一般有以下原因：
            // 用户已离开频道，再次调用退出频道的 API，例如 leaveChannel，会返回此错误。停止调用该 API 即可
            // 用户尚未加入频道，就调用退出频道的 API。这种情况下无需额外操作
19          // ERR_ALREADY_IN_USE，资源已被占用，不能重复使用
21          // ERR_INIT_NET_ENGINE，Windows 下特定的防火墙设置导致 SDK 初始化失败然后崩溃
101         // ERR_INVALID_VENDOR_KEY，不是有效的 App ID。请更换有效的 App ID 重新加入频道
102         // ERR_INVALID_CHANNEL_NAME，不是有效的频道名。请更换有效的频道名重新加入频道
109         // ERR_CHANNEL_KEY_EXPIRED，当前使用的 Token 过期，不再有效。一般有以下原因：
            // Token 授权时间戳无效：Token 授权时间戳为 Token 生成时的时间戳，自 1970 年 1 月 1 日开始到当前时间的描述。授权该 Token 在生成后的 24 小时内可以访问 Agora 服务。如果 24 小时内没有访问，则该 Token 无法再使用。需要重新在服务端申请生成 Token
            // Token 服务到期时间戳已过期：用户设置的服务到期时间戳小于当前时间戳，无法继续使用 Agora 服务（比如正在进行的通话会被强制终止）；设置服务到期时间并不意味着 Token 失效，而仅仅用于限制用户使用当前服务的时间。需要重新在服务端申请生成 Token
110         // ERR_INVALID_CHANNEL_KEY，生成的 Token 无效，一般有以下原因：
            // 用户在 Dashboard 上启用了 App Certificate，但仍旧在代码里仅使用了 App ID。当启用了 App Certificate，必须使用 Token
            //字段 uid 为生成 Token 的必须字段，用户在调用 joinChannel 加入频道时必须设置相同的 uid
113         // ERR_NOT_IN_CHANNEL，用户不在频道内
114         // ERR_SIZE_TOO_LARGE，数据太大
115         // ERR_BITRATE_LIMIT，码率受限
116         // ERR_TOO_MANY_DATA_STREAMS，数据流/通道过多
117         // ERR_STREAM_MESSAGE_TIMEOUT，数据流发送超时
119         // ERR_SET_CLIENT_ROLE_NOT_AUTHORIZED，切换角色失败。请尝试重新加入频道
120         // ERR_DECRYPTION_FAILED，解密失败，可能是用户加入频道用了不同的密码。请检查加入频道时的设置，或尝试重新加入频道
123         // ERR_CLIENT_IS_BANNED_BY_SERVER，此用户被服务器禁止
124         // ERR_WATERMARK_PARAM，水印文件参数错误
125         // ERR_WATERMARK_PATH，水印文件路径错误
126         // ERR_WATERMARK_PNG，水印文件格式错误
127         // ERR_WATERMARKR_INFO，水印文件信息错误
128         // ERR_WATERMARK_ARGB，水印文件数据格式错误
129         // ERR_WATERMARK_READ，水印文件读取错误
1001        // ERR_LOAD_MEDIA_ENGINE，加载媒体引擎失败
1002        // ERR_START_CALL，启动媒体引擎开始通话失败。请尝试重新进入频道
1003        // ERR_START_CAMERA，启动摄像头失败，请检查摄像头是否被其他应用占用，或者尝试重新进入频道
1004        // ERR_START_VIDEO_RENDER，启动视频渲染模块失败
1005        // ERR_ADM_GENERAL_ERROR，音频设备模块：音频初始化失败。请检查音频设备是否被其他应用占用，或者尝试重新进入频道
1006        // ERR_ADM_JAVA_RESOURCE，音频设备模块：使用 java 资源出现错误
1007        // ERR_ADM_SAMPLE_RATE，音频设备模块：设置的采样频率出现错误
1008        // ERR_ADM_INIT_PLAYOUT，音频设备模块：初始化播放设备出现错误。请检查播放设备是否被其他应用占用，或者尝试重新进入频道
1009        // ERR_ADM_START_PLAYOUT，音频设备模块：启动播放设备出现错误。请检查播放设备是否正常，或者尝试重新进入频道
1010        // ERR_ADM_STOP_PLAYOUT，音频设备模块：停止播放设备出现错误
1011        // ERR_ADM_INIT_RECORDING，音频设备模块：初始化录音设备时出现错误。请检查录音设备是否正常，或者尝试重新进入频道
1012        // ERR_ADM_START_RECORDING，音频设备模块：启动录音设备出现错误。请检查录音设备是否正常，或者尝试重新进入频道
1013        // ERR_ADM_STOP_RECORDING，音频设备模块：停止录音设备出现错误
1015        // ERR_ADM_RUNTIME_PLAYOUT_ERROR，音频设备模块：运行时播放出现错误。请检查录音设备是否正常，或者尝试重新进入频道
1017        // ERR_ADM_RUNTIME_RECORDING_ERROR，音频设备模块：运行时录音错误。请检查录音设备是否正常，或者尝试重新进入频道
1018        // ERR_ADM_RECORD_AUDIO_FAILED，音频设备模块：录音失败
1022        // ERR_ADM_INIT_LOOPBACK，音频设备模块：初始化 Loopback 设备错误
1023        // ERR_ADM_START_LOOPBACK，音频设备模块：启动 Loopback 设备错误
1027        // ERR_ADM_NO_PERMISSION，音频设备模块：没有录音权限。请检查是否已经打开权限允许录音
1359        // ERR_ADM_NO_RECORDING_DEVICE，音频设备模块：无录制设备。请检查是否有可用的录放音设备或者录放音设备是否已经被其他应用占用
1360        // ERR_ADM_NO_PLAYOUT_DEVICE，音频设备模块：无播放设备
1501        // ERR_VDM_CAMERA_NOT_AUTHORIZED，视频设备模块：没有摄像头使用权限。请检查是否已经打开摄像头权限
1600        // ERR_VCM_UNKNOWN_ERROR，视频设备模块：未知错误
1601        // ERR_VCM_ENCODER_INIT_ERROR，视频设备模块：视频 Codec 初始化错误。该错误为严重错误，请尝试重新加入频道
1602        // ERR_VCM_ENCODER_ENCODE_ERROR，视频设备模块：视频 Codec 错误。该错误为严重错误，请尝试重新加入频道
1603        // ERR_VCM_ENCODER_SET_ERROR，视频设备模块：视频 Codec 设置错误
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本


#**警告码**<div id="c2"></div>

警告代码，数字类型

warningListener()  方法返回的警告类型

##取值范围

```js
8           // WARN_INVALID_VIEW，指定的 view 无效，使用视频功能时需要指定 view，如果 view 尚未指定，则返回该警告
16          // WARN_INIT_VIDEO，初始化视频功能失败
20          // WARN_PENDING，请求处于待定状态。一般是由于某个模块还没准备好，请求被延迟处理
103         // WARN_NO_AVAILABLE_CHANNEL，没有可用的频道资源。可能是因为服务端没法分配频道资源
104         // WARN_LOOKUP_CHANNEL_TIMEOUT，查找频道超时。在加入频道时 SDK 先要查找指定的频道，出现该警告一般是因为网络太差，连接不到服务器
105         // WARN_LOOKUP_CHANNEL_REJECTED，查找频道请求被服务器拒绝。服务器可能没有办法处理这个请求或请求是非法的
106         // WARN_OPEN_CHANNEL_TIMEOUT，打开频道超时。查找到指定频道后，SDK 接着打开该频道，超时一般是因为网络太差，连接不到服务器
107         // WARN_OPEN_CHANNEL_REJECTED，打开频道请求被服务器拒绝。服务器可能没有办法处理该请求或该请求是非法的
111         // WARN_SWITCH_LIVE_VIDEO_TIMEOUT，切换直播视频超时
118         // WARN_SET_CLIENT_ROLE_TIMEOUT，直播模式下设置用户模式超时
119         // WARN_SET_CLIENT_ROLE_NOT_AUTHORIZED，直播模式下用户模式未授权
121         // WARN_OPEN_CHANNEL_INVALID_TICKET，TICKET 非法，打开频道失败
122         // WARN_OPEN_CHANNEL_TRY_NEXT_VOS，尝试打开另一个服务器
701         // WARN_AUDIO_MIXING_OPEN_ERROR，打开伴奏出错
1014        // WARN_ADM_RUNTIME_PLAYOUT_WARNING，音频设备模块：运行时播放设备出现警告
1016        // WARN_ADM_RUNTIME_RECORDING_WARNING，音频设备模块：运行时录音设备出现警告
1019        // WARN_ADM_RECORD_AUDIO_SILENCE，音频设备模块：没有采集到有效的声音数据
1031        // WARN_ADM_RECORD_AUDIO_LOWLEVEL，音频设备模块：录到的声音太低
1032        // WARN_ADM_PLAYOUT_AUDIO_LOWLEVEL，音频设备模块：播放的声音太低
1033        // WARN_ADM_RECORD_IS_OCCUPIED，音频设备模块：录制设备被占用
1051        // WARN_APM_HOWLING，音频设备模块：录音声音异常
1052        // WARN_ADM_GLITCH_STATE，音频设备模块：音频播放会卡顿
1053        // WARN_ADM_IMPROPER_SETTINGS，音频设备模块：音频底层设置被修改
```

##可用性

iOS 系统，Android 系统

可提供的 2.9.1 及更高版本

</div>
