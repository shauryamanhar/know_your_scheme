/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FolderUtil {

    public String getPath() {
            return (Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.ADMINFOLDERNAME + "/");

    }

    /**
     *
     */
    public void creatFolder() {
        synchronized (FolderUtil.class) {
            try {
                // creating parrent folder
                if (!isFolderExist(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME)) {
                    create(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME);
                }
                // creating Admin folder
                if (!isFolderExist(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.ADMINFOLDERNAME)) {
                    create(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.ADMINFOLDERNAME);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isFolderExist(String fullPath) {
        File file = new File(fullPath);
        return file.exists();
    }

    private boolean create(String fullPath) {
        File file = new File(fullPath);
        file.setExecutable(true, false);
        file.setReadable(true, false);
        file.setWritable(true, false);
        return file.mkdir();
    }

}
