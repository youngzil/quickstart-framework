package com.quickstart.test.dynamic.compile;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.IProblemFactory;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;

public class DCompile {
    private String java_file_path;
    private String java_basic_path;
    private String ecode;
    private Boolean init = false;
    private ICompilationUnit[] compilationUnits = null;

    public Class compile() throws Exception {
        if (!check()) {
            throw new Exception("Not initialize...");
        }
        Class clazz = null;
        String targetClassName = getTargetClassName();
        final DNameEnvironment env = new DNameEnvironment(targetClassName, java_file_path, Thread.currentThread().getContextClassLoader(), ecode);
        final IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.proceedWithAllProblems();
        final DCompilerRequestor requestor = new DCompilerRequestor(java_basic_path);
        final IProblemFactory problemFactory = new DefaultProblemFactory(Locale.getDefault());
        Compiler compiler = new Compiler(env, policy, getSettings(), requestor, problemFactory, true);
        compiler.compile(compilationUnits);
        URL url = new URL("file:/" + java_basic_path + "/");
        System.out.println(url);
        URLClassLoader ucl = new URLClassLoader(new URL[] {url});
        clazz = ucl.loadClass(targetClassName);

        return clazz;
    }

    public Map getSettings() {
        Map settings = new HashMap();
        settings.put(CompilerOptions.OPTION_LineNumberAttribute, CompilerOptions.GENERATE);
        settings.put(CompilerOptions.OPTION_SourceFileAttribute, CompilerOptions.GENERATE);
        settings.put(CompilerOptions.OPTION_ReportDeprecation, CompilerOptions.IGNORE);
        settings.put(CompilerOptions.OPTION_Encoding, ecode);
        // Source JVM
        settings.put(CompilerOptions.OPTION_Source, CompilerOptions.VERSION_1_6);
        // Target JVM
        settings.put(CompilerOptions.OPTION_TargetPlatform, CompilerOptions.VERSION_1_6);
        settings.put(CompilerOptions.OPTION_Compliance, CompilerOptions.VERSION_1_6);
        return settings;
    }

    private boolean check() {
        return init;
    }

    public DCompile() {}

    public String getTargetClassName() {
        String targetClassName = java_file_path.substring(java_basic_path.length() + 1, java_file_path.indexOf("."));
        return targetClassName.replace("/", ".");
    }

    public void initialize(String file_path, String basic_path, String ecode) {
        this.java_file_path = file_path;
        this.java_basic_path = basic_path;
        this.ecode = ecode;
        String targetClassName = getTargetClassName();
        if (targetClassName == null) {
            targetClassName = "";
        }
        compilationUnits = new ICompilationUnit[1];
        compilationUnits[0] = new DCompilationUnit(this.java_file_path, targetClassName, this.ecode);
        init = true;
    }
}
