package com.isjingjing.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteMezzaninesRequest;
import com.aliyuncs.vod.model.v20170321.DeleteMezzaninesResponse;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.isjingjing.utils.exception.defineexception.CustomException;
import com.isjingjing.utils.result.Result;
import com.isjingjing.vod.service.VodService;
import com.isjingjing.vod.utils.AliyunVodSDKUtils;
import com.isjingjing.vod.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @authors:静静
 * @description:null
 */
@Service
public class VodServiceImpl implements VodService, Serializable {

    @Override
    public void delBatchVideos(String videoIds) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient();
            DeleteMezzaninesRequest request = new DeleteMezzaninesRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoIds);
            request.setForce(false);
            client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delVideo(String videoId) {
        try{
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient();
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
        }catch (ClientException e){
            throw new CustomException(20001, "视频删除失败");
        }
    }

    @Override
    public String uploadVideo(MultipartFile file) {

        String fileName = file.getOriginalFilename();

        String title = fileName.substring(0, fileName.lastIndexOf("."));


        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new CustomException(20001, "获取视频上传流失败");
        }

        UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }


        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response.getVideoId();
    }


}
