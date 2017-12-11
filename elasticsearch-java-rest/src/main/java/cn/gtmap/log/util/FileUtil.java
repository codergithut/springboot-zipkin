package cn.gtmap.log.util;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

import java.io.*;
import java.util.List;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianian</a>
 * @version 1.0, 2017/8/11
 * @description 文件工具
 */
public class FileUtil {

  /**
   * @param file 需要获取的文件
   * @return 文件的字节数组
   * @throws IOException
   */
    public static byte[] getFileAsBytes(File file) throws IOException {
        ByteSource byteSource = null ;

        byteSource = Files.asByteSource(file);

        return byteSource.read();
    }


  /**
   * @param file 需要保存的文件路径
   * @param content 文件内容
   * @return 是否保存成功
   */
    public static boolean saveFileByByte(File file, byte[] content) {

        try {

            Files.write(content, file);

            return true;

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }

  /**
   * @param file 获取文件的字符串
   * @return 文件字符串
   * @throws IOException
   */
    public static String getFileAsString(File file) throws IOException {

        BufferedReader reader = null ;

        String fileString = null;

        try {

            reader = Files.newReader(file, Charsets.UTF_8);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } finally {

            reader.close();

            return fileString;

        }
    }

    public static boolean removeFile(File file) {

        return file.delete();

    }

  /**
   * @param file 需要循环遍历的文件夹
   * @param fileFilter 文件过滤接口
   * @param allFile 已获得的文件
   * @return 以获取的所有文件
   */
    public static List<File> getFilesByPath(File file, FileFilter fileFilter, List<File> allFile) {

        if(!file.exists()) {
            return null;
        }

        File[] files = file.listFiles();

        if(files != null &&files.length > 0) {
            for(File fileDetail : files) {

                if(fileDetail.isFile()) {

                    if(fileFilter != null && !fileFilter.accept(fileDetail)) {
                        continue;
                    }

                    System.out.println(fileDetail.getAbsolutePath());
                    allFile.add(fileDetail);
                }

                if(fileDetail.isDirectory()) {
                    getFilesByPath(fileDetail, fileFilter, allFile);

                }
            }
        }
        return allFile;
    }

}
