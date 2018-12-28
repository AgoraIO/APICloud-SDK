package com.apicloud.agora.rtc;

import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;

import io.agora.rtc.video.VideoEncoderConfiguration; // 2.3.0 and later

/**
 * Javascript for Agora RTC SDK <br>
 * var module = api.require('agora-rtc');<br>
 * module.xxx();
 */
public class AgoraRTCModule extends UZModule {

    private UZModuleContext mJsCallbackWarning;
    private UZModuleContext mJsCallbackError;
    private UZModuleContext mJsCallbackRequestToken;
    private UZModuleContext mJsCallbackJoinChannel;
    private UZModuleContext mJsCallbackLeaveChannel;
    private UZModuleContext mJsCallbackFirstLocalVideoFrame;
    private UZModuleContext mJsCallbackFirstRemoteVideoDecode;
    private UZModuleContext mJsCallbackUserJoin;
    private UZModuleContext mJsCallbackUserLeave;

    private final HashMap<Integer, SurfaceView> mUidsList = new HashMap<>();
    private SurfaceView mLocalSurfaceView;

    public AgoraRTCModule(UZWebView webView) {
        super(webView);
    }

    private RtcEngine mRtcEngine;

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {

        @Override
        public void onWarning(final int warn) {
            super.onWarning(warn);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackWarning != null) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("warningCode", warn);
                            sendJsonCallbackToJs(mJsCallbackWarning, json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }

        @Override
        public void onError(final int err) {
            super.onError(err);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackError != null) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("errorCode", err);
                            sendJsonCallbackToJs(mJsCallbackError, json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        @Override
        public void onRequestToken() {
            super.onRequestToken();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackRequestToken != null) {
                        JSONObject json = new JSONObject();
                        sendJsonCallbackToJs(mJsCallbackRequestToken, json);
                    }
                }
            });
        }

        @Override
        public void onJoinChannelSuccess(final String channel, final int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackJoinChannel != null) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("channel", channel);
                            json.put("uid", uid);
                            sendJsonCallbackToJs(mJsCallbackJoinChannel, json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        @Override
        public void onLeaveChannel(RtcStats stats) {
            super.onLeaveChannel(stats);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackLeaveChannel != null) {
                        JSONObject json = new JSONObject();
                        sendJsonCallbackToJs(mJsCallbackLeaveChannel, json);

                        SurfaceView surfaceView = null;
                        Iterator iterator = mUidsList.entrySet().iterator();
                        while (iterator.hasNext()) {
                            HashMap.Entry entry = (HashMap.Entry) iterator.next();

                            surfaceView = (SurfaceView) entry.getValue();
                            removeViewFromCurWindow(surfaceView);
                            mRtcEngine.setupRemoteVideo(new VideoCanvas(null, VideoCanvas.RENDER_MODE_HIDDEN, (Integer) entry.getKey()));


                        }
                        mUidsList.clear();
                    }
                }
            });

        }

        @Override
        public void onFirstLocalVideoFrame(final int width, final int height, int elapsed) {
            super.onFirstLocalVideoFrame(width, height, elapsed);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackFirstLocalVideoFrame != null) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("width", width);
                            json.put("height", height);
                            sendJsonCallbackToJs(mJsCallbackFirstLocalVideoFrame, json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }

        @Override
        public void onFirstRemoteVideoDecoded(final int uid, final int width, final int height, int elapsed) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackFirstRemoteVideoDecode != null) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("uid", uid);
                            json.put("width", width);
                            json.put("height", height);
                            sendJsonCallbackToJs(mJsCallbackFirstRemoteVideoDecode, json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }

        @Override
        public void onUserJoined(final int uid, int elapsed) {
            super.onUserJoined(uid, elapsed);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mJsCallbackUserJoin != null) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("uid", uid);
                            sendJsonCallbackToJs(mJsCallbackUserJoin, json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }

        @Override
        public void onUserOffline(final int uid, int reason) { // Tutorial Step 7
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (mJsCallbackUserLeave != null) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("uid", uid);
                            sendJsonCallbackToJs(mJsCallbackUserLeave, json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }

    };

    public void jsmethod_init(final UZModuleContext moduleContext) {
        if (mRtcEngine == null) {
            try {
                mRtcEngine = RtcEngine.create(getContext(), moduleContext.optString("appId"), mRtcEventHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void jsmethod_destroy(final UZModuleContext moduleContext) {

        SurfaceView surfaceView = null;
        Iterator iterator = mUidsList.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iterator.next();

            surfaceView = (SurfaceView) entry.getValue();
            removeViewFromCurWindow(surfaceView);
        }
        if (mLocalSurfaceView != null) {
            removeViewFromCurWindow(mLocalSurfaceView);
            mLocalSurfaceView = null;
        }

        mUidsList.clear();

        RtcEngine.destroy();
        mRtcEngine = null;
    }

    public void jsmethod_getSdkVersion(final UZModuleContext moduleContext) {

        moduleContext.success(RtcEngine.getSdkVersion(), false, true);

    }

    public void jsmethod_setParameters(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.setParameters(moduleContext.optString("params")));
        }
    }

    public void jsmethod_setLogFile(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.setLogFile(moduleContext.optString("path")));
        }
    }

    public void jsmethod_setLogFilter(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.setLogFilter(moduleContext.optInt("filter")));
        }
    }

    public void jsmethod_joinChannel(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            String token = moduleContext.optString("token");
            if (token.equals("")) {
                token = null;
            }
            String channelName = moduleContext.optString("channel");
            int uid = moduleContext.optInt("uid");
            int ret = mRtcEngine.joinChannel(token, channelName, "", uid);

            sendJsonResultToJs(moduleContext, ret);
        }
    }

    public void jsmethod_leaveChannel(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.leaveChannel());
        }
    }

    public void jsmethod_setChannelProfile(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.setChannelProfile(moduleContext.optInt("profile")));
        }
    }

    public void jsmethod_setClientRole(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.setClientRole(moduleContext.optInt("role")));
        }
    }

    public void jsmethod_renewToken(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.renewToken(moduleContext.optString("token")));
        }
    }

    public void jsmethod_enableVideo(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.enableVideo());
        }
    }

    public void jsmethod_disableVideo(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.disableVideo());
        }
    }

    public void jsmethod_enableLocalVideo(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.enableLocalVideo(moduleContext.optBoolean("enabled")));
        }
    }

    public void jsmethod_setVideoProfile(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            int w = moduleContext.optInt("width");
            int h = moduleContext.optInt("height");
            int fps = moduleContext.optInt("frameRate");
            int bitrate = moduleContext.optInt("bitrate");

            VideoEncoderConfiguration.FRAME_RATE fpsEnum;

            switch (fps) {
                case 1:
                    fpsEnum = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_1;
                    break;
                case 7:
                    fpsEnum = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_7;
                    break;
                case 10:
                    fpsEnum = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_10;
                    break;
                case 15:
                    fpsEnum = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15;
                    break;
                case 24:
                    fpsEnum = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24;
                    break;
                case 30:
                    fpsEnum = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30;
                    break;
                default:
                    fpsEnum = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15;
                    break;
            }

//          int result = mRtcEngine.setVideoProfile(w, h, fps, bitrate); // Earlier than 2.3.0
            int result = mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(w, h, fpsEnum,
                    bitrate,
                    VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));

            sendJsonResultToJs(moduleContext, result);
        }
    }

    public void jsmethod_setupLocalVideo(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {

            if (mLocalSurfaceView != null) {
                removeViewFromCurWindow(mLocalSurfaceView);
                int ret = mRtcEngine.setupLocalVideo(new VideoCanvas(null, VideoCanvas.RENDER_MODE_HIDDEN, 0));
                sendJsonResultToJs(moduleContext, ret);
                mLocalSurfaceView = null;
            }

            if (!moduleContext.isNull("rect")) {

                JSONObject jsonObject = moduleContext.optJSONObject("rect");

                int x = jsonObject.optInt("x");
                int y = jsonObject.optInt("y");
                int w = jsonObject.optInt("w");
                int h = jsonObject.optInt("h");
                if (w == 0) {
                    w = ViewGroup.LayoutParams.MATCH_PARENT;
                }
                if (h == 0) {
                    h = ViewGroup.LayoutParams.MATCH_PARENT;
                }

                mLocalSurfaceView = RtcEngine.CreateRendererView(mContext);

                RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(w, h);
                rlp.leftMargin = x;
                rlp.topMargin = y;

                String fixedOn = moduleContext.optString("fixedOn");
                boolean fixed = moduleContext.optBoolean("fixed", true);
                int rendermode = moduleContext.optInt("renderMode");
                if (rendermode != VideoCanvas.RENDER_MODE_HIDDEN && rendermode != VideoCanvas.RENDER_MODE_ADAPTIVE && rendermode != VideoCanvas.RENDER_MODE_FIT) {
                    rendermode = VideoCanvas.RENDER_MODE_HIDDEN;
                }
                insertViewToCurWindow(mLocalSurfaceView, rlp, fixedOn, fixed);

                int ret = mRtcEngine.setupLocalVideo(new VideoCanvas(mLocalSurfaceView, rendermode, 0));
                sendJsonResultToJs(moduleContext, ret);

            }

        }
    }

    public void jsmethod_startPreview(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.startPreview());
        }
    }

    public void jsmethod_stopPreview(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.stopPreview());
        }
    }

    public void jsmethod_switchCamera(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.switchCamera());
        }
    }

    public void jsmethod_setupRemoteVideo(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {

            int uid = moduleContext.optInt("uid");
            SurfaceView remoteSurfaceView = null;

            if (moduleContext.isNull("rect")) {
                remoteSurfaceView = mUidsList.remove(uid);
                removeViewFromCurWindow(remoteSurfaceView);
                int ret = mRtcEngine.setupRemoteVideo(new VideoCanvas(null, VideoCanvas.RENDER_MODE_HIDDEN, uid));
                sendJsonResultToJs(moduleContext, ret);
                remoteSurfaceView = null;

            } else {
                JSONObject jsonObject = moduleContext.optJSONObject("rect");
                int x = jsonObject.optInt("x");
                int y = jsonObject.optInt("y");
                int w = jsonObject.optInt("w");
                int h = jsonObject.optInt("h");
                if (w == 0) {
                    w = ViewGroup.LayoutParams.MATCH_PARENT;
                }
                if (h == 0) {
                    h = ViewGroup.LayoutParams.MATCH_PARENT;
                }


                remoteSurfaceView = RtcEngine.CreateRendererView(mContext);

                RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(w, h);
                rlp.leftMargin = x;
                rlp.topMargin = y;
                String fixedOn = moduleContext.optString("fixedOn");
                boolean fixed = moduleContext.optBoolean("fixed", true);
                int rendermode = moduleContext.optInt("renderMode");
                if (rendermode != VideoCanvas.RENDER_MODE_HIDDEN && rendermode != VideoCanvas.RENDER_MODE_ADAPTIVE && rendermode != VideoCanvas.RENDER_MODE_FIT) {
                    rendermode = VideoCanvas.RENDER_MODE_HIDDEN;
                }
                insertViewToCurWindow(remoteSurfaceView, rlp, fixedOn, fixed);

                int ret = mRtcEngine.setupRemoteVideo(new VideoCanvas(remoteSurfaceView, rendermode, uid));
                sendJsonResultToJs(moduleContext, ret);
                mUidsList.put(uid, remoteSurfaceView);
            }

        }
    }

    public void jsmethod_adjustRecordingSignalVolume(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            int volume = moduleContext.optInt("volume");

            sendJsonResultToJs(moduleContext, mRtcEngine.adjustRecordingSignalVolume(volume));
        }
    }

    public void jsmethod_adjustPlaybackSignalVolume(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            int volume = moduleContext.optInt("volume");

            sendJsonResultToJs(moduleContext, mRtcEngine.adjustPlaybackSignalVolume(volume));
        }
    }

    public void jsmethod_adjustAudioMixingVolume(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            int volume = moduleContext.optInt("volume");

            sendJsonResultToJs(moduleContext, mRtcEngine.adjustAudioMixingVolume(volume));
        }
    }

    public void jsmethod_muteLocalVideoStream(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.muteLocalVideoStream(moduleContext.optBoolean("mute")));
        }
    }

    public void jsmethod_muteAllRemoteVideoStreams(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.muteAllRemoteVideoStreams(moduleContext.optBoolean("mute")));
        }
    }

    public void jsmethod_muteRemoteVideoStream(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.muteRemoteVideoStream(moduleContext.optInt("uid"), moduleContext.optBoolean("mute")));
        }
    }

    public void jsmethod_enableAudio(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.enableAudio());
        }
    }

    public void jsmethod_disableAudio(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.disableAudio());
        }
    }

    public void jsmethod_pauseAudio(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.pauseAudio());
        }
    }

    public void jsmethod_resumeAudio(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.resumeAudio());
        }
    }

    public void jsmethod_muteLocalAudioStream(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.muteLocalAudioStream(moduleContext.optBoolean("mute")));
        }
    }

    public void jsmethod_muteAllRemoteAudioStreams(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.muteAllRemoteAudioStreams(moduleContext.optBoolean("mute")));
        }
    }

    public void jsmethod_muteRemoteAudioStream(final UZModuleContext moduleContext) {
        if (mRtcEngine != null) {
            sendJsonResultToJs(moduleContext, mRtcEngine.muteRemoteAudioStream(moduleContext.optInt("uid"), moduleContext.optBoolean("mute")));
        }
    }

    /**
     * ------------------------ callback method -----------------------
     */

    public void jsmethod_warningListener(final UZModuleContext moduleContext) {
        mJsCallbackWarning = moduleContext;
    }

    public void jsmethod_errorListener(final UZModuleContext moduleContext) {
        mJsCallbackError = moduleContext;
    }

    public void jsmethod_requestTokenListener(final UZModuleContext moduleContext) {
        mJsCallbackRequestToken = moduleContext;
    }

    public void jsmethod_joinChannelSuccessListener(final UZModuleContext moduleContext) {
        mJsCallbackJoinChannel = moduleContext;
    }

    public void jsmethod_leaveChannelListener(final UZModuleContext moduleContext) {
        mJsCallbackLeaveChannel = moduleContext;
    }

    public void jsmethod_firstLocalVideoFrameListener(final UZModuleContext moduleContext) {
        mJsCallbackFirstLocalVideoFrame = moduleContext;
    }

    public void jsmethod_firstRemoteVideoDecodedListener(final UZModuleContext moduleContext) {
        mJsCallbackFirstRemoteVideoDecode = moduleContext;
    }

    public void jsmethod_remoteUserJoinedListener(final UZModuleContext moduleContext) {
        mJsCallbackUserJoin = moduleContext;
    }

    public void jsmethod_remoteUserOfflineListener(final UZModuleContext moduleContext) {
        mJsCallbackUserLeave = moduleContext;
    }

    private void sendJsonResultToJs(final UZModuleContext moduleContext, int status) {
        JSONObject json = new JSONObject();
        try {
            json.put("code", status);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject ret = null;
        try {
            ret = new JSONObject(json.toString());
            moduleContext.success(ret, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendJsonCallbackToJs(final UZModuleContext moduleContext, JSONObject json) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json.toString());
            moduleContext.success(jsonObject, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
