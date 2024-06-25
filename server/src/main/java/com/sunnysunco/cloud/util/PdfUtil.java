package com.sunnysunco.cloud.util;

import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.sunnysunco.cloud.util.dto.PdfToImageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PdfUtil {
    // 获取当前系统线程数
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    // 获取当前可用线程数
    private static final int AVAILABLE_THREAD_COUNT = THREAD_COUNT > 2 ? THREAD_COUNT - 2 : 1;

    /**
     * 多线程pdf转图片
     */
    public static boolean pdfToImageMultithreading(List<PdfToImageDto> pdfToImageDtoList) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(AVAILABLE_THREAD_COUNT);
        for (PdfToImageDto pdfToImageDto : pdfToImageDtoList) {
            executorService.execute(() -> pdfToImage(pdfToImageDto));
        }
        executorService.shutdown();
        return executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    /**
     * pdf转图片
     *
     * @param pdfFilePath pdf文件路径
     * @param destDir     图片保存路径
     * @param dpi         图片分辨率
     */
    public static void pdfToImage(String pdfFilePath, String destDir, Integer dpi, String type) {
        // 判断destDir是否存在
        File destDirectory = new File(destDir);
        // 删除destDir文件夹
        if (destDirectory.exists()) {
            destDirectory.delete();
        }
        // 创建destDir文件夹
        destDirectory.mkdirs();
        // 判断pdfFilePath是否存在
        File pdfFile = new File(pdfFilePath);
        if (!pdfFile.exists()) {
            log.error("pdf文件不存在");
            throw new BaseException("pdf文件不存在");
        }
        long start = System.currentTimeMillis();
        try {
            RandomAccessReadBufferedFile randomAccessReadBufferedFile = new RandomAccessReadBufferedFile(pdfFile);
            // 创建pdf文件
            PDDocument document = Loader.loadPDF(randomAccessReadBufferedFile);
            // 获取pdf文件的页数
            int pageCount = document.getNumberOfPages();
            // 创建pdf文件的图片
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int i = 0; i < pageCount; i++) {
                // 获取pdf文件的图片
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, dpi, ImageType.RGB);
                // 创建图片文件
                File imageFile = new File(destDir + File.separator + i + "." + type);
                // 创建图片输出流
                ImageIO.write(image, type, imageFile);
                log.info("pdf转图片成功：{}", imageFile.getAbsolutePath());
            }
            document.close();
            randomAccessReadBufferedFile.close();

        } catch (IOException e) {
            log.error("pdf转图片失败", e);
            throw new BaseException("pdf转图片失败");
        }
        long end = System.currentTimeMillis();
        log.info("pdf转图片耗时：{}ms", end - start);
    }

    public static void pdfToImage(PdfToImageDto pdfToImageDto) {
        pdfToImage(pdfToImageDto.getPdfFilePath(), pdfToImageDto.getDestDir(), pdfToImageDto.getDpi(), pdfToImageDto.getType());
    }
}
