package com.csl.controller;

import com.csl.config.FileUploadConfig;
import com.csl.objects.HttpException;
import com.csl.utils.DateUtil;
import com.csl.utils.ResultObject;
import com.csl.utils.UserIdThreadLocal;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController //@RestController注解中包含了@ResponseBody注解
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @PostMapping("/login")
    public ResultObject login(){
        return null;
    }

    //修改用户信息
    @PostMapping("/changeUserInfo")
    public ResultObject changeUserInfo(){
        return null;
    }

    //修改用户头像，前端页面修改头像是独立的，点击头像，选择图片就直接上传了，并不是和修改用户信息一起修改
    @PostMapping("/changeUserFace")
    public ResultObject changeUserFace(
            @RequestParam(required = false) @NotNull(message = "用户头像不能为空") MultipartFile userFaceFile){

        //获取上传头像图片文件原始名称
        String fileOriginName = userFaceFile.getOriginalFilename();
        if (StringUtils.isNotBlank(fileOriginName)){
            //获取上传头像图片的后缀
            String arr[] = fileOriginName.split("\\.");
            String suffix = arr[arr.length - 1];

            //判断图片后缀的格式 防止攻击  .sh .php等比较危险的后缀
            if (!suffix.equalsIgnoreCase("png") &&
                    !suffix.equalsIgnoreCase("jpg") &&
                    !suffix.equalsIgnoreCase("jpeg") ) {
//                return IMOOCJSONResult.errorMsg("图片格式不正确！");
            }

            //组成新的头像图片名称
            // 这是一种覆盖式上传，每次用户上传新的头像，老的头像就会被覆盖掉，因为新的文件名一样
            // 如果需要增量式上传，就需要在新的文件名中拼接例如 时间戳，这样用户每次上传的头像在服务器硬盘上都会存在
            String newFileName = "face-" + UserIdThreadLocal.get() + "." + suffix;

            //头像图片最终上传的位置（服务器上的保存位置）
            // 定义头像保存的地址
            //        String fileSpace = IMAGE_USER_FACE_LOCATION;
            String fileSpace = fileUploadConfig.getImageUserFaceLocation();
            // 在路径上为每一个用户增加一个userid，用于区分不同用户上传
            String uploadPathPrefix = File.separator + UserIdThreadLocal.get();
            //头像图片最终上传的位置（服务器上的保存位置） 每一个用户有一个以userid命名的独立文件夹
            ///workspaces/images/csl/faces/userid/face-userid.png
            String filePath = fileSpace + uploadPathPrefix + File.separator + newFileName;

            FileOutputStream fileOutputStream = null;

            try{
                //文件保存到服务器上的保存位置
                File outFile = new File(filePath);
                if (outFile.getParentFile() != null){
                    //创建文件夹，TODO 需要理解
                    outFile.getParentFile().mkdirs();
                }

                fileOutputStream = new FileOutputStream(outFile);
                InputStream inputStream = userFaceFile.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
            }catch (Exception e){
                // 这里需要抛出运行时异常
//            throw new HttpException(1000,200);
                e.printStackTrace();
            }
            finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    // 这里需要抛出运行时异常
//            throw new HttpException(1000,200);
                    e.printStackTrace();
                }
            }

            //将用户头像图片地址保存在数据库中 User表中
            // 获取图片服务地址
            String imageServerUrl = fileUploadConfig.getImageServerUrl();

            // 用于提供给web服务访问的地址
            uploadPathPrefix += ("/" + newFileName);

            // 由于浏览器可能存在缓存的情况，所以在这里，我们需要加上时间戳来保证更新后的图片可以及时刷新
            // 图片url后加 ?t=xxx 还是会映射到硬盘上的图片地址的
            String finalUserFaceUrl = imageServerUrl + uploadPathPrefix
                    + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

            // TODO
        }else{
            // TODO 无文件名的图片 返回一个error
        }

        return null;
    }
}
