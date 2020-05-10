package com.hs.img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WatermarkImg {
    //字体名称,如：宋体
    private String FONT_NAME = "宋体";
    //字体大小，单位为像素
    private int FONT_SIZE = 20;
    //字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
    private int FONT_STYLE = Font.PLAIN;
    //字体颜色
    private Color FONT_COLOR = Color.red;


    public static void main(String[] args) throws IOException {
        File file = new File("/home/hsir/Pictures/testImg/k2ugjacjoq5k2ugjacjoq5.jpg");
        BufferedImage read = ImageIO.read(file);
        waterMarkImag(read,"积极");
    }





    public void waterMarkImag(BufferedImage bufferedImage,String waterMarkContent){
        //获取图片的大小
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        //字体定义
        Font font = new Font(FONT_NAME,FONT_STYLE,FONT_SIZE);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(bufferedImage,0,0,width,height,null);
        //文字颜色
        graphics.setColor(FONT_COLOR);

        graphics.setFont(font);

        int x = width - getWatermarkLength(waterMarkContent,graphics) - 3;
        int y = height - 3;

        graphics.drawString(waterMarkContent,x,y);
        graphics.dispose();


    }

    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
}
