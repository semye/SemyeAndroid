package com.semye.android.utils;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by yesheng on 2017/1/10.
 * android文件管理类
 */
public class FileUtils {

    private static FileUtils fileManager;

    private FileUtils() {

    }

    /**
     * 获取SD卡的根目录
     *
     * @return 如果SD卡存在, 返回SD卡的根目录,否则返回null
     */
    public static String getSDCardRootPath() {
        if (!isSDcardExist())
            try {
                throw new Exception("SD卡不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return Environment.getExternalStorageDirectory().getPath();
    }


    /**
     * 判断SDcard是否存在
     *
     * @return 如果存在返回true 如果不存在返回false
     */
    public static boolean isSDcardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    public static String rename(String fileName, String newName) {
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            fileName = newName + fileName.substring(index, fileName.length());
            return fileName;
        }
        return newName;
    }

    public static boolean createFolder(String folderName) {
        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(folderName);
        if (!file.exists()) {
            return file.mkdir();
        }
        return false;

    }

    /**
     * 检测SD卡是否存在
     */
    public static boolean checkSDcard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 获取文件保存点
     */
    @Nullable
    public static File getSaveFile(String fileNmae) {
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory()
                    .getCanonicalFile() + "/" + fileNmae);
        } catch (IOException e) {
        }
        return file;
    }

    /**
     * 从指定文件夹获取文件
     */
    @NonNull
    public static File getSaveFile(String folder, String fileNmae) {
        File file = new File(getSavePath(folder), fileNmae);
        return file;
    }

    /**
     * 获取文件保存路径
     */
    public static String getSavePath(String folder) {
        return Environment.getExternalStorageDirectory() + "/" + folder;
    }

    public static void split(File file) throws IOException {
        File dir = new File("/sdcard/xckyapp/pices/");
        dir.mkdir();

        // 计算每一份的大小
        long partLen = (file.length() + 4) / 5; // (10 + 4) / 5 = 2, (14 + 4) /
        // 5 = 3
        int fileNum = 1;
        long len = 0;

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(dir, fileNum + ".temp")));

        int b;
        while ((b = bis.read()) != -1) {
            if (len++ == partLen) {
                bos.close();
                bos = new BufferedOutputStream(new FileOutputStream(new File(
                        dir, ++fileNum + ".temp")));
                len = 0;
            }
            bos.write(b);
        }
        bis.close();
        bos.close();
    }

}
