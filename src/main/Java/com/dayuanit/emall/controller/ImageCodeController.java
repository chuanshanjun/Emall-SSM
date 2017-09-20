package com.dayuanit.emall.controller;

import com.dayuanit.emall.util.PicCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

@Controller
@RequestMapping("/code")
public class ImageCodeController extends BaseController{

    Logger log = LoggerFactory.getLogger(ImageCodeController.class);

    @RequestMapping("/create")
    public void createImg(HttpServletRequest req, HttpServletResponse resp) {
        String code = PicCodeUtil.createCode(4);//生成4位随机数
        log.info(">>>生成的随机码:{}", code);
        req.getSession().setAttribute("code", code);//每请求一次生成新的随机码
        BufferedImage bimg = PicCodeUtil.creatImage(code);//将生成的验证码转换成图片
        OutputStream os = null;
        try {
            resp.setContentType("image/jpeg");//设置响应头文件使浏览器知道文件类型为图片
            resp.setHeader("Pragma", "no-cache");//不缓存
            resp.setHeader("Cache-Control", "no-cache");//不缓存
            resp.setDateHeader("Expires", 0);//使用服务器端控制AJAX页面缓存,三个配合使用

            os = resp.getOutputStream();
            ImageIO.write(bimg, "png", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
