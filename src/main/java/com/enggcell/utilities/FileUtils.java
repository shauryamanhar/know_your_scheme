/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.utilities;

import java.util.Date;

/**
 *
 * @author 1003
 */
public class FileUtils {

    public String rename(String filename,String regId) {

        synchronized (FileUtils.class) {
            try {
                FolderUtil folderUtil = new FolderUtil();
                folderUtil.creatFolder();
                folderUtil = null;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (filename == null || filename.equals("")) {
                return null;
            }
            String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
            filename = (regId + "_" + filename + "_" + String.valueOf(new Date().getTime()).concat(fileExtension));
        }

        return filename;

    }
    
    public String rename(String filename,String regId, boolean flag) {

        synchronized (FileUtils.class) {
            try {
                FolderUtil folderUtil = new FolderUtil();
                folderUtil.creatFolder();
                folderUtil = null;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (filename == null || filename.equals("")) {
                return null;
            }
            String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
            filename = (regId + fileExtension);
        }

        return filename;

    }
}
