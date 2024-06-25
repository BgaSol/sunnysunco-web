package com.sunnysunco.cloud.util;

import com.sunnysunco.cloud.business.base.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
public class ZipUtil {

    public static void main(String[] args) {
        String zipFilePath = "x.zip";
        String destDirectory = "x";
        for (int i = 0; i < 10; i++) {
            unzip(zipFilePath, destDirectory);
        }
    }

    /**
     * 解压zip文件
     *
     * @param zipFilePath zip文件路径
     * @param destDir     解压目录
     */
    public static void unzip(String zipFilePath, String destDir) {
        // 判断destDir是否存在
        File destDirectory = new File(destDir);
        if (destDirectory.exists()) {
            destDirectory.delete();
        }
        // 判断zipFilePath是否存在
        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            log.error("zip文件不存在");
            throw new BaseException("zip文件不存在");
        }

        try {
            InputStream inputStream = Files.newInputStream(Paths.get(zipFilePath));
            unzip(inputStream, destDir);
            inputStream.close();
        } catch (IOException e) {
            log.error("zip文件读取失败", e);
        }
    }

    /**
     * 解压zip文件
     *
     * @param inputStream zip文件输入流
     * @param destDir     解压目录
     */
    public static void unzip(InputStream inputStream, String destDir) {
        long start = System.currentTimeMillis();
        //todo 获取zip文件的字符编码
        String encoding = CharsetNames.UTF_8;
        // 创建zip输入流 用于读取zip文件
        ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(inputStream, encoding);
        // 获取zip文件中的每一个文件
        ZipArchiveEntry zipArchiveEntry;
        try {
            while ((zipArchiveEntry = zipArchiveInputStream.getNextEntry()) != null) {
                // 获取文件名
                String zipFileName = zipArchiveEntry.getName();
                // 兼容mac的压缩文件
                if (zipFileName.contains("__MACOSX")) {
                    continue;
                }
                if (zipArchiveEntry.isDirectory()) {
                    // 如果是文件夹，就创建文件夹
                    File directory = new File(destDir, zipFileName);
                    directory.mkdirs();
                } else {
                    // 创建输出文件
                    File file = new File(destDir, zipFileName);
                    // 创建文件输出流
                    OutputStream fos = Files.newOutputStream(file.toPath());
                    // 写入文件
                    IOUtils.copy(zipArchiveInputStream, fos);
                    fos.close();
                }
            }
        } catch (IOException e) {
            log.error("解压失败", e);
            throw new BaseException("解压失败");
        }
        long end = System.currentTimeMillis();
        log.info("解压完成，耗时：" + (end - start) + "ms");
    }

    /**
     * 解压7z文件
     *
     * @param zipFilePath zip文件路径
     * @param destDir     解压目录
     */
    public static void un7z(String zipFilePath, String destDir) {
        // 判断destDir是否存在
        File destDirectory = new File(destDir);
        if (destDirectory.exists()) {
            destDirectory.delete();
        }
        // 判断zipFilePath是否存在
        File sevenZipFile = new File(zipFilePath);
        if (!sevenZipFile.exists()) {
            log.error("7z文件不存在");
            throw new BaseException("7z文件不存在");
        }
        try (SevenZFile sevenZFile = new SevenZFile(sevenZipFile)) {
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                InputStream sevenZFileInputStream = sevenZFile.getInputStream(entry);
                File curfile = new File(destDir, entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                FileUtils.copyInputStreamToFile(sevenZFileInputStream, curfile);
                sevenZFileInputStream.close();
            }
        } catch (IOException e) {
            log.error("解压失败", e);
            throw new BaseException("解压失败");
        }
    }

    /**
     * 解压缩7z文件
     *
     * @param inputStream 7z文件输入流
     * @param destDir     解压目录
     */
    public static void un7z(InputStream inputStream, String destDir) {
        try {
            String outPutPath = "./cache/7zipTemp/" + UUID.randomUUID() + ".7z";
            FileUtils.copyInputStreamToFile(inputStream, new File(outPutPath));
            un7z(outPutPath, destDir);
            FileUtils.deleteQuietly(new File(outPutPath));
        } catch (IOException e) {
            log.error("解压失败", e);
            throw new BaseException("解压失败");
        }
    }
}
