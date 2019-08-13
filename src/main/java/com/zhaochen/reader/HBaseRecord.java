package com.zhaochen.reader;

import com.alibaba.fastjson.JSONObject;

import java.util.NavigableMap;

public interface HBaseRecord {
    NavigableMap<String, String> getInfoMap();

    JSONObject getInfoJson();

    boolean containsColumn();

    boolean containsColumns();

    String getValue(String column);

    String getUid();

    String getGid();
}
