package com.semye.android.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by yesheng on 15/5/20.
 * 文件压缩
 */
public class ZipUtils {

    public static final String TAG = "ZipUtils";

    public static final int BUFFER_SIZE = 512 * 1024;

    /**
     * @param files   文件的大小
     * @param zipFile 文件路径
     * @throws IOException
     */
    public static void zip(String[] files, String zipFile) throws IOException {

        BufferedInputStream origin;

        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        try {
            byte data[] = new byte[BUFFER_SIZE];

            for (int i = 0; i < files.length; i++) {
                FileInputStream fi = new FileInputStream(files[i]);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                try {
                    ZipEntry entry = new ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                } finally {
                    origin.close();
                }
            }
        } finally {
            out.close();
        }
    }

    /**
     * @param zipFile  解压的文件
     * @param location 解压的路径
     * @throws IOException
     */
    public static boolean unzip(String zipFile, String location) throws IOException {
        boolean flag = false;

        try {
            byte data[] = new byte[BUFFER_SIZE];

            File f = new File(location);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
            try {
                ZipEntry ze;
                while ((ze = zin.getNextEntry()) != null) {

                    String path = location + ze.getName();

                    if (ze.isDirectory()) {
                        File unzipFile = new File(path);
                        if (!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    } else {
                        FileOutputStream fout = new FileOutputStream(path, false);
                        try {
                            int c;
                            while ((c = zin.read(data)) != -1) {
                                fout.write(data, 0, c);
                            }
                            zin.closeEntry();
                        } finally {
                            fout.close();
                            flag = true;
                        }
                    }
                }
            } finally {
                zin.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Unzip exception", e);
        }

        return flag;
    }

}
