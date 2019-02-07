package mao.shu.util.img;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageScale {
	/**
	 * 进行缩略图的保存
	 * 
	 * @param savePath
	 *            保存的缩略图的完整路径
	 * @param srcPath
	 *            是完整图片的路径
	 * @return
	 */
	public static boolean scale(String savePath, String srcPath) {
		// -----------------------上传完成，开始生成缩略图-------------------------
		File file1 = new File(srcPath); // 读入刚刚上传的文件
		File file2 = new File(savePath);
		if (!file2.getParentFile().exists()) { // 保存目录不存在
			file2.getParentFile().mkdirs();
		}
		FileOutputStream newImage = null;
		try {
			Image image = javax.imageio.ImageIO.read(file1);// 构造Image对象
			float tagSize = 150;// 保存大小
			int oldWidth = image.getWidth(null);// 原来图片宽度
			int oldHeight = image.getHeight(null);// 原来图片高度
			int width = 0;
			int height = 0;
			float tempdouble = 0;
			if (oldWidth > oldHeight) {
				tempdouble = oldWidth / tagSize;
			} else {
				tempdouble = oldHeight / tagSize;
			}
			width = Math.round(oldWidth / tempdouble);
			height = Math.round(oldHeight / tempdouble);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);// 图片缓存
			bufferedImage.getGraphics().drawImage(image, 0, 0, width, height, null);// 绘制缩小后的图
			newImage = new FileOutputStream(savePath);// 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newImage);
			encoder.encode(bufferedImage);// 近JPEG编码
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				newImage.close(); // 关闭文件流
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return false ;
	}
}
