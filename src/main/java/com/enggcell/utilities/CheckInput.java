package com.enggcell.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckInput {

    public String checkValue(String value) {
        if (value != null) {
            return value;
        }
        return "";
    }

    public String checkValue(Object value) {
        if (value != null) {
            return value.toString();
        }
        return "";
    }

    public Long checkValue(Long value) {
        if (value != null) {
            return value;
        }
        return Long.valueOf("0");
    }

    public String checkValue(Double value) {
        if (value != null) {
            return value.toString();
        }
        return "";
    }
    
    public String checkValueEdit(String value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value;
                }
                return valueReq;
            } else {
                return value;
            }
        }
        return "";
    }
    
    public List<String> checkValueEdit(String[] value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (Arrays.asList(value).contains(valueReq)) {
                    return Arrays.asList(value);
                }
                List<String> valreq = new ArrayList();
                valreq.add(valueReq);
                return valreq;
            } else {
                return Arrays.asList(value);
            }
        }
        List<String> valreq = new ArrayList();
        return valreq;
    }

    public String checkValueEdit(String value, Object valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value;
                }
                return valueReq.toString();
            } else {
                return value;
            }
        }
        return "";
    }

    public String checkValueEdit(BigDecimal value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(Double value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        } else if (valueReq != null) {
            return valueReq;
        }
        return "";
    }

    public String checkValueEdit(Long value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(BigInteger value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

}
