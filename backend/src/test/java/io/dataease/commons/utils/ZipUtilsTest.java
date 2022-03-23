package io.dataease.commons.utils;

import org.junit.Test;

import java.util.ArrayList;

public class ZipUtilsTest {

    @Test
    public void unZipItTest(){
        String zipFile = "F:\\xiangmu\\zip\\vhr.zip";
        String outputFolder = "F:\\xiangmu\\zip";
        ZipUtils.unZipIt(zipFile,outputFolder);
    }
}
