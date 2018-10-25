package wangfeixixi.com.soaplib.network;

import java.util.HashMap;
import java.util.Map;

public class SoapRequest {
    private String endPoint;
    private String nameSpace;
    private String methodName;
    private String soapAction;
    private int version = 110;
    private boolean isDotNet;
    private Map<String, Object> mParamsMap;

    public SoapRequest(Builder builder) {
        this.endPoint = builder.endPoint;
        this.nameSpace = builder.nameSpace;
        this.methodName = builder.methodName;
        this.soapAction = builder.soapAction;
        this.mParamsMap = builder.mParamsMap;
        this.version = builder.version;
        this.isDotNet = builder.isDotNet;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }

    public void setParamsMap(Map<String, Object> paramsMap) {
        this.mParamsMap = paramsMap;
    }

    public void setDotNet(boolean dotNet) {
        this.isDotNet = dotNet;
    }

    public boolean isDotNet() {
        return this.isDotNet;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public String getNameSpace() {
        return this.nameSpace;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getSoapAction() {
        return this.soapAction;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Map<String, Object> getParamsMap() {
        return this.mParamsMap;
    }

    public static class Builder {
        private String endPoint;
        private String nameSpace;
        private String methodName;
        private String soapAction;
        private int version = 0;
        private boolean isDotNet;
        private Map<String, Object> mParamsMap = new HashMap();

        public Builder() {
        }

        public Builder endPoint(String endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Builder nameSpace(String nameSpace) {
            this.nameSpace = nameSpace;
            return this;
        }

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder soapAction(String soapAction) {
            this.soapAction = soapAction;
            return this;
        }

        public Builder setParams(Map<String, Object> params) {
            this.mParamsMap = params;
            return this;
        }

        public Builder addParam(String name, Object value) {
            this.mParamsMap.put(name, value);
            return this;
        }

        public Builder setVersion(int version) {
            this.version = version;
            return this;
        }

        public Builder setDotNet(boolean isDotNet) {
            this.isDotNet = isDotNet;
            return this;
        }

        public SoapRequest build() {
            if (this.endPoint == null) {
                throw new IllegalStateException("endPoint == null");
            } else if (this.nameSpace == null) {
                throw new IllegalStateException("nameSpace == null");
            } else if (this.methodName == null) {
                throw new IllegalStateException("methodName == null");
            } else if (this.soapAction == null) {
                throw new IllegalStateException("soapAction == null");
            } else {
                if (this.version == 0) {
                    this.version = 110;
                }

                return new SoapRequest(this);
            }
        }
    }
}
