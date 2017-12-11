package cn.gtmap.log.util;

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
    public static List<Map<String,Object>> getCsvDataUtil(String path, String encode) throws IOException {
        String[] s = new String(FileUtil.getFileAsBytes(new File(path)), encode).split("\n");
        String[] title = s[0].split(",");

        List<Map<String,Object>> allDatas = new ArrayList<Map<String,Object>>();

        for(int i = 1; i < s.length; i++) {
            String[] data = s[i].split(",");
            Map<String,Object> dataJson = new HashMap<String,Object>();
            for(int j = 0; j < data.length; j++) {
                String titleDetail = filterString(title[j]);
                if(j ==0) {
                    titleDetail = "序号";
                }
                dataJson.put(titleDetail, filterString(data[j]));
            }
            allDatas.add(dataJson);
        }

        return allDatas;
    }

    private static String filterString(String str) {
        return str.replace("\"", "").replace("\r", "");
    }
}
