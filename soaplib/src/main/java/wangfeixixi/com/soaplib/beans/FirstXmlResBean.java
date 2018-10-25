package wangfeixixi.com.soaplib.beans;

import java.util.List;

/**
 * 作者：guoyzh
 * 时间：2018/8/31
 * 功能：
 */

public class FirstXmlResBean extends BaseSoapBean {
    public String name;
    public double x;
    public double y;
    public int speed;



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
