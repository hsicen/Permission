
package com.hsicen.permission.checker;

import android.media.MediaRecorder;

import java.io.File;

/**
 * <p>作者：Hsicen  2019/9/28 21:39
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：录音
 */
class RecordAudioTest implements PermissionTest {

    private File mTempFile = null;
    private MediaRecorder mRecorder = null;

    RecordAudioTest() {
        mRecorder = new MediaRecorder();
    }

    @Override
    public boolean test() throws Throwable {
        try {
            mTempFile = File.createTempFile("permission", "test");

            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(mTempFile.getAbsolutePath());
            mRecorder.prepare();
            mRecorder.start();
            return true;
        } finally {
            stop();
        }
    }

    private void stop() {
        if (mRecorder != null) {
            try {
                mRecorder.stop();
            } catch (Exception ignored) {
            }
            try {
                mRecorder.release();
            } catch (Exception ignored) {
            }
        }

        if (mTempFile != null && mTempFile.exists()) {
            // noinspection ResultOfMethodCallIgnored
            mTempFile.delete();
        }
    }
}