import javax.swing.*;


public class grafisch {
    public static void main(String[] args) {
      JFrame frame=new JFrame("hello world");
      JLabel label=new JLabel("hello world");

      frame.add(label);
      label.setBounds(0,0,100,50);
      frame.setSize(200,100);
      frame.setLayout(null);
      frame.setVisible(true);
    }
}
