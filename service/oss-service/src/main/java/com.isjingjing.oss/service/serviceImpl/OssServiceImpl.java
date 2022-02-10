package com.isjingjing.oss.service.serviceImpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.isjingjing.oss.service.OssService;
import com.isjingjing.oss.utils.ConstantPropertiesUtil;
import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @authors:静静
 * @description:null
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {

        OSS ossClient = null;

        String url = null;

        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtil.END_POINT;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
// 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

/// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();


            String fileName = file.getOriginalFilename();

            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            String prefixName = fileName.substring(0, fileName.indexOf("."));

            String datePath = new DateTime().toString("yyyy/MM/dd");

            String newFileName = datePath + '/' + prefixName + UUID.randomUUID() + suffixName;
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, newFileName, inputStream);

            url = "https://" + bucketName + "." + endpoint + "/" + newFileName;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
// 关闭OSSClient。
            ossClient.shutdown();
        }


        return url;
    }
}
