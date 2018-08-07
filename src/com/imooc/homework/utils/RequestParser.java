package com.imooc.homework.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;

public class RequestParser {

    public static FileItem getExcel(HttpServletRequest request) {

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        FileItem fileItem = null;
        try {
            fileItem = servletFileUpload.parseRequest(request).get(0);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return fileItem;
    }
}
