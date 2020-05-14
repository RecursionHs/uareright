package com.hs.img;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImgUtils {
    //字体名称,如：宋体
    private String FONT_NAME = "宋体";
    //字体大小，单位为像素
    private int FONT_SIZE = 35;
    //字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
    private int FONT_STYLE = Font.ITALIC;
    //字体颜色
    private Color FONT_RED = Color.red;
    private Color FONT_GRAY = new Color(0f, 0f, 0f, 0.1f);
    public static String NEW_LINE=System.getProperty("line.separator");



    public static void main(String[] args) throws IOException {
        ImgUtils imgUtils = new ImgUtils();
        File file = new File("/home/hsir/Pictures/testImg/k2ugjacjoq5k2ugjacjoq5.jpg");
        BufferedImage read = ImageIO.read(file);
        String locallTime = imgUtils.getLocalTime();
        BufferedImage image = imgUtils.waterMoreMarkImag(read, "四川集中稽核系统" + NEW_LINE + locallTime);

        FileOutputStream fos = new FileOutputStream("/home/hsir/Pictures/testImg/3.jpg");
        ImageIO.write(image,"jpg",fos);
        fos.flush();
        fos.close();
        System.out.println(imgUtils.getLocalTime());
        System.out.println(Math.PI / 4d);
    }





    public BufferedImage waterMarkImag(BufferedImage bufferedImage,String waterMarkContent){
        //获取图片的大小
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        //字体定义
        Font font = new Font(FONT_NAME,FONT_STYLE,FONT_SIZE);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(bufferedImage,0,0,width,height,null);
        //文字颜色
        graphics.setColor(FONT_RED);

        graphics.setFont(font);

        int x = width - getWatermarkLength(waterMarkContent,graphics) - 3;
        int y = height - 3;

        graphics.drawString(waterMarkContent,x,y);
        graphics.dispose();

        return bufferedImage;
    }

    public BufferedImage waterMoreMarkImag(BufferedImage original,String watermarkText) {
            // create graphics context and enable anti-aliasing
            Graphics2D g2d = original.createGraphics();
            g2d.scale(1, 1);
            g2d.addRenderingHints(
                    new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON));
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // create watermark text shape for rendering
            Font font = new Font(FONT_NAME, FONT_STYLE, FONT_SIZE);
            GlyphVector fontGV = font.createGlyphVector(g2d.getFontRenderContext(), watermarkText);
            Rectangle size = fontGV.getPixelBounds(g2d.getFontRenderContext(), 8, 8);
            Shape textShape = fontGV.getOutline();
            double textWidth = size.getWidth();
            double textHeight = size.getHeight();
            AffineTransform rotate45 = AffineTransform.getRotateInstance(-45);
            Shape rotatedText = rotate45.createTransformedShape(textShape);

            // use a gradient that repeats 4 times
            g2d.setPaint(new GradientPaint(0, 0, FONT_GRAY,
                    original.getWidth() / 2, original.getHeight() / 2, FONT_GRAY));
            g2d.setStroke(new BasicStroke(0.5f));

            // step in y direction is calc'ed using pythagoras + 5 pixel padding
            double yStep = Math.sqrt(textWidth * textWidth / 2) + original.getWidth() * 0.1f;

            // step over image rendering watermark text
            for (double x = -textHeight * 3; x < original.getWidth(); x += (textHeight * 3)) {
                double y = -yStep;
                for (; y < original.getHeight(); y += yStep) {
                    g2d.draw(rotatedText);
                    g2d.fill(rotatedText);
                    g2d.translate(0, yStep);
                }
                g2d.translate(textHeight * 3, -(y + yStep));
            }

        return original;
    }
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public String getLocalTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
