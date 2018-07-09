/**
 * 项目名称：quickstart-yaml 
 * 文件名：YamlHelper.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.snakeyaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * YamlHelper
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月22日 下午5:19:48
 * @since 1.0
 */
public class YamlHelper {

    public static final String RENAME_MANIFEST_PACKAGE_NODE = "renameManifestPackage";

    public Map loadYaml(String path) {
        try {
            String yamlString = preLoad(path);
            if (yamlString == null || yamlString.length() <= 0) {
                return null;
            }
            // 初始化Yaml解析器
            Yaml yaml = new Yaml();
            // 读入文件
            Object result = yaml.load(yamlString);
            System.out.println(result.getClass());
            System.out.println(result);

            if (result instanceof Map) {
                return (Map) result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dumpYaml(Map yamlMap, String outPath) {
        try {
            // 初始化Yaml解析器
            Yaml yaml = new Yaml();
            String result = yaml.dumpAsMap(yamlMap);
            System.out.println(result);
            File newFile = new File(outPath);
            PrintWriter writer = new PrintWriter(newFile);
            writer.print(result);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 这个方法主要作用是因为文件中有!!的一行，yaml无法进行解析，所以将其去掉
    private String preLoad(String path) throws IOException {
        File yamlFile = new File(path);

        BufferedReader fileReader = new BufferedReader(new FileReader(yamlFile));
        String temp;
        StringBuilder stringBuilder = new StringBuilder();
        while ((temp = fileReader.readLine()) != null) {
            if (temp.trim().startsWith("!!")) {
                continue;
            }
            stringBuilder.append(temp + "\n");
        }
        String result = stringBuilder.toString();
        System.out.println("YAML 内容-->" + result);

        return result;
    }

    public void removePackageNode(String path, String nodeTag) {
        Map yamlMap = loadYaml(path);
        System.out.println("Map-->: " + yamlMap);

        Map pi = (Map) yamlMap.get("packageInfo");
        if (pi.containsKey(nodeTag))
            pi.remove(nodeTag);

        dumpYaml(yamlMap, path);
    }

    /**
     * 更改版本号、版本名
     */
    public void renameVersion(String path, VersionInfo versionInfo) {

        Map yamlMap = loadYaml(path);
        System.out.println("Map-->: " + yamlMap);

        Map vi = (Map) yamlMap.get("versionInfo");
        vi.put("versionCode", versionInfo.getVersionCode());
        vi.put("versionName", versionInfo.getVersionName());

        dumpYaml(yamlMap, path);
    }

    public VersionInfo getVersion(String path) {
        Map yamlMap = loadYaml(path);
        System.out.println("Map--> : " + yamlMap);

        Map vi = (Map) yamlMap.get("versionInfo");
        String code = (java.lang.String) vi.get("versionCode");
        String name = (java.lang.String) vi.get("versionName");
        System.out.println("versionCode: " + code);
        System.out.println("versionName: " + name);

        VersionInfo info = new VersionInfo();
        info.setVersionCode(code);
        info.setVersionName(name);
        return info;
    }

    public static void main(String[] args) throws IOException {
        String path = "./apktool.yml";
        YamlHelper helper = new YamlHelper();

        VersionInfo info = helper.getVersion(path);
        info.setVersionCode(String.valueOf(Integer.valueOf(info.getVersionCode()) + 1));

        helper.renameVersion(path, info);
    }

}
