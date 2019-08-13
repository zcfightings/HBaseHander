package com.zhaochen.reader;

public class HBaseKey {
    private byte[] rowkey = null;
    private String id = null;

    public HBaseKey(String id){
        this.id = id;
        this.rowkey = getRowkeyById(id);
    }

    public HBaseKey(byte[] rowkey){
        this.rowkey = rowkey;
        this.id = getIdByRowkey(rowkey);
    }

    public byte[] getRowKey(){
        return this.rowkey;
    }

    public String getId(){
        return this.id;
    }

    private byte[] getRowkeyById(String id){
        return salting(id).getBytes();
    }

    private String getIdByRowkey(byte[] rowkey){
        return unSalting(new String(rowkey));
    }

    private String salting(String id){
        /**
         * todo
         * 具体加盐方式可根据业务添加
         */
        return id;
    }

    private String unSalting(String sid){
        /**
         * todo
         * 具体解盐方式根据业务添加
         */
        return sid;
    }

}
