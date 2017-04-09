
Class umlparser {
  public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream("/Users/hanchen/Dropbox/coding_interview/java/COMPE202_Personal_Project/test.java");

		CompilationUnit compilationUnit = JavaParser.parse(in);
	}
}
