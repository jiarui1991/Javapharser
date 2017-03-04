import java.io.File;
import java.io.FileOutputStream;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

//package com.github.javaparser;

public class Main {



    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        // FileInputStream in = new FileInputStream("/Users/jiaruihu/Documents/workspace/Javapharser/src/A.java");

        // parse the file
        // CompilationUnit cu = JavaParser.parse(in);

        // prints the resulting compilation unit to default system output
        // System.out.println(cu.toString());
        // visit and print the methods names
        // new MethodVisitor().visit(cu, null);

        StringBuilder plantUmlSource = new StringBuilder();

        plantUmlSource.append("@startuml\n");

        plantUmlSource.append("Alice -> Bob: Authentication Request\n");

        plantUmlSource.append("Bob --> Alice: Authentication Response\n");

        plantUmlSource.append("@enduml");

        SourceStringReader reader = new SourceStringReader(plantUmlSource.toString());

        FileOutputStream output = new FileOutputStream(new File("/your/path/to/plantuml/test.svg"));

        reader.generateImage(output, new FileFormatOption(FileFormat.SVG, false));

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
