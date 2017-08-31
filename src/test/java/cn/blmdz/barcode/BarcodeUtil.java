package cn.blmdz.barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 * 条形码工具类
 *
 * @author tangzz
 * @createDate 2015年9月17日
 *
 */
public class BarcodeUtil {

	/**
	 * 生成文件
	 *
	 * @param msg
	 * @param path
	 * @return
	 */
	public static File generateFile(String msg, String path) {
		File file = new File(path);
		try {
			generate(msg, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return file;
	}

	/**
	 * 生成字节
	 *
	 * @param msg
	 * @return
	 */
	public static byte[] generate(String msg) {
		ByteArrayOutputStream ous = new ByteArrayOutputStream();
		generate(msg, ous);
		return ous.toByteArray();
	}

	/**
	 * 生成到流
	 *
	 * @param msg
	 * @param ous
	 */
	public static void generate(String msg, OutputStream ous) {
		if (msg == null || msg.length() == 0 || ous == null) {
			return;
		}
		Code128Bean bean = new Code128Bean();

		// 精细度
		final int dpi = 500;
//		final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
//		System.out.println(moduleWidth);
		// 配置对象
		bean.setModuleWidth(0.2);
		bean.setHeight(10);
		bean.setFontSize(0);
//		bean.setFontName("微软雅黑");
//		bean.setBarHeight(20);
//		bean.setQuietZone(20);
//		bean.setWideFactor(3);
//		bean.doQuietZone(false);

		String format = "image/png";
		try {

			
			
			// 输出到流
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

			// 生成条形码
			bean.generateBarcode(canvas, msg);

			// 结束绘制
			canvas.finish();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		String msg = "8022210002002800009";
		String path = "barcode.png";
		generateFile(msg, path);
	}
}