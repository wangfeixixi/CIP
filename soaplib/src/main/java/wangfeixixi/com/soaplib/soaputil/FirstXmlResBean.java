package wangfeixixi.com.soaplib.soaputil;

import java.util.List;

/**
 * 作者：guoyzh
 * 时间：2018/8/31
 * 功能：
 */

public class FirstXmlResBean extends BaseSoapResBean {

    /**
     * ROWDATA : {"ROW":[{"detpname":"办公室","deptid":"D00001","deptcode":"0001"},{"detpname":"东莞市澳星博士眼镜有限公司","deptid":"D00002","deptcode":"0000"},{"detpname":"营运部","deptid":"D00006","deptcode":1004},{"detpname":"东莞运营部万顺店","deptid":"D00008","deptcode":50002},{"detpname":"东莞德政中路长安天虹店","deptid":"D00009","deptcode":52212},{"detpname":"东莞东城区电视台店","deptid":"D00010","deptcode":52213},{"detpname":"东莞东城大道世博广场店","deptid":"D00011","deptcode":52214},{"detpname":"东莞南城区中环财富广场店","deptid":"D00012","deptcode":52215},{"detpname":"东莞东纵路东湖花园店","deptid":"D00013","deptcode":52216},{"detpname":"东莞元美路华凯大厦店","deptid":"D00016","deptcode":52219}]}
     * Version : 2
     * METADATA : {"FIELDS":{"FIELD":[{"WIDTH":6,"attrname":"deptid","fieldtype":"string"},{"WIDTH":6,"attrname":"deptcode","fieldtype":"string"},{"WIDTH":50,"attrname":"detpname","fieldtype":"string"}]},"PARAMS":""}
     */

    private DATAPACKETBean DATAPACKET;

    public DATAPACKETBean getDATAPACKET() {
        return DATAPACKET;
    }

    public void setDATAPACKET(DATAPACKETBean DATAPACKET) {
        this.DATAPACKET = DATAPACKET;
    }

    public static class DATAPACKETBean {
        private ROWDATABean ROWDATA;
        private int Version;
        /**
         * FIELDS : {"FIELD":[{"WIDTH":6,"attrname":"deptid","fieldtype":"string"},{"WIDTH":6,"attrname":"deptcode","fieldtype":"string"},{"WIDTH":50,"attrname":"detpname","fieldtype":"string"}]}
         * PARAMS :
         */

        private METADATABean METADATA;

        public ROWDATABean getROWDATA() {
            return ROWDATA;
        }

        public void setROWDATA(ROWDATABean ROWDATA) {
            this.ROWDATA = ROWDATA;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }

        public METADATABean getMETADATA() {
            return METADATA;
        }

        public void setMETADATA(METADATABean METADATA) {
            this.METADATA = METADATA;
        }

        public static class ROWDATABean {
            /**
             * detpname : 办公室
             * deptid : D00001
             * deptcode : 0001
             */

            private List<ROWBean> ROW;

            public List<ROWBean> getROW() {
                return ROW;
            }

            public void setROW(List<ROWBean> ROW) {
                this.ROW = ROW;
            }

            public static class ROWBean {
                private String detpname;
                private String deptid;
                private String deptcode;

                public String getDetpname() {
                    return detpname;
                }

                public void setDetpname(String detpname) {
                    this.detpname = detpname;
                }

                public String getDeptid() {
                    return deptid;
                }

                public void setDeptid(String deptid) {
                    this.deptid = deptid;
                }

                public String getDeptcode() {
                    return deptcode;
                }

                public void setDeptcode(String deptcode) {
                    this.deptcode = deptcode;
                }
            }
        }

        public static class METADATABean {
            private FIELDSBean FIELDS;
            private String PARAMS;

            public FIELDSBean getFIELDS() {
                return FIELDS;
            }

            public void setFIELDS(FIELDSBean FIELDS) {
                this.FIELDS = FIELDS;
            }

            public String getPARAMS() {
                return PARAMS;
            }

            public void setPARAMS(String PARAMS) {
                this.PARAMS = PARAMS;
            }

            public static class FIELDSBean {
                /**
                 * WIDTH : 6
                 * attrname : deptid
                 * fieldtype : string
                 */

                private List<FIELDBean> FIELD;

                public List<FIELDBean> getFIELD() {
                    return FIELD;
                }

                public void setFIELD(List<FIELDBean> FIELD) {
                    this.FIELD = FIELD;
                }

                public static class FIELDBean {
                    private int WIDTH;
                    private String attrname;
                    private String fieldtype;

                    public int getWIDTH() {
                        return WIDTH;
                    }

                    public void setWIDTH(int WIDTH) {
                        this.WIDTH = WIDTH;
                    }

                    public String getAttrname() {
                        return attrname;
                    }

                    public void setAttrname(String attrname) {
                        this.attrname = attrname;
                    }

                    public String getFieldtype() {
                        return fieldtype;
                    }

                    public void setFieldtype(String fieldtype) {
                        this.fieldtype = fieldtype;
                    }
                }
            }
        }
    }
}
