package com.zhaochen.reader;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public abstract class HBaseTableAbstract implements Closeable {
    private static final Logger logger = Logger.getLogger(HBaseTableAbstract.class);
    protected HTable table = null;
    protected String tableName = null;

    public HBaseTableAbstract(HTable table, String tableName){
        this.table = table;
        this.tableName = tableName;
    }

    @Override
    public void close() throws IOException {
        if (table != null){
            table.close();
            table = null;
        }
    }

    protected Get createGet(String uid){


    }



}
