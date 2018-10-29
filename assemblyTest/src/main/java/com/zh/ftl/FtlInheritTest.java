package com.zh.ftl;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import freemarker.template.*;

import java.io.*;
import java.util.HashMap;

/**
 * 特殊指令
 * <@block></@block>
 * <@extends></@extends>
 * <@override></@override>
 * 使用
 */
public class FtlInheritTest {

    private static final String DEFAULT_ENCODING = "utf-8";

    private static final String TEMPLATE_DIR = "ftl";

    public static void main(String[] args) throws IOException, TemplateException {
        String templateDir = FtlInheritTest.class.getClassLoader().getResource(TEMPLATE_DIR).getPath();
        System.out.println(templateDir);
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDirectoryForTemplateLoading(new File(templateDir));
        configuration.setDefaultEncoding(DEFAULT_ENCODING);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);

        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());

        Template template = configuration.getTemplate("home.ftl");
        Writer out = new OutputStreamWriter(System.out);
        template.process(new HashMap<String, String>(), out);
    }
}
