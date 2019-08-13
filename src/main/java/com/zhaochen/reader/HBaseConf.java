package com.zhaochen.reader;


import com.zhaochen.common.Constant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HBase 配置项类，根据不同的环境，获取不同HBase配置
 * @author : zhaochen(zc5@meitu.com)
 */
public class HBaseConf {
    private static final Logger logger = Logger.getLogger(HBaseConf.class);


    private static Map<String, Map<String, String>> envMap = new HashMap<>();
    private String hBaseEnv;
    private Map<String, String> envParams = null;

    static {
        InputStream inputStream = HBaseConf.class.getResourceAsStream("/conf/hbase_conf.xml");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            Element env = document.getRootElement();
            for (Iterator i = env.elementIterator(); i.hasNext(); ) {
                Element envItem = (Element) i.next();
                String envName = envItem.getName();
                Map<String, String> envParams = new HashMap<>();
                for (Iterator j = envItem.elementIterator(); j.hasNext(); ) {
                    Element paramNode = (Element) j.next();
                    envParams.put(paramNode.getName(), paramNode.getTextTrim());
                }
                envMap.put(envName, envParams);
            }
        } catch (Exception e) {
            logger.error("HBaseConf read xml failed", e);
            System.err.println("HBaseConf read xml failed, exit.\nError message: " + e.getMessage());
            System.exit(1);
        }
    }

    public HBaseConf(String hbaseEnv){
        setHbaseEnv(hbaseEnv);
    }

    public void setHbaseEnv(String hbaseEnv){
        if (envMap.containsKey(hbaseEnv)){
            this.hBaseEnv = hbaseEnv;
            this.envParams = envMap.get(hbaseEnv);
        } else {
            throw  new IllegalArgumentException ("Can not find hbase_env:" + hbaseEnv);
        }
    }

    public String getZkQuorum(){
        return this.envParams.get(Constant.ZK_QUORUM_KEY);
    }

    public String getZkPort(){
        return this.envParams.get(Constant.ZK_PORT_KEY);
    }

    public String getZkNode(){
        return this.envParams.get(Constant.ZK_NODE_KEY);
    }

    public Configuration getConfiguration(){
        Configuration conf = HBaseConfiguration.create();
        conf.set(Constant.HBASE_ZK_QUORUM, getZkQuorum());
        conf.set(Constant.HBASE_ZK_CLIENT_PORT, getZkPort());
        conf.set(Constant.HBASE_ZNODE, getZkNode());
        return conf;
    }

    public Configuration getConfigurationWithColumnFamily(String tableName, String columnFamily){
        Configuration conf = getConfiguration();
        conf.set(TableInputFormat.INPUT_TABLE, tableName);
        conf.set(TableInputFormat.SCAN_COLUMN_FAMILY, columnFamily);
        return conf;
    }

    public Configuration getConfigurationWithColumnFamilyList(String tableName, List<String> columnFamilyList) throws IOException {

        return null;
    }




}
