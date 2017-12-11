package cn.tianjian.util;

import org.junit.Test;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description
 */
public class CsvUtilTest {

    @Test
    public void csvUtilTest() throws Exception {
        CsvUtil util = new CsvUtil("D:\\springbootzikpin\\mircoservice-master\\" +
                "springboot+zipkin\\spirngboot-zipkin\\common\\src\\main\\resources\\test\\" +
                "csvdata.csv");

        int rowNum = util.getRowNum();
        int colNum = util.getColNum();
        String x = util.getRow(2);
        String y = util.getCol(2);
        System.out.println("rowNum:" + rowNum);
        System.out.println("colNum:" + colNum);
        System.out.println("x:" + x);
        System.out.println("y:" + y);

        for(int i=1;i<rowNum;i++){
            for(int j=0;j<colNum;j++){
                System.out.println("result[" + i + "|" + j + "]:" + util.getString(i, j));
            }
        }


    }

}
