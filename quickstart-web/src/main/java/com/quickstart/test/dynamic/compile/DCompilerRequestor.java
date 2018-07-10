package com.quickstart.test.dynamic.compile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;

public class DCompilerRequestor implements ICompilerRequestor {

    private String outputDir;

    public DCompilerRequestor(String outDir) {
        this.outputDir = outDir;
    }

    public void acceptResult(CompilationResult result) {
        try {
            boolean error = false;
            if (result.hasProblems()) {
                IProblem[] problems = result.getProblems();
                for (int i = 0; i < problems.length; i++) {
                    IProblem problem = problems[i];
                    if (problem.isError()) {
                        error = true;
                        System.err.println(problem.toString());
                    }
                }
            }
            if (!error) {
                ClassFile[] classFiles = result.getClassFiles();
                for (int i = 0; i < classFiles.length; i++) {
                    ClassFile classFile = classFiles[i];
                    char[][] compoundName = classFile.getCompoundName();
                    String className = "";
                    String sep = "";
                    for (int j = 0; j < compoundName.length; j++) {
                        className += sep;
                        className += new String(compoundName[j]);
                        sep = ".";
                    }
                    byte[] bytes = classFile.getBytes();
                    String outFile = outputDir + "/" + className.replace('.', '/') + ".class";

                    FileOutputStream fout = new FileOutputStream(outFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fout);
                    bos.write(bytes);
                    bos.close();
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
