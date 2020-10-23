package com.semye.android.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.NonNull;

/**
 * Created by yesheng on 2017/1/16.
 */
public class IOUtils {


    public static byte[] toByteArray(@NonNull InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static int copy(@NonNull InputStream input, @NonNull OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(InputStream input, @NonNull OutputStream output)
            throws IOException {
        byte[] buffer = new byte[4096];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

}
