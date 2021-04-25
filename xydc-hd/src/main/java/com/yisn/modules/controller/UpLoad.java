package com.yisn.modules.controller;

import com.yisn.modules.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yisn on 2021/04/21/21:44
 */
@Controller
public class UpLoad {

    @Value("${file.store}")
    private String storePath; //1

    @Value("${file.licenses}")
    private String licensesPath; //2

    @Value("${file.kitchen}")
    private String kitchenPath; //3

    @Value("${file.product}")
    private String productPath; //4

    @RequestMapping("upload")
    @ResponseBody
    public Result upload(@RequestParam(name="id") int id,
                         @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()) {
            return new Result(Result.ERROR, "文件内容为空");
        }
//        存放的目录
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
//        分类存放的中间目录
        String midPath = "/images";
        if(id == 1) midPath += storePath;
        else if(id == 2) midPath += licensesPath;
        else if(id == 3) midPath += kitchenPath;
        else if(id == 4) midPath += productPath;
//        获取文件名的后缀
        String originalFilename = file.getOriginalFilename();
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
//        使用时间+随机数合成新的文件名
        SimpleDateFormat sf_ = new SimpleDateFormat("yyyyMMddHHmmss");
        String times = sf_.format(new Date());
        UUID uuid=UUID.randomUUID();
        String newName = times + uuid.toString() + extendName;

        String saveUrl = staticPath + midPath + '/' + newName;
        String visitUrl = midPath + '/' + newName;

        System.out.println(saveUrl);
        System.out.println(visitUrl);

        File saveFile = new File(saveUrl);
        if(!saveFile.exists()) {
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(Result.OK, "上传成功", visitUrl);
    }
}
