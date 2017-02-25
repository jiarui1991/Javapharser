import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

//package com.github.javaparser;

public class Main {



    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("/Users/jiaruihu/Documents/workspace/Javapharser/src/A.java");

        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        // prints the resulting compilation unit to default system output
        System.out.println(cu.toString());
        // visit and print the methods names
        new MethodVisitor().visit(cu, null);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /*
             * here you can access the attributes of the method.
             * this method will be called for all methods in this
             * CompilationUnit, including inner class methods
             */
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }



}
