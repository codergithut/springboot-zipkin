package cn.gtmap.log.domain.query;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description
 */
public class QueryMetadata {

    private String endpoint;

    private AggregationMetaData aggregationMetaData = new AggregationMetaData();

    private List<MustQueryMetaData> queryMustMetaData;



    public AggregationMetaData getAggregationMetaData() {
        return aggregationMetaData;
    }

    public void setAggregationMetaData(AggregationMetaData aggregationMetaData) {
        this.aggregationMetaData = aggregationMetaData;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public List<MustQueryMetaData> getQueryMustMetaData() {
        return queryMustMetaData;
    }

    public void setQueryMustMetaData(List<MustQueryMetaData> queryMustMetaData) {
        this.queryMustMetaData = queryMustMetaData;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public class AggregationMetaData{
        private AggregationMetaData aggregationMetaData;

        private String type;

        private String fieldName;

        public AggregationMetaData getAggregationMetaData() {
            return aggregationMetaData;
        }

        public void setAggregationMetaData(AggregationMetaData aggregationMetaData) {
            this.aggregationMetaData = aggregationMetaData;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
    }

    /**
     * 强制必须条件
     */
    public class MustQueryMetaData {

        /**
         * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
         * @description 需要过滤查询的字段名称
         */
        private String filedName;

        /**
         * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
         * @description 需要查询的字段值（可能是范围值）
         */
        private List<String> filedValue;

        /**
         * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
         * @description 需要查询的字段符号（可能是范围值）
         */
        private List<String> symbol;

        /**
         * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
         * @description 过滤或查询条件
         */
        private String filterDo;

        public String getFiledName() {
            return filedName;
        }

        public void setFiledName(String filedName) {
            this.filedName = filedName;
        }

        public List<String> getFiledValue() {
            return filedValue;
        }

        public void setFiledValue(List<String> filedValue) {
            this.filedValue = filedValue;
        }

        public List<String> getSymbol() {
            return symbol;
        }

        public void setSymbol(List<String> symbol) {
            this.symbol = symbol;
        }

        public String getFilterDo() {
            return filterDo;
        }

        public void setFilterDo(String filterDo) {
            this.filterDo = filterDo;
        }
    }

    /**
     * 选择条件
     */
    public class ShouldQueryMetaData {
        private String type;

        private String filedName;

        private String filedValue;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFiledName() {
            return filedName;
        }

        public void setFiledName(String filedName) {
            this.filedName = filedName;
        }

        public String getFiledValue() {
            return filedValue;
        }

        public void setFiledValue(String filedValue) {
            this.filedValue = filedValue;
        }

    }


}
