package com.zhaochen.reader;

import com.alibaba.fastjson.JSONObject;

import java.util.NavigableMap;

public class HBaseRecordImpl implements HBaseRecord{
    @Override
    public NavigableMap<String, String> getInfoMap() {
        return null;
    }

    @Override
    public JSONObject getInfoJson() {
        return null;
    }

    @Override
    public boolean containsColumn() {
        return false;
    }

    @Override
    public boolean containsColumns() {
        return false;
    }

    @Override
    public String getValue(String column) {
        return null;
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
