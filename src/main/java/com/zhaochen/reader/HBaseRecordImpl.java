package com.zhaochen.reader;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class HBaseRecordImpl implements HBaseRecord{

    NavigableMap<String, NavigableMap<Long, String>> resultMap = null;

    public HBaseRecordImpl(Result result) throws UnsupportedEncodingException {
        this.resultMap = result2Map(result);
    }

    private NavigableMap<String, NavigableMap<Long, String>> result2Map(Result result) throws UnsupportedEncodingException {
        NavigableMap<String, NavigableMap<Long, String>> resultMap = new TreeMap<>();
        for(Cell cell : result.listCells()){
            String key = new String(CellUtil.cloneQualifier(cell),"utf-8");
            String value = new String(CellUtil.cloneValue(cell),"utf-8");
            if (!resultMap.keySet().contains(key)){
                NavigableMap<Long, String> valueMap = new TreeMap<>();
                resultMap.put(key, valueMap);
            }
            long ts = cell.getTimestamp();
            resultMap.get(key).put(ts, value);
        }
        return resultMap;
    }

    @Override
    public NavigableMap<String, String> getInfoMap() {
        NavigableMap<String, String> result = new TreeMap<>();
        for (String key: this.resultMap.keySet()){
            result.put(key, resultMap.get(key).firstEntry().getValue());
        }
        return result;
    }

    @Override
    public NavigableMap<String, NavigableMap<Long, String>> getMultipleInfoMap() {
        return this.resultMap;
    }

    @Override
    public JSONObject getInfoJson() {
        return new JSONObject((TreeMap)getInfoMap());
    }

    @Override
    public JSONObject getMultipleInfoJson() {
        return new JSONObject((TreeMap)this.resultMap);
    }

    @Override
    public boolean containsColumn(String column) {
        return resultMap.keySet().contains(column);
    }

    @Override
    public boolean containsColumns(List<String> columns) {
        for (String column : columns){
            if (!containsColumn(column)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getValue(String column) {
        return getInfoMap().get(column);
    }

    @Override
    public Map<Long, String> getMultipleValue(String column) {
        return this.resultMap.get(column);
    }

    @Override
    public String getUid() {
        return null;
    }

    @Override
    public String getGid() {
        return null;
    }
}
