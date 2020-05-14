package com.hs.pdf;


import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.*;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * spire.pdf.free只能显示10页
 */
public class PdfUtils {
    //系统上的自体,linux下可使用fc-list :lang=zh查看中文字体
    private static Font font = new Font("文泉驿微米黑", Font.PLAIN,12);
    private static PdfTrueTypeFont pdfTrueTypeFont = new PdfTrueTypeFont(font, true);

    public static void main(String[] args) {
        //创建PdfDocument对象
        PdfDocument pdf = new PdfDocument();
        //加载示例文档
        pdf.loadFromFile("/sddata/da2/tmp/Java并发编程的艺术.pdf");
        //总共页数
        int count = pdf.getPages().getCount();
        for(int i = 0;i < count;i++){
            insertWatermark(pdf.getPages().get(i), "四川集中稽核系统");
        }
        System.out.println(count);


        //获取第一页

        //调用insertWatermark方法插入文本水印



        //保存文档
        pdf.saveToFile("/sddata/da2/tmp/Java并发编程的艺术m.pdf");
        pdf.clone();
   }

    /**
     *
     * @param page pdf页对象
     * @param watermark 水印文字
     */
    public static void insertWatermark(PdfPageBase page,String watermark){
        Dimension2D dimension2D = new Dimension();
        dimension2D.setSize(page.getCanvas().getClientSize().getWidth() / 2,page.getCanvas().getClientSize().getHeight() / 3);
        PdfTilingBrush brush = new PdfTilingBrush(dimension2D);
        //透明度
        brush.getGraphics().setTransparency(0.3F);
        brush.getGraphics().save();
        brush.getGraphics().translateTransform((float) brush.getSize().getWidth() / 2, (float) brush.getSize().getHeight() / 2);
        brush.getGraphics().rotateTransform(-45);
        brush.getGraphics().drawString(watermark, pdfTrueTypeFont, PdfBrushes.getViolet(), 0, 0, new PdfStringFormat(PdfTextAlignment.Center));
        brush.getGraphics().restore();
        brush.getGraphics().setTransparency(1);
        Rectangle2D loRect = new Rectangle2D.Float();
        loRect.setFrame(new Point2D.Float(0, 0), page.getCanvas().getClientSize());
        page.getCanvas().drawRectangle(brush, loRect);

    }
}
