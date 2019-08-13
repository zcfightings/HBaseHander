package com.zhaochen.reader;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

public interface HBaseRecord {

    NavigableMap<String, String> getInfoMap();

    NavigableMap<String, NavigableMap<Long, String>> getMultipleInfoMap();

    JSONObject getInfoJson();

    JSONObject getMultipleInfoJson();

    boolean containsColumn(String column);

    boolean containsColumns(List<String> columns);

    String getValue(String column);

    Map<Long, String> getMultipleValue(String column);

    String getUid();

    String getGid();
}
