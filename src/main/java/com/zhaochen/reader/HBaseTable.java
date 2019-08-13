package com.zhaochen.reader;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface HBaseTable extends Closeable {

    /**
     * 接口中定义了table所具有的方法，通过这种方式  可以限定用户使用api其他方法 保证数据访问的安全性
     * 继承Closeable接口是为了保证数据流的自动关闭
     */

    @Override
    void close() throws IOException;

    List<String> getColumnFamilyList();

    boolean isTableExists();

    boolean isRecordExists(String rowkey);

    boolean isRecordExists(byte[] rowkey);

    HBaseRecord getRecord(String rowkey);

    HBaseRecord getRecord(byte[] rowkey);

    List<HBaseRecord> getRecord(List<String> rowkeys);

    String getTableName();

    HBaseScanner getHBaseScanner();

}
