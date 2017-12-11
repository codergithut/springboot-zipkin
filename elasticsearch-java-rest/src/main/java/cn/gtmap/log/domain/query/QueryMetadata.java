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

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 查询入口
     */
    private String endpoint;

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 统计查询描述
     */
    private AggregationMetaData aggregationMetaData = new AggregationMetaData();

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 查询过滤条件描述
     */
    private List<MustQueryMetaData> queryMustMetaData;

    /* set get 方法 */
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

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 统计查询源信息数据封装
     *
     */
    public class AggregationMetaData{

        /**
         * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
         * @description 子查询统计
         */
        private AggregationMetaData aggregationMetaData;

        /**
         * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
         * @description 类别 Count Average 等等，类别不同构造的json数据不同
         */
        private String type;

        /**
         * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
         * @description 统计字段的名称
         */
        private String fieldName;

        /* set get 方法 */
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
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 强制满足的需求或条件
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

        /* set get 方法 */
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
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 选择性可以满足的条件 注释同上
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
