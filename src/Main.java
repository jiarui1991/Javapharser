package pack2;

public class Main {
    public static void main(String[] args) throws Exception {

        // original path and outputpath
        int index = 5;
        String path = "test" + index;
        String output = index + ".png";
        
        drawUml obj = new drawUml(path, output);
        obj.draw();

    }

}
