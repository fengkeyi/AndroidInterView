package com.ubtechinc.aimbot.democode.okio;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Utf8;

public class OkioDemo {

    private static final String TAG = "OkioDemo";

    public static void copyFile(InputStream inputStream, File outputFile) throws IOException {
        Source source = Okio.source(inputStream);
        final BufferedSource buffer = Okio.buffer(source);
        Sink sink = Okio.sink(outputFile);
        BufferedSink bufferedSink = Okio.buffer(sink);
        bufferedSink.writeAll(buffer);
        bufferedSink.close();
        sink.close();
        buffer.close();
        source.close();
    }

    public static void readFile(File file) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(Okio.source(file));
        String line;
        Buffer buffer = new Buffer();
        while ((line = bufferedSource.readUtf8Line()) != null) {
            buffer.writeString(line, StandardCharsets.UTF_8);
        }
        Log.i(TAG, "readFile:" + buffer.toString());
        bufferedSource.close();
        buffer.clear();
    }

}
