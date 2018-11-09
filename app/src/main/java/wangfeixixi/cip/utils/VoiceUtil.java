package wangfeixixi.cip.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import wangfeixixi.cip.fram.UIUtils;

public class VoiceUtil {
    private static SpeechSynthesizer mSpeechSynthesizer;

    public static void initKey(Context context) {
// 1. 获取实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(context);

//        mSpeechSynthesizer.setSpeechSynthesizerListener(new SpeechSynthesizerListener());
        String appId = "14678940";
        String appKey = "F7aZGFVk9cOQdb9X6nPw2Aog";
        String secretKey = "2wkI4xprZ8sMmxICY9iZYim704j1qy65";
        // 3. 设置appId，appKey.secretKey
        int result = mSpeechSynthesizer.setAppId(appId);
        Log.d("ceshi", "setAppid" + result);
        result = mSpeechSynthesizer.setApiKey(appKey, secretKey);
        Log.d("ceshi", "setApiKey" + result);

//        mSpeechSynthesizer.auth(TtsMode.ONLINE);  // 纯在线
        mSpeechSynthesizer.auth(TtsMode.MIX); // 离在线混合

        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0"); // 设置发声的人声音，在线生效
        mSpeechSynthesizer.initTts(TtsMode.MIX); // 初始化离在线混合模式，如果只需要在线合成功能，使用 TtsMode.ONLINE

        try {
            mSpeechSynthesizer.loadModel(copyAssetsFile("bd_etts_speech_female.dat"), copyAssetsFile("bd_etts_text.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mSpeechSynthesizer.setStereoVolume(1.0f, 1.0f);
    }

    public static void close() {
        int result = mSpeechSynthesizer.release();
    }

    public static void speek(String s) {
        mSpeechSynthesizer.speak(s);
    }


    private static String copyAssetsFile(String sourceFilename) throws IOException {
        String destFilename = createTmpDir(UIUtils.getContext()) + "/" + sourceFilename;
        copyFromAssets(UIUtils.getContext().getAssets(), sourceFilename, destFilename, false);
        Log.i("ceshi", "文件复制成功：" + destFilename);
        return destFilename;
    }

    // 创建一个临时目录，用于复制临时文件，如assets目录下的离线资源文件
    public static String createTmpDir(Context context) {
        String sampleDir = "baiduTTS";
        String tmpDir = Environment.getExternalStorageDirectory().toString() + "/" + sampleDir;
        if (!makeDir(tmpDir)) {
            tmpDir = context.getExternalFilesDir(sampleDir).getAbsolutePath();
            if (!makeDir(sampleDir)) {
                throw new RuntimeException("create model resources dir failed :" + tmpDir);
            }
        }
        return tmpDir;
    }

    public static boolean makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return file.mkdirs();
        } else {
            return true;
        }
    }

    public static void copyFromAssets(AssetManager assets, String source, String dest, boolean isCover)
            throws IOException {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = assets.open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                }
            }
        }
    }
}
