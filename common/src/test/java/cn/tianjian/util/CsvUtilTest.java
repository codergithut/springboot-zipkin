package cn.tianjian.util;

import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description
 */
public class CsvUtilTest {

    @Test
    public void csvUtilTest() throws Exception {
        CsvUtil util = new CsvUtil("D:\\springbootzikpin\\mircoservice-master\\" +
                "springboot+zipkin\\springboot-zipkin\\common\\src\\main\\resources\\test\\" +
                "csvdata.csv");

        List<Map<String,String>> datas = GetCsvDataUtil.getCsvDataUtil("D:\\springbootzikpin\\mircoservice-master\\" +
                "springboot+zipkin\\springboot-zipkin\\common\\src\\main\\resources\\test\\" +
                "xxx.csv", "GB2312");

        for(Map<String,String> key : datas) {
            for(Map.Entry entry : key.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }

//        System.out.println(s);

//        int rowNum = util.getRowNum();
//        int colNum = util.getColNum();
//        String x = util.getRow(2);
//        String y = util.getCol(2);
//        System.out.println("rowNum:" + rowNum);
//        System.out.println("colNum:" + colNum);
//        System.out.println("x:" + x);
//        System.out.println("y:" + y);
//
//        for(int i=1;i<rowNum;i++){
//            for(int j=0;j<colNum;j++){
//                System.out.println("result[" + i + "|" + j + "]:" + util.getString(i, j));
//            }
//        }


    }

}
