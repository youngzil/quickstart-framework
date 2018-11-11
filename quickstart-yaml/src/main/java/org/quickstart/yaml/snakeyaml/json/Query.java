/**
 * 项目名称：quickstart-yaml 
 * 文件名：Query.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.snakeyaml.json;

/**
 * Query
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午7:29:54
 * @since 1.0
 */
public class Query {

    private ConstantScore constant_score;

    public ConstantScore getConstant_score() {
        return constant_score;
    }

    public void setConstant_score(ConstantScore constant_score) {
        this.constant_score = constant_score;
    }

    public class ConstantScore {
        private Filter filter;

        public Filter getFilter() {
            return filter;
        }

        public void setFilter(Filter filter) {
            this.filter = filter;
        }
    }

    public class Filter {
        private Range range;

        public Range getRange() {
            return range;
        }

        public void setRange(Range range) {
            this.range = range;
        }
    }

    public class Range {
        private LastUpdated last_updated;

        public LastUpdated getLast_updated() {
            return last_updated;
        }

        public void setLast_updated(LastUpdated last_updated) {
            this.last_updated = last_updated;
        }
    }

    public class LastUpdated {
        private long gte;
        private long lte;

        public long getGte() {
            return gte;
        }

        public void setGte(long gte) {
            this.gte = gte;
        }

        public long getLte() {
            return lte;
        }

        public void setLte(long lte) {
            this.lte = lte;
        }
    }

}
