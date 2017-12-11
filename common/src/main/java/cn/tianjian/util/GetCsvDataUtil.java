package cn.tianjian.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/11
 * @description
 */
public class GetCsvDataUtil {
    public static List<Map<String,String>> getCsvDataUtil(String path, String encode) throws IOException {
        String[] s = new String(FileUtil.getFileAsBytes(new File(path)), encode).split("\n");
        String[] title = s[0].split(",");

        List<Map<String,String>> allDatas = new ArrayList<Map<String,String>>();

        for(int i = 1; i < s.length; i++) {
            String[] data = s[i].split(",");
            Map<String,String> dataJson = new HashMap<String,String>();
            for(int j = 0; j < data.length; j++) {
                dataJson.put(title[j], data[j]);
            }
            allDatas.add(dataJson);
        }

        return allDatas;
    }
}
