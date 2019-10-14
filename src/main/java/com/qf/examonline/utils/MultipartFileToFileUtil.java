package com.qf.examonline.utils;


import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MultipartFileToFileUtil {



    public static<T> List<T> change(File dest, Class<T> clazz){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(dest);
                List<T> list = ImportExcelUtil.importExcel(inputStream, clazz);
                return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dest.delete();
        }
        throw new RuntimeException();
    }


        /*public static List change(File dest, Object object){
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(dest);
                Class<?> clazz = object.getClass();
                String simpleName = clazz.getSimpleName();
                if(simpleName.equals("SelectQuestions")){
                    List<SelectQuestions> list = ImportExcelUtil.importExcel(inputStream, SelectQuestions.class);
                    return list;
                }else if(simpleName.equals("SketchQuestions")){
                    List<SketchQuestions> list = ImportExcelUtil.importExcel(inputStream, SketchQuestions.class);
                    return list;
                }else if (simpleName.equals("BooleanQuestions")){
                    List<BooleanQuestions> list = ImportExcelUtil.importExcel(inputStream, BooleanQuestions.class);
                    return list;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dest.delete();
            }
            throw new RuntimeException();
        }*/

}
