package com.offcial.core;

import com.offcial.services.MessageService;
import com.offcial.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信对接核心
 *
 * @author Zza
 * @date 2017-10-18 16:01
 */
public class MyServlet extends HttpServlet {

    @Autowired
    private MessageService messageService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收微信服务器以Get请求发送的4个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            // 校验通过，原样返回echostr参数内容
            out.print(echostr);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respXml = messageService.processRequest(request);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.close();
    }

}
