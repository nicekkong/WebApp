package com.je.webapp.form;

import java.util.LinkedHashMap;

public class ResultForm<K, V> {
    private int                 resultCode;
    private String              resultMsg;
    private int                 totalCnt;
    private LinkedHashMap<K, V> result;
    
    public ResultForm() {
        this.result = new LinkedHashMap<>();
    }
    
    public int getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
    
    public String getResultMsg() {
        return resultMsg;
    }
    
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    
    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }
    
    public V getResult(K key) {
        return result.get(key);
    }
    
    public LinkedHashMap<K, V> getResult() {
        return result;
    }
    
    public void setResult(K key, V value) {
        this.result.put(key, value);
    }
}
