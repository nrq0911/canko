package com.canko.common;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UploadUtils {

    private static final String AK = "cyxmHjD98SOb50pB64YSTzcn-pvWXWSR9EJMfvQm";
    private static final String SK = "7TXuaf6A0ZlaSdl40RqIPK3nHr2-YCmgzZst3z_V";
    private static final String BUCKET = "azonebuy";
    private static final Logger log = Logger.getLogger(UploadUtils.class);

    public static boolean upload(String fileName, MultipartFile file) throws IOException {

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        boolean result = false;
        try {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(file.getBytes());
            Auth auth = Auth.create(AK, SK);
            String upToken = auth.uploadToken(BUCKET);

            try {
                Response response = uploadManager.put(byteInputStream, fileName, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
                log.info("upload qiniu file result:" + response.bodyString());
                result = true;
            } catch (QiniuException ex) {
                Response r = ex.response;
                log.error("upload file error result:" + r.toString() + "due to " + ex);
            }
        } catch (UnsupportedEncodingException ex) {
            log.error(ex);
        }
        return result;
    }

}
