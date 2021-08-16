package org.quickstart.javase.tool;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PictureTest {

    // 一、 生成一张固定大小的huhx1.jpg
    @Test
    public void thumbnailator1() {
        try {
            Thumbnails.of("image/huhx.jpg").size(80, 80).toFile("photo/huhx1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 二、 文件夹所有图片都生成缩略图
    @Test
    public void thumbnailator2() {
        try {
            Thumbnails.of(new File("image").listFiles()).size(640, 480).outputFormat("jpg").toFiles(Rename.PREFIX_DOT_THUMBNAIL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 三、 旋转角度的缩略图
    @Test
    public void thumbnailator3() {
        try {
            Thumbnails.of(new File("image/huhx.jpg")).scale(0.25).rotate(90).outputFormat("jpg").toFile("photo/huhx2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 四、 生成一个带有旋转和水印的缩略图
    @Test
    public void thumbnailator4() {
        try {
            Thumbnails.of(new File("image/huhx.jpg")).size(160, 160).rotate(90)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("image/water.jpg")), 0.5f).outputQuality(0.8f)
                .toFile(new File("photo/huhx3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 五、 把生成的图片输出到输出流
    @Test
    public void thumbnailator5() {
        try {
            OutputStream os = new FileOutputStream("photo/huhx4.jpg");
            Thumbnails.of("image/huhx.jpg").size(200, 200).outputFormat("jpg").toOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 六、 按一定的比例生成缩略图，生成缩略图的大小是原来的25%
    @Test
    public void thumbnailator6() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("image/huhx.jpg"));
            BufferedImage thumbnail = Thumbnails.of(originalImage).scale(0.25f).asBufferedImage();
            ImageIO.write(thumbnail, "JPG", new File("photo/huhx5.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 七、 对图片进行裁剪
    @Test
    public void thumbnailator7() {
        try {
            /**
             * 中心400*400的区域
             */
            Thumbnails.of("image/huhx.jpg").sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile("photo/huhxCenter.jpg");
            /**
             * 右下400*400的区域
             */
            Thumbnails.of("image/huhx.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile("photo/huhxRight.jpg");
            /**
             * 指定坐标(0, 0)和(400, 400)区域，再缩放为200*200
             */
            Thumbnails.of("image/huhx.jpg").sourceRegion(0, 0, 400, 400).size(200, 200).keepAspectRatio(false).toFile("photo/huhxRegion.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
