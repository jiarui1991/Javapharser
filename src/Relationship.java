import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

//package pack2;

public class Relationship {
	private ClassOrInterfaceDeclaration a;
	private ClassOrInterfaceDeclaration b;
	String relationship;

	
	public Relationship(ClassOrInterfaceDeclaration a, ClassOrInterfaceDeclaration b, String re) {
		this.a = a;
		this.b =b;
		this.relationship =re;
	}
	
	public String toString() {
		return relationship;
		
	}
	
	public ClassOrInterfaceDeclaration getA(){
		return a;
	}
	
	public ClassOrInterfaceDeclaration getB(){
		return b;
	}
	
	public String printtoString(){
		return a.getNameAsString()+relationship+b.getNameAsString();
	}
	
	
	
}
