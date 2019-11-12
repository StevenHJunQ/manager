package com.aoxing.it.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;

import com.alibaba.druid.util.Base64;

/**
 * @author hejq9
 * @date 2019-10-24
 */
public class ValidateCodeUtil {
    /**
     * 验证码类，用于最后返回此对象，包含验证码图片base64和真值
     */
    private static Validate validate = null;
    /**
     * 随机类，用于生成随机参数
     */
    private static Random random = new Random();
    /**
     * 随机生成字符串的取值范围
     */
    private static String randString = "0123456789abcdefghijkmnpqrtyABCDEFGHIJLMNQRTY";
    /**
     * 图片宽度
     */
    private static int width = 80;
    /**
     * 图片高度
     */
    private static int height = 34;
    /**
     * 字符的数量
     */
    private static int StringNum = 4;
    /**
     * 干扰线数量
     */
    private static int lineSize = 40;


    //将构造函数私有化 禁止new创建
    private ValidateCodeUtil() {
        super();
    }

    /**
     * 获取随机字符,并返回字符的String格式
     *
     * @param index (指定位置)
     * @return
     */
    private static String getRandomChar(int index) {
        //获取指定位置index的字符，并转换成字符串表示形式
        return String.valueOf(randString.charAt(index));
    }

    /**
     * 获取随机指定区间的随机数
     *
     * @param min (指定最小数)
     * @param max (指定最大数)
     * @return
     */
    private static int getRandomNum(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    /**
     * 获得字体
     *
     * @return
     */
    private static Font getFont() {
        //名称、样式、磅值
        return new Font("Fixedsys", Font.CENTER_BASELINE, 25);
    }

    /**
     * 获得颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int frontColor, int backColor) {
        if (frontColor > 255)
            frontColor = 255;
        if (backColor > 255)
            backColor = 255;

        int red = frontColor + random.nextInt(backColor - frontColor - 16);
        int green = frontColor + random.nextInt(backColor - frontColor - 14);
        int blue = frontColor + random.nextInt(backColor - frontColor - 18);
        return new Color(red, green, blue);
    }

    /**
     * 绘制字符串,返回绘制的字符串
     *
     * @param g
     * @param randomString
     * @param i
     * @return
     */
    private static String drawString(Graphics g, String randomString, int i) {
        Graphics2D g2d = (Graphics2D) g;
        //设置字体
        g2d.setFont(getFont());
        //设置颜色
        g2d.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        String randChar = String.valueOf(getRandomChar(random.nextInt(randString.length())));
        //组装
        randomString += randChar;
        int rot = getRandomNum(5, 10);
        g2d.translate(random.nextInt(3), random.nextInt(3));
        g2d.rotate(rot * Math.PI / 180);
        g2d.drawString(randChar, 13 * i, 20);
        g2d.rotate(-rot * Math.PI / 180);
        return randomString;
    }

    /**
     * 绘制干扰线
     *
     * @param g
     */
    private static void drawLine(Graphics g) {
        //起点(x,y)  偏移量x1、y1
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * @param key
     * @return String 返回base64
     * @MethodName: getRandomCode
     * @Description: 生成Base64图片验证码
     */
    public static Validate getRandomCode() {
        validate = validate == null ? new Validate() : validate;

        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 获得BufferedImage对象的Graphics对象
        Graphics g = image.getGraphics();
        //填充矩形
        g.fillRect(0, 0, width, height);
        //设置字体
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        //设置颜色
        g.setColor(getRandColor(110, 133));
        //绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drawLine(g);
        }
        //绘制字符
        String randomString = "";
        for (int i = 1; i <= StringNum; i++) {
            randomString = drawString(g, randomString, i);
            validate.setValue(randomString);
        }

        //释放绘图资源
        g.dispose();
        ByteArrayOutputStream bs = null;
        try {
            bs = new ByteArrayOutputStream();
            //将绘制得图片输出到流
            ImageIO.write(image, "png", bs);
            String imgsrc = Base64.byteArrayToBase64(bs.toByteArray());
            validate.setBase64Str(imgsrc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bs.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bs = null;
            }
        }
        return validate;
    }

    /**
     * @author chenhx
     * @ClassName: Validate
     * @Description: 验证码类
     * @date 2017年11月14日 上午11:35:34
     */
    public static class Validate implements Serializable {
        private static final long serialVersionUID = 1L;
        //Base64 值
        private String Base64Str;
        //验证码值
        private String value;

        public String getBase64Str() {
            return Base64Str;
        }

        public void setBase64Str(String base64Str) {
            Base64Str = base64Str;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
